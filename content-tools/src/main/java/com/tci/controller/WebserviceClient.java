package com.tci.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tci.beans.Ambiente;

@Service
public class WebserviceClient {
	@Autowired
	private Ambiente ambiente;
	private static final Logger LOGGER = LogManager.getLogger(WebserviceClient.class);
	@Autowired
	private ObjectMapper mapper;
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
		return String.format("%s%s", ambiente.getUniprofUrl(),"");
	}
	public void logar(String login,String senha) throws Exception {
		String post=String.format("{\"email\": \"%s\",\"password\": \"%s\"}", login,senha) ;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(post,headers);
		Object result = getRestTemplate().postForObject(getRoot()+"/api/auth", entity, String.class);
		LOGGER.info("RESPOSTA DO LOGIN --> " + result.toString());
		JsonNode jsonNode = mapper.readTree(result.toString());
		String token = jsonNode.get("token").asText();
		setToken(token);
	}
	public void companies() throws Exception {
		ResponseEntity<List<String>> response = getRestTemplate().exchange(
				getRoot()+"/api/companies",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<String>>(){});
		List<String> employees = response.getBody();
		System.out.println(employees);
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
