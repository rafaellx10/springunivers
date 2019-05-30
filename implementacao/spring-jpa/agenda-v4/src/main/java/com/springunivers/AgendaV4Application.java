package com.springunivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springunivers.dao.ContatoDao;
import com.springunivers.model.map1.Cidade;
import com.springunivers.model.map1.Contato;
import com.springunivers.model.map1.Telefone;
import com.springunivers.model.map1.TelefoneTipo;

@SpringBootApplication
public class AgendaV4Application {
	public static void main(String[] args) {
		SpringApplication.run(AgendaV4Application.class, args);
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
				tel.setTipo(TelefoneTipo.CEL);
				contato.addTelefone(tel);
				
				contato.setCidade(cidade);
				dao.inserirContato(contato);
			}else {
				System.out.println("Cidade não localizada, realizando a inseração ... ");
				cidade = new Cidade();
				cidade.setId(CIDADE_ID);
				cidade.setNome("SÃO PAULO");
				cidade.setEstado("SP");
				dao.inserirCidade(cidade);
			}
			
		};
	}
	
}
