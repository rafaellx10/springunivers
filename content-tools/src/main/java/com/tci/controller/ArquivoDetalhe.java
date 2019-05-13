package com.tci.controller;

import java.io.File;
import java.math.BigDecimal;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ArquivoDetalhe {
	@Autowired
	@Qualifier("default")
	private Tika defaultTika;

	public static int KB = 1024;
	public static int MB = KB * 1024;
	public static int GB = MB * 1024;

	private double tamanho(long bytes, int unidade) {
		return new BigDecimal(bytes).divide(new BigDecimal(unidade)).doubleValue();
	}

	public long Bytes(File arquivo) {
		return arquivo.length();
	}

	public double Kbytes(long bytes) {
		return tamanho(bytes, KB);
	}

	public double Mbytes(long bytes) {
		return tamanho(bytes, MB);
	}

	public double Gbytes(long bytes) {
		return tamanho(bytes, GB);
	}

	public String kilobytes(File arquivo) {
		return kilobytes(arquivo.length());
	}

	public String megabytes(File arquivo) {
		return megabytes(arquivo.length());
	}
	public String gigabytes(File arquivo) {
		return gigabytes(arquivo.length());
	}
	public String kilobytes(long bytes) {
		return String.format("%.2f",Kbytes(bytes)) + "Kb";
	}
	public String megabytes(long bytes) {
		return String.format("%.2f",Mbytes(bytes)) + "Mb";
	}
	public String gigabytes(long bytes) {
		return String.format("%.2f",Gbytes(bytes)) + "Gb";
	}
	public String extensaoOriginal(File arquivo) throws Exception {
		return defaultTika.detect(arquivo);
	}

	public static void main(String[] args) {
		try {
			Tika t = new Tika();
			
			System.out.println(t.detect(new File("C:\\tci\\tiff\\3138\\1.tif")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
