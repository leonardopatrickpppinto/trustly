package br.com.trustly.scraping;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.trustly.model.Category;
import br.com.trustly.model.Files;

public class GitHubScraping {

	private static String BASE_URL="https://github.com";
	public static ArrayList<String> inProcess = new ArrayList<String>();
	public static ArrayList<String> finalized = new ArrayList<String>();
	public static HashMap<String , List<Category>> bd = new  HashMap<String , List<Category>>();
	
	public static List<Category> Factory(String nameRepository) throws Exception {
		
		ArrayList<String> urldirectories = new ArrayList<String>();
		ArrayList<String> urlFiles = new ArrayList<String>();
		ArrayList<String> urlAccessed = new ArrayList<String>();
		
		if(!inProcess.contains(nameRepository)) {
			inProcess.add(nameRepository);
		}
		
		urldirectories = new ArrayList<String>();
		urlFiles = new ArrayList<String>();
		urlAccessed = new ArrayList<String>();
		new  ScrapingTest(BASE_URL+nameRepository);
		
		 try {
			 urldirectories.add(nameRepository);
			 String find = "js-navigation-open link-gray-dark";
	
		 		 int i =0;
				 while(i < urldirectories.size()) {
					 	
					 urldirectories.get(i);
					 urlAccessed.add(urldirectories.get(i));
			
					   Scraping s = new Scraping(BASE_URL + urldirectories.get(i));
			
					   Map<String,String> prop = new HashMap<String,String>();
					   prop.put("href", "0");
					   prop.put("aria-label", "-6");
						   
					   urldirectories.addAll(
								addIfNot(
										filterDirectory(s.findAllProprety(find,prop))
										,urlAccessed
										));
						
					   urlFiles.addAll(
								addIfNot(
										filterFiles(s.findAllProprety(find,prop))
										,urlFiles
										));
						i++;
				 };
		 
			} catch (FileNotFoundException e) {
				System.out.println("Repository not find");
			}
		 
		 return processFiles(nameRepository, urlFiles);
	 }
	
	 private static List<Category> processFiles(String nameRepository, 	ArrayList<String> urlFiles) throws Exception {
		 System.out.println("---Files ---");
		 System.out.println("Qtd Files: "+ urlFiles.size());
		 System.out.print("nameRepository: "+ nameRepository);
		 
		 CategoryRepository categoryRepository = new CategoryRepository();
		 for(String urlFile: urlFiles) {
			 	
				Files files = new Files();
				files.setUrl(BASE_URL+urlFile);
				files.setNameRepository(nameRepository);
				files.setNameFile(urlFile);
				files.setType(getType(getName(urlFile)));
				
				ScrapingGitHubFiles s = new ScrapingGitHubFiles(files); 
				categoryRepository.addCategory(s.getFiles(), s.getBitsLine());
			
		 }
		 bd.put(nameRepository, categoryRepository.getListCategories());
		 inProcess.remove(nameRepository);
		 finalized.add(nameRepository);
		 
		 return bd.get(nameRepository);
	 }	 
	
	 private static ArrayList<String> addIfNot(ArrayList<String> theOld, ArrayList<String> exists){
		 ArrayList<String> theNew = new ArrayList<String>();
		 for(String o : theOld ) {
		 	if(!exists.contains(o)) {
		 		theNew.add(o);
		 		}
		 }
		 return theNew;
	 }
	
	 private static ArrayList<String> filterDirectory(ArrayList<String[]> links) {
		 ArrayList<String> d = new ArrayList<String>();		 
		 
		 for(String[] l: links) {
			if("Directory".equals(l[1])) {
				d.add(l[0]);
			}	
		 }
		return d;
	 }
	
	 private static ArrayList<String> filterFiles(ArrayList<String[]> links) {
		 ArrayList<String> d = new ArrayList<String>();	 
		 for(String[] l: links) {
			if(!"Directory".equals(l[1])) {
				d.add(l[0]);
			}
		 }
		return d;
	 }
	 
	 private static String getType(String name) {
		 String type = "";
		 if(name.lastIndexOf(".")>0) {
			 type = name.substring(name.lastIndexOf("."), name.length());
			}else {
			type = name.substring(1, name.length());
			}
		 
		 return type;
	 }
	 
	 private static String getName(String URLF) {
		 return URLF.substring(URLF.lastIndexOf("/"), URLF.length());
	 }
	 

}

