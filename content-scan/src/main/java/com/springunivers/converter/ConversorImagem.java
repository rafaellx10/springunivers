/**
 * 
 */
package com.springunivers.converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * @author erick.nagano
 *
 */
public class ConversorImagem {

	public static void main(String[] args) throws IOException {
		try {
			ajustarExtensaoArquivo();
			converterArquivosParaTif();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ajustarExtensaoArquivo() throws Exception {
		File[] arquivos = getOrigemDir().listFiles();
		for (File arquivo : arquivos) {
			if (arquivo.isFile()) {
				File jpg=getJpgFile(arquivo);
				arquivo.renameTo(jpg);
				System.out.println("Convertendo o arquivo " + arquivo.getAbsolutePath() + " para " + jpg.getAbsolutePath());
			}
		}
	}
	
	private static void converterArquivosParaTif() throws Exception {
		File[] arquivos = getJpegDir().listFiles();
		for (File arquivo : arquivos) {
			if (arquivo.isFile()) {
				System.out.println("Convertendo o arquivo " + arquivo.getAbsolutePath() +" para .tif");
				converterJpgToTif(arquivo);
				System.out.println("Converteu arquivo " + arquivo.getName());
			}
		}
	}

	// esta forma � a mais simples por�m amplia o tamanho do arquivo
	private static void converterJpgToTifSimples(File jpg) throws IOException {
		File tif = getTifFile(jpg);
		BufferedImage image = ImageIO.read(jpg);// Or image.jpg or image.tiff, etc.
		ImageIO.write(image, "tiff", tif);

	}

	// esta forma � a mais simples por�m amplia o tamanho do arquivo
	private static void converterJpgToTif(File jpg)throws Exception {
		ImageWriter writer = null;
		try {
			File tiffFile = getTifFile(jpg);
			BufferedImage jpgBuffer = ImageIO.read(jpg);
			if (tiffFile.exists()) {
				tiffFile.delete();
			}
			ImageOutputStream ios = null;
			Iterator it = ImageIO.getImageWritersByFormatName("tiff");
			if (it.hasNext()) {
				writer = (ImageWriter) it.next();
			}

			ImageWriteParam writeParam = writer.getDefaultWriteParam();
			writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			writeParam.setCompressionType("JPEG");
			
			ios = ImageIO.createImageOutputStream(tiffFile);
			writer.setOutput(ios);

			IIOImage iioImage = new IIOImage(jpgBuffer, null, null);
			writer.write(null,iioImage,writeParam);
		} finally {
			writer.dispose();
			writer=null;
		}

	}

	private static File getJpgFile(File tif) {
		String nome = tif.getName().replaceAll("\\.tif$", ".jpg");
		File jpeg = new File(getJpegDir(), nome);
		return jpeg;
	}

	private static File getTifFile(File jpg) {
		String nome = jpg.getName().replaceAll("\\.jpg$", ".tif");
		File tif = new File(getTifDir(), nome);
		return tif;
	}

	private static String ROOT = "C:\\tci\\test";

	private static File getOrigemDir() {
		return new File(ROOT+"\\3130");
	}

	private static File getJpegDir() {
		File file = new File(ROOT + "\\jpeg");
		if (!file.exists())
			file.mkdir();
		return file;
	}

	private static File getTifDir() {
		File file = new File(ROOT + "\\tif");
		if (!file.exists())
			file.mkdir();
		return file;
	}

}
