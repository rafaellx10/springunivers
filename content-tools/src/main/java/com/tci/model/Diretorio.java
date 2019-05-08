package com.tci.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diretorio {
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
	private List<String> imagens;
	public List<String> getImagens() {
		return imagens;
	}
	public void addImagem(String imagem) {
		imagens.add(imagem);
	}
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
	public String getNome() {
		return nome;
	}
	public Diretorio(String nome) {
		super();
		this.nome = nome;
		this.endereco=new File(nome);
		this.imagens = new ArrayList<String>();
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diretorio other = (Diretorio) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
