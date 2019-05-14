package com.tci.model;

public class Imagem extends Diretorio {
	private boolean contemTif;
	private boolean contemTxt;
	private boolean contemHocr;
	
	public Imagem(String nome) {
		super(nome);
	}

	public boolean isContemTif() {
		return contemTif;
	}

	public void setContemTif(boolean contemTif) {
		this.contemTif = contemTif;
	}

	public boolean isContemTxt() {
		return contemTxt;
	}

	public void setContemTxt(boolean contemTxt) {
		this.contemTxt = contemTxt;
	}

	public boolean isContemHocr() {
		return contemHocr;
	}

	public void setContemHocr(boolean contemHocr) {
		this.contemHocr = contemHocr;
	}
	
}
