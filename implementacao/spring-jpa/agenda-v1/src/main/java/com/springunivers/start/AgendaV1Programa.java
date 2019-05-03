package com.springunivers.start;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.dao.ContatoDao;
import com.springunivers.model.Contato;
import com.springunivers.repository.ContatoRepository;

@Component
public class AgendaV1Programa {
	private static final Logger logger = LogManager.getLogger(AgendaV1Programa.class);
	@Autowired
	private ContatoRepository repositroy;
	@Autowired
	private ContatoDao dao;
	private Contato criarContato() {
		Contato contato = new Contato();
		contato.setNome("RAIANE");
		contato.setSobrenome("OLIVEIRA");
		contato.setDdd(11);
		contato.setNumero(455696654L);
		contato.setCidade("PARNAIBA");
		contato.setEstado("PI");
		return contato;
	}
	public void incluirContato() {
		String nome="GLEYSON";
		if(repositroy.findByNome(nome)==null) {
			Contato contato = criarContato();
			contato.setNome(nome);
			repositroy.save(contato);
			System.out.println("INCLUINDO NOVO USUARIO");
		}else {
			System.out.println("JA TEMOS UM USUARIO NA BASE DE DADOS");
		}
		
	}
	public void imprimirContatos(){
		List<Contato> contatos = repositroy.findAll();
		for(Contato contato:contatos) {
			System.out.println(contato);
		}
	}
	public void imprimirContatosOliveira(){
		logger.info("TESTE");
		List<Contato> contatos = repositroy.findBySobrenomeContaining("OLIV");
		for(Contato contato:contatos) {
			System.out.println(contato);
		}
		
	}
}
