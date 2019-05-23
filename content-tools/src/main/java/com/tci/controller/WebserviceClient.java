package com.tci.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebserviceClient {
	private final String ROOT="https://uniproof-api-stage.herokuapp.com";
	private static final Logger LOGGER = LogManager.getLogger(WebserviceClient.class);
	private RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)throws IOException {
				String token = getToken();
				if (token!=null && !token.trim().isEmpty() ) {
					System.out.println("token-->" + token);
					request.getHeaders().set("Authorization",token);
				}

				return execution.execute(request, body);
			}
		});
		return restTemplate;
	}
	private String getRoot() {
		return String.format("%s%s", ROOT,"");
	}
	public String logar(String login,String senha) {
		String json=String.format("{\"email\": \"%s\",\"password\": \"%s\"}", login,senha) ;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		Object result = getRestTemplate().postForObject(getRoot()+"/api/auth", entity, String.class);
		LOGGER.info("RESPOSTA DO LOGIN --> " + result.toString());
		return result.toString();
	}
	public void setToken(String token) {
		System.setProperty("TOKEN", token);
	}
	public String getToken() {
		String token = System.getProperty("TOKEN");
		return token;
	}
	private static final String TIME_ZONE = "America/Sao_Paulo";
	@Scheduled(cron = "${cron}" , zone = TIME_ZONE)
	public void enviarArquivo() {
		LOGGER.info("ENVIANDO IMAGEM");
	}
}
