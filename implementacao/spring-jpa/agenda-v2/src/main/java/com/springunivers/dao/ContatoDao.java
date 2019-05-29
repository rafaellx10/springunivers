package com.springunivers.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.springunivers.model.heranca.mappedsuperclass.Contato;

@Repository
public class ContatoDao {
	@PersistenceContext
	private EntityManager em;
	
	public Contato buscar(Integer id) {
		return em.find(Contato.class, id);
	}
}
