package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.start.ContentScanPrograma;

@SpringBootApplication
public class ContentScanApplication {
	public static void main(String[] args) {
		System.setProperty("app.path", ContentScanPrograma.APP_PATH);
		System.setProperty("app.log", "app");
		SpringApplication.run(ContentScanApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(ContentScanPrograma programa) {
		return args -> {
			programa.executar();
		};
	}
}
