package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.dao.ContatoDao;
import com.springunivers.model.Cidade;
import com.springunivers.model.Contato;
import com.springunivers.model.Telefone;

@SpringBootApplication
public class AgendaV3Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV3Application.class, args);
	}
	@Bean
	public CommandLineRunner run(ContatoDao dao) {
		return args -> {
			int CIDADE_ID=3550308;
			Cidade cidade = dao.buscarCidade(CIDADE_ID);
			if(cidade!=null) {
				Contato contato = new Contato();
				contato = new Contato();
				contato.setNome("GLEYSON");
				contato.setSobrenome("SAMPAIO");
				
				Telefone tel = new Telefone();
				tel.setDdd(11);
				tel.setNumero(978786514L);
				contato.setTelefone(tel);
				
				contato.setCidade(cidade);
				dao.inserirContato(contato);
			}else {
				System.out.println("Cidade não localizada, realizando a inseração ");
				dao.inserirCidade(cidade);
			}
			
		};
	}
	
}
