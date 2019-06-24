package com.springunivers.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ALUNO")
public class EtidadeAluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 30,nullable = false)
	private String nome;
	@Column(length = 30,nullable = false)
	private String email;
	@Column(length = 30,nullable = false)
	private String whatzap;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	
	
	public EtidadeAluno(int id, String nome, String email, String whatzap, Date dataVencimento,
			List<EntidadeRegistroAula> aulas) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.whatzap = whatzap;
		this.dataVencimento = dataVencimento;
		
	}
	public EtidadeAluno() {
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWhatzap() {
		return whatzap;
	}
	public void setWhatzap(String whatzap) {
		this.whatzap = whatzap;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


}
