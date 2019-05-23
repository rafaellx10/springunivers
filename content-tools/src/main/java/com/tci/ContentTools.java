package com.tci;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tci.desktop.Desktop;

@SpringBootApplication
@EnableScheduling
public class ContentTools {
	private static final Logger LOGGER = LogManager.getLogger(ContentTools.class);
	private static ConfigurableApplicationContext contexto;
	public static void main(String[] args) {
		try {
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			SpringApplicationBuilder builder = new SpringApplicationBuilder(ContentTools.class);
			builder.headless(false);
			contexto = builder.run(args);
			Desktop main = getBean(Desktop.class);
			main.exibir();
			LOGGER.info("Bem vindo");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public static <T> T getBean(Class classe) {
		return (T) contexto.getBean(classe);
	}
}
