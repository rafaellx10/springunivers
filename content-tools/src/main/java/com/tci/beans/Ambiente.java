package com.tci.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Ambiente {
	@Value("${user.dir}")
	private String appPath;
	@Value("${app.uniprof.url:https://uniproof-api-stage.herokuapp.com}")
	private String uniprofUrl;
	@Value("${app.ocrprocessor.porta:8181}")
	private String ocrProcessorPorta;
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
	
}
