package com.springunivers.converter;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Conversor {
	private static final Logger LOGGER = LogManager.getLogger(Conversor.class);
	@Autowired
	private ArquivoDetalhe detalhe;
	public void ajustarExtensaoArquivo(File diretorio) throws Exception {
		//System.setProperty("app.log", diretorio.getAbsolutePath().replaceAll("\\\\", "-"));
		File[] arquivos = diretorio.listFiles();
		for (File arquivo : arquivos) {
			if (arquivo.isFile() && extensaoIncorreta(arquivo)) {
				File jpg=getJpgFile(arquivo);
				arquivo.renameTo(jpg);
				LOGGER.info("O arquivo {} {} FOI convertido para {} {} ", arquivo.getName(), detalhe.kb(arquivo), jpg.getName(), detalhe.kb(jpg) );
			}else {
				LOGGER.info("O arquivo {} {} POSSUI a extensão adequada ", arquivo.getName(), detalhe.kb(arquivo));
			}
		}
	}
	private static File getJpgFile(File tif) {
		String nome = tif.getName().replaceAll("\\.tif$", ".jpg");
		File jpeg = new File(tif.getParent(), nome);
		return jpeg;
	}
	public boolean extensaoIncorreta(File arquivo) throws Exception {
		return detalhe.extensaoOriginal(arquivo).toLowerCase().contains("jpeg");
	}
}
