package com.springunivers.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
	public Contato findById(Integer id) {
		return em.find(Contato.class, id);
	}
	//buscarPorNome - Somente UM REGISTRO
	public Contato findOne(String campo, Object valor) {
		Contato contato = null;
		Query query = em.createQuery("SELECT c FROM Contato c WHERE c." + campo+ " = :filtro");
		query.setParameter("filtro", valor);
		try {
			contato = (Contato) query.getSingleResult();
		}catch (NonUniqueResultException | NoResultException e) {
			e.printStackTrace();
		}
		return contato;
	}
	//listar todos
	public List<Contato> findAll() {
		Query query = em.createQuery("SELECT c FROM Contato c");
		return query.getResultList();
	}
	//listar por nome
	public List<Contato> findByNome(String nome) {
		Query query = em.createQuery("SELECT c FROM Contato c WHERE c.nome = :nome");
		query.setParameter("nome", nome);
		return query.getResultList();
	}
	//listar por sobrenome
	public List<Contato> findBySobrenome(String sobrenome) {
		Query query = em.createQuery("SELECT c FROM Contato c WHERE c.sobrenome = :sobrenome");
		query.setParameter("sobrenome", sobrenome);
		return query.getResultList();
	}
	//listar por nome LIKE
	public List<Contato> findByNomeContaining(String nome) {
		Query query = em.createQuery("SELECT c FROM Contato c WHERE c.nome LIKE :nome");
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
	//listar por nome LIKE
	public List<Contato> findBySobrenomeContaining(String sobrenome) {
		Query query = em.createQuery("SELECT c FROM Contato c WHERE c.sobrenome LIKE :sobrenome");
		query.setParameter("sobrenome", "%" + sobrenome + "%");
		return query.getResultList();
	}
	//concatenar - JPA Funcations
	//http://www.java2s.com/Tutorials/Java/JPA/4520__JPA_Query_Function.htm
	public String getNomeComTelefone(Integer id) {
		Query query = em.createQuery("SELECT CONCAT(c.nome,' - ' ,c.numero) FROM Contato c WHERE c.id=:id");
		query.setParameter("id", id);
		try {
			return (String) query.getSingleResult();
		}catch (NonUniqueResultException | NoResultException e) {
			e.printStackTrace();
		}
		return "";
	}
	//API CRITERIA
	//http://blog.werneckpaiva.com.br/2012/01/queries-com-a-nova-jpa2-criteria-api/
	public List<Contato> findAllCriteria(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
		Root<Contato> from = query.from(Contato.class);
		CriteriaQuery<Contato> select = query.select(from);
		 
		TypedQuery<Contato> typedQuery = em.createQuery(select);
		List<Contato> results = typedQuery.getResultList();
		return results;
	}
	
}
