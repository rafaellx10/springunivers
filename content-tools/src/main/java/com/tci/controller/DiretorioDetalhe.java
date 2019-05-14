package com.tci.controller;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tci.model.Diretorio;

@Component
public class DiretorioDetalhe {
	private static final Logger LOGGER = LogManager.getLogger(DiretorioDetalhe.class);
	public String contemOcrZip(String diretorio) {
		Diretorio ocr = contemOcrZip(new Diretorio(diretorio));
		String scan = diretorio+";"+ocr.getContemOcrZip();
		if(ocr.getContemOcrZip().equals("S0kb")) {
			File file = new File(ocr.getNome(),"OCR.zip");
			LOGGER.warn("<REMOVENDO OCR> " + file.getAbsolutePath() + " " + file.length() + "bytes");
			scan=scan + "\n" + "<REMOVENDO OCR> " + file.getAbsolutePath() + " " + file.length() + "bytes";
		}
		return scan ;
	}
	public Diretorio contemOcrZip(Diretorio diretorio) {
		File ocr = new File(diretorio.getNome(),"OCR.zip");
		diretorio.setContemOcrZip((ocr.exists()?"S" + (ocr.length()<=22?"0bytes":""):"N")); //22bytes=zip vazio
		return diretorio ;
	}
	public String csvVersusDiretorio(String diretorio, String imagem) {
		if(imagem.contains("pdf")) {
			File img = new File(diretorio,imagem); 
			boolean existDir=img.exists();
			imagem = img.getAbsolutePath()+";" + (existDir?"S":"N") + "; ;";
		}
		return imagem;
	}
	
}
