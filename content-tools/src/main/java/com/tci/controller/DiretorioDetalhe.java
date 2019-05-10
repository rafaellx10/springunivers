package com.tci.controller;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DiretorioDetalhe {
	private static final Logger LOGGER = LogManager.getLogger(DiretorioDetalhe.class);
	public String contemOcrZip(String diretorio) {
		
		File ocr = new File(diretorio,"OCR.zip");
		String scan = diretorio+";"+ (ocr.exists()?"S" + (ocr.length()==0?" 0kb":""):"N");
		if(ocr.exists() && ocr.length()==0) {
			ocr.delete();
			LOGGER.warn("<REMOVENDO OCR> " + ocr.getAbsolutePath() + " " + ocr.length() + "Kb");
		}
		return scan ;
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
