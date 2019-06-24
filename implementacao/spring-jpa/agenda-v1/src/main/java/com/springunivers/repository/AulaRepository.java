package com.springunivers.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springunivers.entity.EntidadeRegistroAula;

@Repository
public interface AulaRepository extends CrudRepository<EntidadeRegistroAula, Integer>{
	
	

}
