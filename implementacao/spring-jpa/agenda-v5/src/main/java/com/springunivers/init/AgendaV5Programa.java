package com.springunivers.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.dao.ContatoDao;

@Component
public class AgendaV5Programa {
	@Autowired
	private ContatoDao dao;
	private final int ID=5;
	public void inserirContatoComAmigo() {
		dao.inserirContatoComAmigo(ID);
	}
	
}
