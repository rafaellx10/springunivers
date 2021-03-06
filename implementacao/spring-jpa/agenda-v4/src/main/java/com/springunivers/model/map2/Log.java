package com.springunivers.model.map2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Log {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao",nullable = false)
	private Date dataInclusao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao",nullable = true)
	private Date dataAlteracao;
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	
	
}
