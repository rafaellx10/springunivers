package com.tci.beans;

import org.apache.tika.Tika;
import org.apache.tika.detect.TypeDetector;
import org.apache.tika.mime.MimeTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {
	@Bean(name = "default")
	public Tika defaultTika() {
		return new Tika();
	} 
	@Bean(name = "mime")
	public Tika mimeTika(){
		return  new Tika(new MimeTypes());
	}
	@Bean(name = "type")
	public Tika typeTika() {
		return new Tika(new TypeDetector());
	}
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
}
