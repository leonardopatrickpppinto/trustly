package br.com.trustly.scraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * Connects the Url to process using scrapping method
 * and process the line and properties found in the html code
 * @author Leonardo Patrick
 *
 */
public class Scraping {
	private ArrayList<String> file = new ArrayList<String>();
	
	public Scraping (String url) throws Exception {
		this(new URL(url));
	}
	
	/**
	 * Make endless connection attempts until the connection is successful
	 * @param url
	 * @throws Exception
	 */
	public Scraping (URL url) throws Exception{
		 System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		 InputStream is = null;
		 Boolean desconected = Boolean.FALSE;
		do {
			 desconected = Boolean.FALSE;
				try {
					  is = url.openStream();
					 
					 InputStreamReader isr = new InputStreamReader(is); 
					 BufferedReader br = new BufferedReader(isr); 
					 
					 String linha = br.readLine();  
					 while(linha!=null) {
						 file.add(linha);
						 linha = br.readLine(); 
					 }
					 
					 } catch (IOException e) {
						 desconected = Boolean.TRUE;
						 file = new ArrayList<String>();
						 System.out.println("Could not connect  " + url.getPath());
						 TimeUnit.SECONDS.sleep(1);
					}
		} while(desconected);
	  
	}
	
	/**
	 * Finds an html attribute in the line of the searched html excerpt
	 * @param find
	 * @param proprety
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String[]> findProprety(String find, String proprety) throws IOException {
		return findProprety(find, proprety, 0);
	}
	
	/**
	 * Finds an html attribute on the line or after the number of lines, 
	 * considering the html segment sought
	 * @param find
	 * @param proprety
	 * @param advance
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String[]> findProprety(String find, String proprety, int advance) throws IOException {
		
		ArrayList<String[]> ret = new ArrayList<String[]>();
		int i = 0;
		 for (String line : file) {
		 if(line.indexOf(find)>0) {
			 
			 String lineFind= file.get(i+advance)
					 			.substring(file.get(i+advance)
					 					.indexOf(proprety), file.get(i+advance).length());
			 
			 String l = lineFind.substring(
					    lineFind.indexOf(proprety+"=\"")+proprety.length()+2,
					    lineFind.length());
			 
			
			ret.add(new String[] {l.substring(0,l.indexOf("\""))}
			);
		} 
		 i++;
	  }
		 return ret;
	}
	
	/**
	 * Finds an html All attribute on the line or after the number of lines, 
	 * considering the html segment sought
	 * The received map must contain the number of lines
	 * that will go forward or backward to find a certain attribute
	 * @param find
	 * @param props
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String[]> findAllProprety(String find, Map<String, String> props) throws IOException {
		
		 ArrayList<String[]> ret = new ArrayList<String[]>();
		 int i = 0;
		 for (String line : file) {
		 if(line.indexOf(find)>0) {
			 
			String[] value = new String[props.size()];
			 
			int j=0;
			for(String key : props.keySet()) {
				
			String proprety = key;
			int advance = Integer.parseInt(props.get(key));
			String lineFind= file.get(i+advance)
					 			.substring(file.get(i+advance)
					 					.indexOf(proprety), file.get(i+advance).length());
			 
			 String l = lineFind.substring(
					    lineFind.indexOf(proprety+"=\"")+proprety.length()+2,
					    lineFind.length());
						 
			 value[j] =l.substring(0,l.indexOf("\""));
			 j++;
			}
			 ret.add(value);
		 } 
		 i++;
	  }
		 return ret;
	}
	
	/**
	 * Search html code line using the html excerpt passed as a parameter
	 * @param find
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> findLine(String find) throws IOException{
		return findLine(find,0);
	}
	
	/**
	 * Searches for a line from a html segment, 
	 * advances or rewinds the number of lines entered in the parameter and sets this line
	 * @param find
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> findLine(String find, int advance) throws IOException {
		ArrayList<String> ret = new ArrayList<String>();
		 int i=0;
		 
		 for (String line : file) {
     		 if(line.indexOf(find)>0) {
			 ret.add(file.get(i+advance));
		 	}
		 	i++;
		 }
		 return ret;
	}
		
}