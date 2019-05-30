package com.springunivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.meetup.Email;

@SpringBootApplication
@ComponentScan(basePackages= {"com.meetup","com.springunivers"})
public class TesteBeanApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TesteBeanApplication.class, args);
		Mensagem m = context.getBean(Mensagem.class);  
		m.enviar();
		m = context.getBean(Mensagem.class);  
		m.enviar();
		
		Email email = context.getBean(Email.class);
		email.enviar();
	}

}
