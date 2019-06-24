package com.springunivers.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AULAS")
public class EntidadeRegistroAula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;
	
	// @JoinColumn e @Column nao podem ser juntos, ou um ou outro
	// como aluno é uma outra entidade, então é JoinColumn
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private EtidadeAluno aluno;
	
	// Não se usa mais temporal com os tipos do pacote java.time.*, só usa com java.util.Date
	// @Temporal(TemporalType.DATE)
	private LocalDate dataAula;
	
	private String resumoDia;
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public EtidadeAluno getAluno() {
		return aluno;
	}
	public void setAluno(EtidadeAluno aluno) {
		this.aluno = aluno;
	}
	public LocalDate getDataAula() {
		return dataAula;
	}
	public void setDataAula(LocalDate dataAula) {
		this.dataAula = dataAula;
	}
	public String getResumoDia() {
		return resumoDia;
	}
	public void setResumoDia(String resumoDia) {
		this.resumoDia = resumoDia;
	}
	
	

}
