package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.model.Contato;
import com.springunivers.start.AgendaV1Programa;

@SpringBootApplication
public class AgendaV1Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV1Application.class, args);
	}
	@Bean
	public CommandLineRunner run(AgendaV1Programa programa) {
		return args -> {
			Contato contato = new Contato();
			contato.setNome("RAIANE");
			contato.setSobrenome("OLIVEIRA");
			contato.setDdd(11);
			contato.setNumero(455696654L);
			contato.setCidade("PARNAIBA");
			contato.setEstado("PI");
			
			programa.salvarContatoDao(contato);
			//programa.incluirContatoRepository(contato);
			//programa.imprimirContatos();
			//programa.imprimirContatosContem("JOSE");
		};
	}
}
