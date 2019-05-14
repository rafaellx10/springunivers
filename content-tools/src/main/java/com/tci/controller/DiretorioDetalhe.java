package com.tci.controller;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tci.model.Diretorio;

@Component
public class DiretorioDetalhe {
	@Autowired
	private ArquivoDetalhe arquivoDetalhe;
	private static final Logger LOGGER = LogManager.getLogger(DiretorioDetalhe.class);
	public String processaOcrZip(String diretorio) {
		Diretorio ocr = contemOcrZip(new Diretorio(diretorio));
		String scan = diretorio+";"+ocr.getContemOcrZip();
		if(ocr.getContemOcrZip().equals("S0kb")) {
			File file = new File(ocr.getNome(),"OCR.zip");
			file.delete();
			LOGGER.warn("<REMOVENDO OCR> " + file.getAbsolutePath() + " " + file.length() + "bytes");
			scan=scan + "\n" + "<REMOVENDO OCR> " + file.getAbsolutePath() + " " + file.length() + "bytes";
		}
		return scan ;
	}
	public String contemOcrZipTxtHocr(boolean arvore,String diretorio) {
		Diretorio dir = contemOcrZip(new Diretorio(diretorio));
		StringBuilder csv = new StringBuilder(dir.getNome()+";"+dir.getContemOcrZip());
		File[] files = dir.getEndereco().listFiles();
		try {
		for(File file: files) {
			if(arquivoDetalhe.isTif(file)) {
				File txt = new File(diretorio, file.getName().replaceAll("tif", "txt"));
				File hocr = new File(diretorio, file.getName().replaceAll("tif", "hocr"));
				csv.append("\n;;"+file.getParent()+";"+file.getName()+";"+String.format("%.3f",arquivoDetalhe.Mbytes(file.length())) +";"+String.format("%.3f",arquivoDetalhe.Gbytes(file.length())) +";"+ (txt.exists()?"S":"N")+";"+(hocr.exists()?"S":"N"));
			}
		}
		}catch (Exception e) {
			LOGGER.error("ERRO AO TENTAR VALIDAR OS TIPOS txt e hocr no diretório:" + diretorio);
		}
		return csv.toString() ;
	}
	private Diretorio contemOcrZip(Diretorio diretorio) {
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
