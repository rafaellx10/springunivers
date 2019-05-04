package com.springunivers.start;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.converter.Conversor;
import com.springunivers.model.Diretorio;
import com.springunivers.util.FileReaderUtil;

@Component
public class ContentScanPrograma {
	public static final String APP_PATH = System.getProperty("user.dir");
	private static final Logger LOGGER = LogManager.getLogger(ContentScanPrograma.class);
	@Autowired
	private Conversor conversor;
	public void executar() {
		try {
			File diretorios = new File(APP_PATH, "diretorios.txt");
			if(!diretorios.exists())
				throw new RuntimeException("Não foi localizado o arquivo " + diretorios.getAbsolutePath());
			FileReaderUtil fileReader = new FileReaderUtil();
			List<String> scanDiretorios = fileReader.readLine(diretorios);
			int id=1;
			for(String diretorio: scanDiretorios) {
				conversor.converter(new Diretorio(id++,diretorio));
			}
			LOGGER.info("fim");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
}
