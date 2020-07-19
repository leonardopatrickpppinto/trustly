package br.com.trustly.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxWebConfiguration {                                    
	    @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .apiInfo(apiInfo())
	          .select()                                  
	          .apis(
	        		  RequestHandlerSelectors.basePackage("br.com.trustly.resources")
	        		 )             
	          .paths(PathSelectors.any())                          
	          .build();
	    }

	    private ApiInfo apiInfo() {
		    return new ApiInfo(
		      "List Files Repository GitHub", 
		      "API that returns the total number of lines and the total number of bytes of all the files of a given public Github repository, grouped by file extension.", 
		      "API", 
		      "For trustly", 
		      new Contact("Leonardo Patrick", "https://www.linkedin.com/in/leonardopatrickppinto/", "leonardop@itout.com.br"), 
		      "", "", Collections.emptyList());
		}
	}