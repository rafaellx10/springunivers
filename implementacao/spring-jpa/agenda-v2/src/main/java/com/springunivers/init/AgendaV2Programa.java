package com.springunivers.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.dao.ContatoDao;

@Component
public class AgendaV2Programa {
	@Autowired
	private ContatoDao dao;
	public void buscarContato() {
		System.out.println(dao.buscar(1));
	}
}
