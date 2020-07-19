package br.com.trustly.scraping;

import java.io.IOException;
import java.net.URL;

public class ScrapingTest {
	public ScrapingTest (String url) throws Exception {
		this(new URL(url));
	}
	public ScrapingTest (URL url) throws Exception{
		
		 System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		 int limit = 0;
		 Boolean desconected = Boolean.FALSE;
		do {
			 desconected = Boolean.FALSE;
				try {
					  limit ++;
					  url.openStream();
					 
					 } catch (IOException e) {
						 System.out.println("Could not connect  " + url.getPath());
						 if(limit>3)
							 throw new Exception("Could not connect  " + url.getPath());
					}
		} while(desconected);
	  
	}
		
}