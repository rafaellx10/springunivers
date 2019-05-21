package com.springunivers.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springunivers.model.Contato;

@Repository
public class ContatoDao {
	@PersistenceContext
	private EntityManager em;
	@Transactional
	public void incluir(Contato contato) {
		em.persist(contato);
	}
	@Transactional
	public void alterar(Contato contato) {
		em.merge(contato);
	}
	public Contato buscarPorNome(String nome) {
		Contato contato = null;
		Query query = em.createQuery("SELECT c FROM Contato c WHERE c.nome = :nome");
		query.setParameter("nome", nome);
		try {
			contato = (Contato) query.getSingleResult();
		}catch (NonUniqueResultException | NoResultException e) {
			e.printStackTrace();
		}
		return contato;
	}
}
