package com.tci.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileWritterUtil {
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
				printWriter.print(cabecalho);
			printWriter.print(conteudo);
			printWriter.close();
		}
	}
}
