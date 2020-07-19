package br.com.trustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrustlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrustlyApplication.class, args);
	}
	
    

}
