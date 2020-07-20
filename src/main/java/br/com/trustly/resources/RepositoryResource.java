package br.com.trustly.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trustly.model.Category;
import br.com.trustly.model.Repositories;
import br.com.trustly.repository.CategoriesRepository;
import br.com.trustly.repository.FilesRepository;
import br.com.trustly.repository.RepositoryRepository;
import br.com.trustly.scraping.GitHubScraping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class RepositoryResource  {
	public static final String KEY = "nameRepository";
	
	@Autowired
	FilesRepository filesRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Autowired
	RepositoryRepository repositoryRepository;
	/**
	 * Receives the owner and the repository.
	 * Before accessing GitHub by Scrapping, 
	 * check if you already have it in the database in memory or if there is a processing in progress.
	 * If there is a processing for the directory in question, the application awaits termination.
	 * The param <owner> receives the owner of the github repository 
	 * @param owner
	 * @param nameRepository
	 * @return List<Repositories>
	 * @throws Exception
	 */
	@GetMapping("/repository/{owner}/{nameRepository}")
	public Iterable<Repositories> findRepositoryByName(@PathVariable(value = "owner") String owner, @PathVariable(value = "nameRepository") String nameRepository) throws Exception {
		String nomeRepositoryFull= "/"+owner.trim()+"/"+nameRepository.trim();
		
		/**
		 * As long as we have a processing for that repository being processed,
		 *  we wait for no other concurrent processing to be done
		 */
		while(GitHubScraping.inProcess.contains(nomeRepositoryFull)) {
			System.out.println("Waiting...");
			TimeUnit.SECONDS.sleep(1);
		}
		
		/**
		 * Check if we already have some processing finished in memory
		 */
		if(GitHubScraping.finalized.contains(nomeRepositoryFull)) {
			
			List<Repositories> repositoriesList = new ArrayList<Repositories>();

			Repositories repositories = new Repositories();
			repositories.setNameRepository(nomeRepositoryFull);
			repositories.setCategories(GitHubScraping.bd.get(nomeRepositoryFull));
			repositoriesList.add(repositories);
			return repositoriesList;
		}
		/**
		 * if there is no database and it is not processed in memory, 
		 * a connection and processing will be performed through the Github website
		 */
		if(repositoryRepository.findByNameRepository(nomeRepositoryFull).size()==0) {
			
			List<Repositories> repositoriesList = new ArrayList<Repositories>();
			
			Repositories repositories = new Repositories();
			repositories.setNameRepository(nomeRepositoryFull);
			repositoryRepository.save(repositories);
			
			List<Category> categories = GitHubScraping.factory(nomeRepositoryFull);
			
			for(Category c : categories) {
				filesRepository.saveAll(c.getFiles());
			}
			categoriesRepository.saveAll(categories);
			repositories.setCategories(categories);
			
			repositoriesList.add(repositories);
			
			return repositoriesList;
		}
		return repositoryRepository.findByNameRepository(nomeRepositoryFull);
	}
}
