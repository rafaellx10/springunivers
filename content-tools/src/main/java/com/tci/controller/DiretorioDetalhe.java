package com.tci.controller;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class DiretorioDetalhe {
	public String contemOcrZip(String diretorio) {
		File ocr = new File(diretorio,"OCR.zip");
		return ocr.exists() && ocr.length() > 0?"":diretorio;
	}
}
