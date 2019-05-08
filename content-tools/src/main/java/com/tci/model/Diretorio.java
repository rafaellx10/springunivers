package com.tci.model;

import java.io.File;
import java.util.Date;

public class Diretorio {
	private Integer id;
	private String nome;
	private double kb;
	private double mb;
	private double gb;
	private double kbNew;
	private double mbNew;
	private double gbNew;
	private int total;
	private File endereco;
	private Date inicio;
	private Date fim;
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Date getFim() {
		return fim;
	}
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Diretorio(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco=new File(nome);
	}
	public File getEndereco() {
		return endereco;
	}
	public double getKb() {
		return kb;
	}
	public void setKb(double kb) {
		this.kb = kb;
	}
	public double getMb() {
		return mb;
	}
	public void setMb(double mb) {
		this.mb = mb;
	}
	public double getGb() {
		return gb;
	}
	public void setGb(double gb) {
		this.gb = gb;
	}
	public double getKbNew() {
		return kbNew;
	}
	public void setKbNew(double kbNew) {
		this.kbNew = kbNew;
	}
	public double getMbNew() {
		return mbNew;
	}
	public void setMbNew(double mbNew) {
		this.mbNew = mbNew;
	}
	public double getGbNew() {
		return gbNew;
	}
	public void setGbNew(double gbNew) {
		this.gbNew = gbNew;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
