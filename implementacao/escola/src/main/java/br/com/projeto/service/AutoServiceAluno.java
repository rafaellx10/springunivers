package br.com.projeto.service;

import java.io.Serializable;

import br.com.projeto.entity.EtidadeAluno;

public interface AutoServiceAluno  {
	void salvar(EtidadeAluno aluno);
	void editar(EtidadeAluno aluno);
	void excluir(Integer id);
	EtidadeAluno buscarId(Integer id);
	Iterable<EtidadeAluno> buscarTodos();
	
	
}
