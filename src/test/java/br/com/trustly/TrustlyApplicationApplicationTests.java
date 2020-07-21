package br.com.trustly;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.trustly.model.Category;
import br.com.trustly.model.Repositories;
import br.com.trustly.repository.CategoriesRepository;
import br.com.trustly.repository.FilesRepository;
import br.com.trustly.repository.RepositoryRepository;
import br.com.trustly.scraping.GitHubScraping;
/**
 * Class of unit tests
 * @author Leonardo Patrick
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrustlyApplicationApplicationTests {
		
	@Autowired
	FilesRepository filesRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Autowired
	RepositoryRepository repositoryRepository;
		
		@Rule
		public ExpectedException thrown = ExpectedException.none();
		/**
		 * Test whether the will return a repository
		 * @throws Exception
		 */
		@Test
		public void testRepositoryRocketSeat() throws Exception {

			String nomeRepositoryFull= "/Rocketseat/react-native-template-rocketseat-advanced";
			List<Repositories> list = getRepositoryFull(nomeRepositoryFull);
			
			assertThat(list.size()).isEqualTo(1);
		}
		/**
		 * Tests whether the specific repository will return the number of categories expected
		 * @throws Exception
		 */
		@Test
		public void testRepositoryRocketSeatCategory() throws Exception {
			String repository="/Rocketseat/react-native-template-rocketseat-advanced";
			
			List<Category> categories = GitHubScraping.factory(repository);
			
			System.out.println("Qtd: " + categories.size());
			assertThat(
					categories.size()
					).isEqualTo(8);
		}
		
		/**
		 * Tests whether the specific repository will return to expected files
		 * @throws Exception
		 */
	@Test
		public void testRepositoryRocketSeatFiles() throws Exception {
			String repository="/Rocketseat/react-native-template-rocketseat-advanced";
			
			List<Category> categories = GitHubScraping.factory(repository);
			int totFiles = 0;
			for(Category c : categories) {
				totFiles = totFiles + c.getFiles().size();
			}
			
			assertThat(
					totFiles
					).isEqualTo(21);
		}
		
		private List<Repositories> getRepositoryFull(String nomeRepositoryFull) throws Exception {
			while(GitHubScraping.inProcess.contains(nomeRepositoryFull)) {
				System.out.println("Waiting...");
				TimeUnit.SECONDS.sleep(1);
			}
			
			if(GitHubScraping.finalized.contains(nomeRepositoryFull)) {
				
				List<Repositories> repositoriesList = new ArrayList<Repositories>();

				Repositories repositories = new Repositories();
				repositories.setNameRepository(nomeRepositoryFull);
				repositories.setCategories(GitHubScraping.bd.get(nomeRepositoryFull));
				repositoriesList.add(repositories);
				return repositoriesList;
			}
		
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
