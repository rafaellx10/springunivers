package com.tci.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sessao {
	@Value("${user.dir}")
	private String appPath;
	@Value("${app.uniprof.url:https://uniproof-api-stage.herokuapp.com}")
	private String uniprofUrl;
	@Value("${app.ocrprocessor.porta:8181}")
	private String ocrProcessorPorta;
	
	private String loginToken;
	private String companyToken;
	private String serviceId;
	
	
	public String getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	public String getCompanyToken() {
		return companyToken;
	}
	public void setCompanyToken(String companyToken) {
		this.companyToken = companyToken;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getLoteId() {
		return loteId;
	}
	public void setLoteId(String loteId) {
		this.loteId = loteId;
	}
	private String loteId;
	
	
	public String getAppPath() {
		return appPath;
	}
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	public String getUniprofUrl() {
		return uniprofUrl;
	}
	public void setUniprofUrl(String uniprofUrl) {
		this.uniprofUrl = uniprofUrl;
	}
	public String getOcrProcessorPorta() {
		return ocrProcessorPorta;
	}
	public void setOcrProcessorPorta(String ocrProcessorPorta) {
		this.ocrProcessorPorta = ocrProcessorPorta;
	}
	@Override
	public String toString() {
		return "Ambiente [appPath=" + appPath + ", uniprofUrl=" + uniprofUrl + ", ocrProcessorPorta="
				+ ocrProcessorPorta + "]";
	}
	public boolean enviarArquivos() {
		return loginToken!=null && loteId !=null;
	}
}
