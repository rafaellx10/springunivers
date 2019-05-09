package com.tci.controller;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class DiretorioDetalhe {
	public String contemOcrZip(String diretorio) {
		File ocr = new File(diretorio,"OCR.zip");
		String scan = diretorio+";"+ (ocr.exists()?"S" + (ocr.length()==0?" 0kb":""):"N");
		return scan ;
	}
	public String csvVersusDiretorio(String diretorio, String imagem) {
		File img = new File(diretorio,imagem); 
		boolean existDir=img.exists();
		String linha = img.getAbsolutePath()+";" + (existDir?"S":"N") + ";|N|";
		linha=linha.replace("|N|", dirVersusCsv(diretorio, imagem));
		return linha;
	}
	public String dirVersusCsv(String diretorio, String imagem) {
		String[] imagens = new File(diretorio).list();
		boolean exists = false;
		for(String nome: imagens) {
			if(exists=nome.equals(imagem))
				break;
		}
		return (exists?"S":"N");
	}
}
