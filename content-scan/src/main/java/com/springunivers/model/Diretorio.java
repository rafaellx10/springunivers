package com.springunivers.model;

public class Diretorio {
	private Integer id;
	private String nome;
	private double kb;
	private double mb;
	private double gb;
	private double kbNew;
	private double mbNew;
	private double gbNew;
	
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
	
}
