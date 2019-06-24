package com.springunivers.services;

import com.springunivers.entity.EtidadeAluno;

public interface AutoServiceAluno  {
	void salvar(EtidadeAluno aluno);
	void editar(EtidadeAluno aluno);
	void excluir(Integer id);
	EtidadeAluno buscarId(Integer id);
	Iterable<EtidadeAluno> buscarTodos();
	
	
}
