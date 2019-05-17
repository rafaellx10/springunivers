package com.tci.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileWritterUtil {
	public static final String DIRETORIO_OCR_TXT_HOCR_TIFEERO="DIRETORIO_OCR_TXT_HOCR_TIFEERO.csv";
	public void writerHeader(String nomeArquivo, String cabecalho) throws Exception{
		writer(nomeArquivo, cabecalho, "");
	}
	public void writer(String nomeArquivo, String conteudo) throws Exception{
		writer(nomeArquivo, null, conteudo);
	}
	public void writer(String nomeArquivo,String cabecalho, String conteudo) throws Exception{
		File file = new File(System.getProperty("user.dir"),nomeArquivo);
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file, true);
		try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
			if(cabecalho!=null)
				printWriter.print(cabecalho+"\n");
			printWriter.print(conteudo);
			printWriter.close();
		}
	}
	
}
