package open.digytal.webapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import open.digytal.webapi.model.cadastro.Cidade;
import open.digytal.webapi.model.cadastro.Contato;


@Repository
public class ContatoDao {
	@PersistenceContext
	private EntityManager em;
	@Transactional
	public void inserirContato(Contato contato) {
		em.persist(contato);
	}
	@Transactional
	public void inserirCidade(Cidade cidade) {
		em.persist(cidade);
	}
	public Cidade buscarCidade(Integer id) {
		Cidade cidade = em.find(Cidade.class, id);
		return cidade;
	}
	//listar todos
	public List<Cidade> listarCidades() {
		Query query = em.createQuery("SELECT c FROM Cidade c ORDER BY c.nome");
		return query.getResultList();
	}
	
}
