package com.springunivers.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.model.Contato;
import com.springunivers.repository.ContatoRepository;

@Component
public class AgendaV1Programa {
	@Autowired
	private ContatoRepository repositroy;
	public void incluirContato() {
		if(!repositroy.findById(1).isPresent()) {
			Contato contato = new Contato();
			contato.setNome("RAIANE");
			contato.setSobrenome("OLIVEIRA");
			contato.setDdd(11);
			contato.setNumero(455696654L);
			contato.setCidade("PARNAIBA");
			contato.setEstado("PI");
			repositroy.save(contato);
			System.out.println("INCLUINDO NOVO USUARIO");
		}else {
			System.out.println("JA TEMOS UM USUARIO NA BASE DE DADOS");
		}
		
	}
}
