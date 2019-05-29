package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.init.AgendaV3Programa;

@SpringBootApplication
public class AgendaV3Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV3Application.class, args);
	}
	@Bean
	public CommandLineRunner run(AgendaV3Programa programa) {
		return args -> {
			programa.buscarOuInserirContato();
		};
	}
	
}
