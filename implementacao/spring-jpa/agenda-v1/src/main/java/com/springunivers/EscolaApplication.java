package com.springunivers;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.springunivers.desktop.FormularioAgenda;
import com.springunivers.telas.TelaMenu;

@SpringBootApplication
public class EscolaApplication {
	private static final Logger LOGGER = LogManager.getLogger(EscolaApplication.class);
	private static ConfigurableApplicationContext contexto;
	public static void main(String[] args) {
		try {
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			SpringApplicationBuilder builder = new SpringApplicationBuilder(EscolaApplication.class);
			builder.headless(false);
			contexto = builder.run(args);
			//FormularioAgenda main = getBean(FormularioAgenda.class);
			TelaMenu menu = getBean(TelaMenu.class);
			menu.exibir();
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
