package com.springunivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springunivers.entity.EntidadeRegistroAula;
import com.springunivers.repository.AulaRepository;

@Service
@Transactional
public class ServiceImplementaRegistroAula implements AutoServiceRegistroAula{
	
	
	@Autowired
	private AulaRepository repository;

	@Override
	public void salvar(EntidadeRegistroAula aulas) {
		repository.save(aulas);
		
		
	}

	@Override
	public void editar(EntidadeRegistroAula aulas) {
		
		
	}

	@Override
	public void excluir(Integer id) {
		repository.deleteById(id);
		
		
	}

	@Override
	public EntidadeRegistroAula buscarId(Integer id) {
		repository.findById(id);
		return null;
	}

	@Override
	public Iterable<EntidadeRegistroAula> buscarTodos() {
		return repository.findAll();
		
		
	}
	

	
	
 
}
