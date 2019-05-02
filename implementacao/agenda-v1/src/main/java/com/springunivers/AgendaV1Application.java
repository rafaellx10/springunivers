package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.start.AgendaV1Programa;

@SpringBootApplication
public class AgendaV1Application {

	public static void main(String[] args) {
		SpringApplication.run(AgendaV1Application.class, args);
	}
	@Bean
	public CommandLineRunner run(AgendaV1Programa bean) {
		return args -> {
			bean.incluirContato();
		};
	}
}
