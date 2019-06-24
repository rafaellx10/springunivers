package br.com.projeto;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.projeto.telas.TelaMenu;

@SpringBootApplication
public class ProjetoApplication{// implements CommandLineRunner {
	
	//@Autowired
	private static ApplicationContext context;
	
	public static void main(String[] args) {
		context= new SpringApplicationBuilder(ProjetoApplication.class)
			.headless(false).run(args);
		TelaMenu frame = context.getBean(TelaMenu.class);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/*
	@Override
	public void run(String... args) throws Exception {
		// Abre a tela de menu ao iniciar o sistema
		TelaMenu frame = context.getBean(TelaMenu.class);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	*/
	public static <T> T getBean(Class bean) {
		return (T) context.getBean(bean);
	}

}
