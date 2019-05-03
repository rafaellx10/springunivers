package com.springunivers.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.springunivers.model.Contato;

@Repository
public class ContatoDao {
	@PersistenceContext
	private EntityManager em;
	
	public void incluir(Contato contato) {
		em.persist(contato);
	}
	public void alterar(Contato contato) {
		em.merge(contato);
	}
}
