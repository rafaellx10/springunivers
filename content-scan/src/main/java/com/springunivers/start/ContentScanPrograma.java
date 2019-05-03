package com.springunivers.start;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springunivers.converter.Extensao;

@Component
public class ContentScanPrograma {
	private static final Logger logger = LogManager.getLogger(ContentScanPrograma.class);
	@Autowired
	private Extensao extensao;
	public void validarExtensa() {
		File file = new File("C:\\tci\\img\\3130\\1.tif");
		try {
			System.out.println(extensao.extensaoOriginal(file));
			logger.info("FIM");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
