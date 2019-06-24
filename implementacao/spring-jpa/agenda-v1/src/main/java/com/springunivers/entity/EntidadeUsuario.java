package com.springunivers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class EntidadeUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private  int id;
	@Column(nullable = false,length = 10)
	private String login;
	@Column(nullable = false,length = 5)
	private String senha;
	@JoinColumn(name = "tipo_usuario")
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

}
