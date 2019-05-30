package com.meetup;

import org.springframework.stereotype.Component;

@Component
public class Email {
	public void enviar() {
		System.out.println("enviar");
	}
}
