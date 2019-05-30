package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.init.AgendaV5Programa;

@SpringBootApplication
public class AgendaV5Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV5Application.class, args);
	}
	@Bean
	public CommandLineRunner run(AgendaV5Programa programa) {
		return args -> {
			programa.inserirContatoComAmigo();
		};
	}
	
}
