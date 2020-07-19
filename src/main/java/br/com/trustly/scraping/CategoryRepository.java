package br.com.trustly.scraping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.trustly.model.Category;
import br.com.trustly.model.Files;

/**
 * Responsible for storing categories
 * @author Leonardo Patrick
 *
 */
public class CategoryRepository {
	
	private Map<String, Category> categories = new HashMap<String, Category>();
	/**
	 * Receive the object file and bits in string by adding the category to the repository or updating
	 * @param file
	 * @param bitsLine
	 */
	public  void addCategory(Files file, String bitsLine) {
		addCategory(
				file.getNameRepository(),
				file.getType(), 
				getBytesProcess(bitsLine), 
				file,
				Integer.valueOf(file.getLinhas()));
	}
	
	public  void addCategory(String nameRepository, String type, Double bytes,Files file, Integer linhas) {
		
		 if(categories.containsKey(type)){
			 Category category = categories.get(type);
			 category.setBytes(category.getBytes() + bytes);
			 category.setQtd(category.getQtd() +1);
			 category.setLinhas(category.getLinhas() + linhas);
			 category.setNameRepository(nameRepository);
			 
			 List<Files> files = categories.get(type).getFiles();
			 files.add(file);
			 category.setFiles(files);
			 categories.replace(type, category);
		
		 }else {
			Category category = new Category();
			category.setType(type);
			
			ArrayList<Files> f= new ArrayList<Files>();
			f.add(file);
			
			category.setFiles(f);
			category.setBytes(bytes);
			category.setLinhas(linhas);
			category.setQtd(1);
			categories.put(type, category);
		}
	}		 
	
	public List<Category> getListCategories() {
		List<Category> category = new ArrayList<Category>();
		
		for(String type : categories.keySet()) {
			category.add(categories.get(type));
		}
		
		return category;
	}
	
	 private static Double getBytesProcess(String l ) {
		 Double size = 0.0D;
		 
		 if(l!=null) {
			 
		
			if(l.trim().contains("KB")) {
				size = new Double(l.trim().replace(" KB", ""))*1024;
				}else if(l.trim().contains("Bytes")){
					size = new Double(l.trim().replace(" Bytes", ""));
				}else if(l.trim().contains("MB")) {
					size = new Double(l.trim().replace(" MB", ""))*1024*1024;
				}else if(l.trim().contains("GB")) {
					size = new Double(l.trim().replace(" GB", ""))*1024*1024*1024;
				}else{
					size = 0.0D;
				};
				
		 }else{
			 size = 0.0D;
		 }
	 return size;
	 }

}
