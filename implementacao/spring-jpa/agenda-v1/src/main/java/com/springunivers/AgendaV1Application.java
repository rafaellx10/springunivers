package com.springunivers;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.springunivers.desktop.FormularioAgenda;

@SpringBootApplication
public class AgendaV1Application {
	private static final Logger LOGGER = LogManager.getLogger(AgendaV1Application.class);
	private static ConfigurableApplicationContext contexto;
	public static void main(String[] args) {
		try {
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			SpringApplicationBuilder builder = new SpringApplicationBuilder(AgendaV1Application.class);
			builder.headless(false);
			contexto = builder.run(args);
			FormularioAgenda main = getBean(FormularioAgenda.class);
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
	/*
	@Bean
	public CommandLineRunner run(FormularioAgenda formulario) {
		return args -> {
			formulario.exibir();
			/*
			Contato contato = new Contato();
			contato.setNome("RAIANE");
			contato.setSobrenome("OLIVEIRA");
			contato.setDdd(11);
			contato.setNumero(455696654L);
			contato.setCidade("PARNAIBA");
			contato.setEstado("PI");
			
			programa.salvarContatoDao(contato);
			
			//programa.incluirContatoRepository(contato);
			//programa.imprimirContatos();
			//programa.imprimirContatosContem("JOSE");
		};
	}
	*/
}
