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
	public void salvarContatoDao(Contato contato) {
		/*
		 * Contato find = dao.findByNome(contato.getNome()); if(find!=null) {
		 * contato.setId(find.getId()); dao.alterar(contato); }else
		 * dao.incluir(contato);
		 */
			
	}
	public void salvarContatoRepository(Contato contato) {
		if(repositroy.findByNome(contato.getNome())==null) {
			repositroy.save(contato);
			logger.info("INCLUINDO NOVO USUARIO");
		}else {
			logger.info("JA TEMOS UM USUARIO NA BASE DE DADOS");
		}
		
	}
	public void imprimirContatos(){
		List<Contato> contatos = repositroy.findAll();
		for(Contato contato:contatos) {
			System.out.println(contato);
		}
	}
	public void imprimirContatosContem(String nome){
		logger.info("BUSCANDO CONTATOS QUE CONTEM O NOME " + nome );
		List<Contato> contatos = repositroy.findByNomeContaining(nome);
		for(Contato contato:contatos) {
			System.out.println(contato);
		}
	}
}
