package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.init.AgendaV2Programa;

@SpringBootApplication
public class AgendaV2Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV2Application.class, args);
	}
	@Bean
	public CommandLineRunner run(AgendaV2Programa programa) {
		return args -> {
			programa.buscarContato();
			programa.atualizarUltimaCompra();
		};
	}
	
}
