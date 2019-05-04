package com.springunivers.converter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Conversor {
	private static final Logger LOGGER = LogManager.getLogger(Conversor.class);
	@Autowired
	private ArquivoDetalhe detalhe;

	public void converter(File diretorio) throws Exception {
		long bytes = FileUtils.sizeOfDirectory(diretorio);
		double mb= new BigDecimal(bytes).divide(new BigDecimal(1024)).divide(new BigDecimal(1024)).doubleValue();
		double gb= new BigDecimal(mb).divide(new BigDecimal(1024)).doubleValue();
		LOGGER.info("<INICIANDO> O processo de conversão no diretório: {} com {}(bytes) {}(megabytes) {}(gigabytes) ", diretorio.getAbsolutePath(), bytes,mb,gb);
		File[] arquivos = diretorio.listFiles();
		for (File arquivo : arquivos) {
			if (arquivo.isFile() && jpgJpegOriginal(arquivo)) {
				String kb = detalhe.kb(arquivo);
				File jpg = getJpgFile(arquivo);
				arquivo.renameTo(jpg);
				String kbj = detalhe.kb(jpg);
				LOGGER.info("O arquivo {} {} <FOI> convertido para {} {} ", arquivo.getName(), kb, jpg.getName(), kbj);
				converterJpgToTif(jpg);
			} else {
				LOGGER.info("O arquivo {} {} <POSSUI> a extensão adequada ", arquivo.getName(), detalhe.kb(arquivo));
			}
		}
	}
	private void converterJpgToTif(File jpg) throws Exception {
		ImageWriter writer = null;
		try {
			File tif = getTifFile(jpg);
			BufferedImage jpgBuffer = ImageIO.read(jpg);
			if (tif.exists()) {
				tif.delete();
			}
			ImageOutputStream ios = null;
			Iterator it = ImageIO.getImageWritersByFormatName("tiff");
			if (it.hasNext()) {
				writer = (ImageWriter) it.next();
			}

			ImageWriteParam writeParam = writer.getDefaultWriteParam();
			writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			writeParam.setCompressionType("JPEG");

			ios = ImageIO.createImageOutputStream(tif);
			writer.setOutput(ios);

			IIOImage iioImage = new IIOImage(jpgBuffer, null, null);
			writer.write(null, iioImage, writeParam);
			LOGGER.info("O arquivo {} {} <FINALIZADO> com sucesso!! ", tif.getName(), detalhe.kb(tif));
		} finally {
			writer.dispose();
			writer = null;
		}

	}
	
	
	private File getJpgFile(File tif) {
		String nome = tif.getName().replaceAll("\\.tif$", ".jpg");
		File jpeg = new File(tif.getParent(), nome);
		return jpeg;
	}

	private File getTifFile(File jpg) {
		String nome = jpg.getName().replaceAll("\\.jpg$", ".tif");
		File tif = new File(jpg.getParent(), nome);
		return tif;
	}

	public boolean jpgJpegOriginal(File arquivo) throws Exception {
		return detalhe.extensaoOriginal(arquivo).toLowerCase().contains("jpeg")
				|| detalhe.extensaoOriginal(arquivo).toLowerCase().contains("jpg");
	}
	
	/*
	 * private void converterJpgToTif(File jpg) throws Exception { ImageWriter
	 * writer = null; try { File tiffFile = getTifFile(jpg); BufferedImage jpgBuffer
	 * = ImageIO.read(jpg); if (tiffFile.exists()) { tiffFile.delete(); }
	 * ImageOutputStream ios = null; Iterator it =
	 * ImageIO.getImageWritersByFormatName("tiff"); if (it.hasNext()) { writer =
	 * (ImageWriter) it.next(); }
	 * 
	 * ImageWriteParam writeParam = writer.getDefaultWriteParam();
	 * writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	 * writeParam.setCompressionType("JPEG");
	 * 
	 * ios = ImageIO.createImageOutputStream(tiffFile); writer.setOutput(ios);
	 * 
	 * IIOImage iioImage = new IIOImage(jpgBuffer, null, null); writer.write(null,
	 * iioImage, writeParam);
	 * LOGGER.info("O arquivo {} {} <FINALIZADO> com sucesso!! ", jpg.getName(),
	 * detalhe.kb(jpg)); } finally { writer.dispose(); writer = null; }
	 * 
	 * }
	 */

}
