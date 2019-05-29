package com.springunivers.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.dao.ContatoDao;

@Component
public class AgendaV2Programa {
	@Autowired
	private ContatoDao dao;
	public void buscarContato() {
		dao.buscarOuInserirContato(2);
	}
	public void atualizarUltimaCompra() {
		dao.atualizarUltimaVisitaCliente(2);
	}
}
