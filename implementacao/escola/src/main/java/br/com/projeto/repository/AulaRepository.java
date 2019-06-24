package br.com.projeto.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.entity.EntidadeRegistroAula;

@Repository
public interface AulaRepository extends CrudRepository<EntidadeRegistroAula, Integer>{
	
	

}
