package br.com.trustly.scraping;

import java.io.IOException;
import br.com.trustly.model.Files;

public class ScrapingGitHubFiles extends Scraping {
	private int avance=0;
	private Files files;
	private String bitsLine;
	
	public ScrapingGitHubFiles(Files files) throws Exception {
		super(files.getUrl());
		this.files=files;
		
		avanceIfExcutavel();
	}
	private void avanceIfExcutavel() throws IOException {
		
		for(String l : findLine("file-mode",0)) {
			if(l.indexOf("executable file")>0) {
				System.out.println("É executavel");
				avance=2;
			}
		}
		avanceSetLine();
	}
	
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
	private void avanceSetBytes() throws IOException {
		for(String l : findLine("flex-md-order-1",4+avance)) {
			System.out.println("Size: "+l.trim());
			files.setBytes(l.trim());
			bitsLine=l.trim();
		}
	}
	
	public String getBitsLine() {
		return bitsLine;
	}
	public void setBitsLine(String bitsLine) {
		this.bitsLine = bitsLine;
	}
	public Files getFiles() {
		return files;
	}
	
}
