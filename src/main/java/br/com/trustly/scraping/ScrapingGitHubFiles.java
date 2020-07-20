package br.com.trustly.scraping;

import java.io.IOException;
import br.com.trustly.model.Files;

/**
 * Class with specific definitions of Github pages and uses scrapping to find the desired data
 * Receive file object, access url and return bytes and lines if any
 * @author Leonardo Patrick
 *
 */
public class ScrapingGitHubFiles extends Scraping {
	private int avance=0;
	private Files files;
	private String bitsLine;
	
	/**
	 * Cascades the three methods of the class
	 * From a Files object
	 * @param files
	 * @throws Exception
	 */
	public ScrapingGitHubFiles(Files files) throws Exception {
		super(files.getUrl());
		this.files=files;
		
		avanceIfExcutavel();
	}
	/**
	 * Checks if it is an executable file, 
	 * because if it is the line of total bytes and total lines changes in the html excerpt
	 * @throws IOException
	 */
	private void avanceIfExcutavel() throws IOException {
		
		for(String l : findLine("file-mode",0)) {
			if(l.indexOf("executable file")>0) {
				System.out.println("É executavel");
				avance=2;
			}
		}
		avanceSetLine();
	}
	/**
	 * Returns the line amount of the file
	 * @throws IOException
	 */
	private void avanceSetLine() throws IOException {
		for(String l : findLine("flex-md-order-1",2+avance)) {
			
			if(l.indexOf("lines")>0) {
			System.out.println("Lines: "+l.trim().substring(0,l.trim().indexOf(" ")));
			files.setLinhas(l.trim().substring(0,l.trim().indexOf(" ")));
			//temLine = Boolean.TRUE;
			}else {
				avance=avance-2;
				files.setLinhas("0");
			}
		}
		avanceSetBytes();
	}
	/**
	 * Returns the file size
	 * @throws IOException
	 */
	private void avanceSetBytes() throws IOException {
		for(String l : findLine("flex-md-order-1",4+avance)) {
			System.out.println("Size: "+l.trim());
			files.setBytes(l.trim());
			bitsLine=l.trim();
		}
	}
	
	/**
	 * Returns the file size in text
	 * @return
	 */
	public String getBitsLine() {
		return bitsLine;
	}
	
	/**
	 * Returns the file
	 * @return
	 */
	public Files getFiles() {
		return files;
	}
	
}
