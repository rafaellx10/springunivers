package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.dao.ContatoDao;
import com.springunivers.model.Contato;

@SpringBootApplication
public class AgendaV5Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV5Application.class, args);
	}
	@Bean
	public CommandLineRunner run(ContatoDao dao) {
		return args -> {
			int AMIGO_ID=6;
			Contato amigo = dao.buscarContato(AMIGO_ID);
			if(amigo!=null) {
				Contato contato = new Contato();
				contato = new Contato();
				contato.setNome("GLEYSON");
				contato.setSobrenome("SAMPAIO");
				
				contato.setDdd(11);
				contato.setNumero(978786514L);
				contato.setCidade("SAO PAULO");
				contato.setEstado("SP");
				
				contato.add(amigo);
				
				dao.inserirContato(contato);
			}else {
				System.out.println("Amigo não encontrado, vamos inserir");
				Contato novoAmigo = new Contato();
				novoAmigo = new Contato();
				novoAmigo.setNome("AMIGO");
				novoAmigo.setSobrenome("DO PEITO");
				
				novoAmigo.setCidade("SANTOS");
				novoAmigo.setEstado("SP");
				
				novoAmigo.setDdd(11);
				novoAmigo.setNumero(977871365L);
				
				dao.inserirContato(novoAmigo);
			}
			
		};
	}
	
}
