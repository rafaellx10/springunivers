package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.start.AgendaV1Programa;

@SpringBootApplication
public class AgendaV1Application {
	public static final String ROOT_PATH=System.getProperty("user.dir");
	public static void main(String[] args) {
		System.setProperty("app.home", ROOT_PATH);
		SpringApplication.run(AgendaV1Application.class, args);
	}
	@Bean
	public CommandLineRunner run(AgendaV1Programa programa) {
		return args -> {
			//programa.incluirContato();
			//programa.imprimirContatos();
			programa.imprimirContatosOliveira();
		};
	}
}
