package com.springunivers.services;

import com.springunivers.entity.EntidadeRegistroAula;


public interface AutoServiceRegistroAula {
	void salvar(EntidadeRegistroAula aulas);
	void editar(EntidadeRegistroAula aulas);
	void excluir(Integer id);
	EntidadeRegistroAula buscarId(Integer id);
	Iterable<EntidadeRegistroAula> buscarTodos();

}
