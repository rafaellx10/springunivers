package com.tci.controller;

import java.io.File;
import java.math.BigDecimal;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//https://dzone.com/articles/determining-file-types-java

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
		return String.format("%.2f", Kbytes(bytes)) + "Kb";
	}

	public String megabytes(long bytes) {
		return String.format("%.2f", Mbytes(bytes)) + "Mb";
	}

	public String gigabytes(long bytes) {
		return String.format("%.2f", Gbytes(bytes)) + "Gb";
	}

	public String tipoConteudo(File arquivo) throws Exception {
		return defaultTika.detect(arquivo);
	}
	public boolean isTif(File arquivo) throws Exception {
		String extensao=tipoConteudo(arquivo);
		return extensao.contains("tif") || extensao.contains("tiff");
	}
	
	/*
	 * public static void main(String[] args) { try { Tika t = new Tika();
	 * 
	 * System.out.println(t.detect(new File("C:\\tci\\tiff\\3138\\1.tif"))); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	/*
	 * private final static Tika defaultTika = new Tika(); private final static Tika
	 * mimeTika = new Tika(new MimeTypes()); private final static Tika typeTika =
	 * new Tika(new TypeDetector());
	 * 
	 * public static void main(String[] args) { try { File file = new
	 * File("C:\\tci\\tiff\\3138\\2.hocr"); //
	 * System.out.println(file.getName().contains(".hocr"));
	 * System.out.println(Files.probeContentType(file.toPath())); //
	 * System.out.println(identifyFileTypeUsingMimetypesFileTypeMap(file.
	 * getAbsolutePath())); //
	 * System.out.println(identifyFileTypeUsingUrlConnectionGetContentType(file.
	 * getAbsolutePath()));
	 * 
	 * System.out.println(identifyFileTypeUsingDefaultTika(file.getAbsolutePath()));
	 * System.out.println(identifyFileTypeUsingMimeTypesTika(file.getAbsolutePath())
	 * );
	 * System.out.println(identifyFileTypeUsingTypeDetectorTika(file.getAbsolutePath
	 * ()));
	 * 
	 * System.out.println(identifyFileTypeUsingMimeTypesTikaForFile(file.
	 * getAbsolutePath())); //
	 * System.out.println(identifyFileTypeUsingTypeDetectorTikaForFile(file.
	 * getAbsolutePath())); } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * public static String identifyFileTypeUsingMimetypesFileTypeMap(String
	 * fileName) { final MimetypesFileTypeMap fileTypeMap = new
	 * MimetypesFileTypeMap(); return fileTypeMap.getContentType(fileName); }
	 * 
	 * public static String identifyFileTypeUsingUrlConnectionGetContentType(final
	 * String fileName) { String fileType = "Undetermined"; try { final URL url =
	 * new URL("file://" + fileName); final URLConnection connection =
	 * url.openConnection(); fileType = connection.getContentType(); } catch
	 * (MalformedURLException badUrlEx) { badUrlEx.printStackTrace(); } catch
	 * (IOException ioEx) { ioEx.printStackTrace(); } return fileType; }
	 * 
	 * public static String identifyFileTypeUsingDefaultTika(final String fileName)
	 * { return defaultTika.detect(fileName); }
	 * 
	 * public static String identifyFileTypeUsingMimeTypesTika(final String
	 * fileName) { return mimeTika.detect(fileName); }
	 * 
	 * public static String identifyFileTypeUsingTypeDetectorTika(final String
	 * fileName) { return typeTika.detect(fileName); }
	 * 
	 * public static String identifyFileTypeUsingMimeTypesTikaForFile(final String
	 * fileName) { String fileType; try { final File file = new File(fileName);
	 * fileType = mimeTika.detect(file); } catch (IOException ioEx) { // fileType =
	 * "Unknown"; } return fileType; }
	 * 
	 * public static String identifyFileTypeUsingTypeDetectorTikaForFile(final
	 * String fileName) { String fileType; try { final File file = new
	 * File(fileName); fileType = typeTika.detect(file); } catch (IOException ioEx)
	 * { fileType = "Unknown"; } return fileType; }
	 */
}
