package com.springunivers.converter;

import java.io.File;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ArquivoDetalhe {
	@Autowired
	@Qualifier("default")
	private Tika defaultTika;

	public String extensaoOriginal(File arquivo) throws Exception {
		return defaultTika.detect(arquivo);
	}

	public long bytes(File arquivo) {
		return arquivo.length();
	}

	public long kilobytes(File arquivo) {
		return bytes(arquivo) / 1024;
	}

	public long megabytes(File arquivo) {
		return kilobytes(arquivo) / 1024;
	}

	public long gigabytes(File arquivo) {
		return megabytes(arquivo) / 1024;
	}

	public long terabytes(File arquivo) {
		return gigabytes(arquivo) / 1024;
	}
	
	public String mb(File arquivo) {
		return megabytes(arquivo) + " Mb";
	}
	public String kb(File arquivo) {
		return kilobytes(arquivo) + " Kb";
	}
	
	/*
	 * public static void main(String[] args) { try { File file = new
	 * File("C:\\tci\\img\\3130\\1.tif"); //
	 * System.out.println(Files.probeContentType(file.toPath())); //
	 * System.out.println(identifyFileTypeUsingMimetypesFileTypeMap(file.
	 * getAbsolutePath())); //
	 * System.out.println(identifyFileTypeUsingUrlConnectionGetContentType(file.
	 * getAbsolutePath()));
	 * 
	 * //
	 * System.out.println(identifyFileTypeUsingDefaultTika(file.getAbsolutePath()));
	 * //
	 * System.out.println(identifyFileTypeUsingMimeTypesTika(file.getAbsolutePath())
	 * ); //
	 * System.out.println(identifyFileTypeUsingTypeDetectorTika(file.getAbsolutePath
	 * ()));
	 * 
	 * System.out.println(identifyFileTypeUsingDefaultTikaForFile(file.
	 * getAbsolutePath())); //
	 * System.out.println(identifyFileTypeUsingMimeTypesTikaForFile(file.
	 * getAbsolutePath())); //
	 * System.out.println(identifyFileTypeUsingTypeDetectorTikaForFile(file.
	 * getAbsolutePath())); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
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
	 * 
	 * 
	 * public static String identifyFileTypeUsingMimeTypesTikaForFile(final String
	 * fileName) { String fileType; try { final File file = new File(fileName);
	 * fileType = mimeTika.detect(file); } catch (IOException ioEx) { //
	 * out.println("Unable to detect type of file " + fileName + " - " + ioEx);
	 * fileType = "Unknown"; } return fileType; }
	 * 
	 * public static String identifyFileTypeUsingTypeDetectorTikaForFile(final
	 * String fileName) { String fileType; try { final File file = new
	 * File(fileName); fileType = typeTika.detect(file); } catch (IOException ioEx)
	 * { // out.println("Unable to detect type of file " + fileName + " - " + ioEx);
	 * fileType = "Unknown"; } return fileType; }
	 */
}
