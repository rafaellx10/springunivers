package br.com.projeto.service;

import java.io.Serializable;

import br.com.projeto.entity.EntidadeRegistroAula;


public interface AutoServiceRegistroAula {
	void salvar(EntidadeRegistroAula aulas);
	void editar(EntidadeRegistroAula aulas);
	void excluir(Integer id);
	EntidadeRegistroAula buscarId(Integer id);
	Iterable<EntidadeRegistroAula> buscarTodos();

}
