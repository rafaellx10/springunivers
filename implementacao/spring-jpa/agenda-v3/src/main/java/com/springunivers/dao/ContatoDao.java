package com.springunivers.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springunivers.model.Contato;


@Repository
public class ContatoDao {
	@PersistenceContext
	private EntityManager em;
	@Transactional
	public void buscarOuInserirContato(Integer id) {
		Contato contato = em.find(Contato.class, id);
		if(contato==null) {
			contato = new Contato();
			contato.setNome("GLEYSON");
			contato.setSobrenome("SAMPAIO");
			contato.setDdd(11);
			contato.setNumero(978786514L);
			
			em.persist(contato);
		}else {
			System.out.println(contato.getId() + " " + contato.getNome() + " " + contato.getSobrenome());
		}
	}
	
	
}
