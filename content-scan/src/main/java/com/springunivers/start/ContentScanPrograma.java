package com.springunivers.start;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.converter.Extensao;
import com.springunivers.util.FileReaderUtil;

@Component
public class ContentScanPrograma {
	public static final String APP_PATH = System.getProperty("user.dir");
	private static final Logger LOGGER = LogManager.getLogger(ContentScanPrograma.class);
	private List<String> scanDiretorios;
	@Autowired
	private Extensao extensao;

	public void validarExtensa() {
		File file = new File("C:\\tci\\img\\3130\\1.tif");
		try {
			System.out.println(extensao.extensaoOriginal(file));
			LOGGER.info("FIM");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executar() {
		try {
			File diretorios = new File(APP_PATH, "diretorios.txt");
			if(!diretorios.exists())
				throw new RuntimeException("Não foi localizado o arquivo " + diretorios.getAbsolutePath());
			FileReaderUtil fileReader = new FileReaderUtil();
			scanDiretorios = fileReader.readLine(diretorios);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
}
