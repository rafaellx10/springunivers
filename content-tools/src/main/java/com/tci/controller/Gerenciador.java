package com.tci.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

import com.tci.beans.Ambiente;
import com.tci.model.Diretorio;
import com.tci.model.Imagem;

@Component
public class Gerenciador {
	private static final Logger LOGGER = LogManager.getLogger(Gerenciador.class);
	@Autowired
	private ArquivoDetalhe arquivoDetalhe;
	@Autowired
	private Ambiente ambiente;
	private Map<String,Diretorio> repositorio;
	
	
	public void criarRepositorio() {
		repositorio=new HashMap<String,Diretorio>();
	}
	public void atualizarRepositorio(String linhaCsv) {
		String[] campos = linhaCsv.split("\\;");
		String nomeDir=campos[0];
		String nomeImg = campos[1];
		Diretorio diretorio=null;
		if((diretorio = repositorio.get(nomeDir))==null) {
			diretorio = new Diretorio(nomeDir);
			repositorio.put(nomeDir, diretorio);
		}
		diretorio.addImagem(new Imagem(nomeImg));
	}
	public List<Diretorio> getRepositorio() {
		return new ArrayList<Diretorio>(repositorio.values());
	}
	public String removerImagens(Diretorio diretorio) throws Exception {
		diretorioVolume(diretorio, false);
		diretorio.setTotal(diretorio.getImagens().size());
		removerImagem(diretorio);
		return diretorio.getNome() + " --> Removendo imagens\n";
	}
	private void removerImagem(Diretorio diretorio) throws Exception {
		File csvImagemFile = new File(ambiente.getAppPath(), "REMOVER_IMAGENS_RESUMO.csv");
		StringBuilder sb = new StringBuilder();
		if (!csvImagemFile.exists()) {
			csvImagemFile.createNewFile();
			sb.append("DIRETORIO;TOTAL;IMAGEM;KB;MB;GB;KB-ATUAL;MB-ATUAL;GB-ATUAL;STATUS\n");
		}
		FileWriter fileWriter = new FileWriter(csvImagemFile, true);
		try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
			sb.append(diretorio.getNome() + ";");
			sb.append(diretorio.getTotal() + ";");
			sb.append(" ;");
			sb.append(String.format("%.3f", diretorio.getKb()) + ";");
			sb.append(String.format("%.3f", diretorio.getMb()) + ";");
			sb.append(String.format("%.3f", diretorio.getGb()) + ";");
			sb.append("KbNew");
			sb.append("MbNew");
			sb.append("GbNew\n");
			for(Diretorio imagem: diretorio.getImagens()) {
				sb.append(removerImagem(diretorio, imagem));
			}
			diretorioVolume(diretorio, true);
			String log= sb.toString();
			log=log.replaceAll("KbNew", String.format("%.3f", diretorio.getKbNew()) + ";");
			log=log.replaceAll("MbNew", String.format("%.3f", diretorio.getMbNew()) + ";");
			log=log.replaceAll("GbNew", String.format("%.3f", diretorio.getGbNew()) + ";");
			
			printWriter.print(log);
			printWriter.close();
		} catch (Exception e) {
			LOGGER.error("<ERRO REMOÇÃO IMAGEM> csvImagens do diretório: {} ", diretorio.getNome());
			
		} finally {
			LOGGER.info("<RESUMO> csvImagens do diretório: {} ", diretorio.getNome());
		}
	}
	private String removerImagem(Diretorio diretorio, Diretorio imagem) throws Exception {
		StringBuilder sb = new StringBuilder();
		File img = new File(diretorio.getEndereco(),imagem.getNome());
		sb.append(" ; ;");
		sb.append(imagem.getNome() + ";");
		sb.append(String.format("%.3f", arquivoDetalhe.Kbytes(img.length())) + ";");
		sb.append(String.format("%.3f", arquivoDetalhe.Mbytes(img.length())) + ";");
		sb.append(String.format("%.3f", arquivoDetalhe.Gbytes(img.length())) + ";");
		sb.append(" ; ; ;");
		boolean removida=false;
		if(img.exists()) removida= img.delete();
		sb.append((removida?"EXCLUIDA": "NAO EXCLUIDA")+ "\n");
		
		return sb.toString(); 
	}
	public String converter(Diretorio diretorio) throws Exception {
		if (diretorio.getEndereco().isDirectory()) {
			diretorioVolume(diretorio, false);
			String volume = String.format("%s %.2f (Kb) %.2f (Mb) %.2f (Gb) ", diretorio.getNome(), diretorio.getKb(),
					diretorio.getMb(), diretorio.getGb());
			diretorio.setInicio(new Date());
			LOGGER.info("<INICIANDO> O processo de conversão do diretório: {} ", volume);
			File[] arquivos = diretorio.getEndereco().listFiles();
			diretorio.setTotal(arquivos.length);
			for (File arquivo : arquivos) {
				if (arquivo.isFile() && arquivoDetalhe.jpgJpegOriginal(arquivo)) {
					String mb = arquivoDetalhe.megabytes(arquivo);
					String kb = arquivoDetalhe.kilobytes(arquivo);
					File jpg = getJpgFile(arquivo);
					arquivo.renameTo(jpg);
					String mbj = arquivoDetalhe.megabytes(jpg);
					String kbj = arquivoDetalhe.kilobytes(jpg);
					LOGGER.info("O arquivo {} {} {} <FOI> convertido para {} {} {} ", arquivo.getName(), kb, mb,
							jpg.getName(), kbj, mbj);
					converterJpgToTif(jpg);
					removerJpg(jpg);
				} else {
					LOGGER.info("O arquivo {} {} {} <POSSUI> a extensão adequada ", arquivo.getName(),
							arquivoDetalhe.kilobytes(arquivo), arquivoDetalhe.megabytes(arquivo));
				}
			}

			diretorioVolume(diretorio, true);
			volume = String.format("%s %.2f (Kb) %.2f (Mb) %.2f (Gb) ", diretorio.getNome(), diretorio.getKbNew(),
					diretorio.getMbNew(), diretorio.getGbNew());
			diretorio.setFim(new Date());
			csv(diretorio);
			LOGGER.info("<FINALIZANDO> O processo de conversão do diretório: {} ", volume);
		} else {
			LOGGER.info("<ATENCAO> o endereco {} <NÃO É UM DIRETÓRIO> ", diretorio.getEndereco());
		}
		return diretorio.getNome() + " Processado <VEJA O LOG> ";
	}

	private void removerJpg(File jpg) {
		if (jpg.exists()) {
			if (jpg.delete()) {
				LOGGER.info("O arquivo {} foi <REMOVIDO>", jpg);
			} else
				LOGGER.info("O arquivo {} <NÃO> foi <REMOVIDO>", jpg);
		}
	}

	private void diretorioVolume(Diretorio diretorio, boolean depois) {
		long bytes = FileUtils.sizeOfDirectory(diretorio.getEndereco());
		if (depois) {
			diretorio.setKbNew(arquivoDetalhe.Kbytes(bytes));
			diretorio.setMbNew(arquivoDetalhe.Mbytes(bytes));
			diretorio.setGbNew(arquivoDetalhe.Gbytes(bytes));
		} else {
			diretorio.setKb(arquivoDetalhe.Kbytes(bytes));
			diretorio.setMb(arquivoDetalhe.Mbytes(bytes));
			diretorio.setGb(arquivoDetalhe.Gbytes(bytes));
		}
	}

	private void converterJpgToTif(File jpg) throws Exception {
		ImageWriter writer = null;
		if (jpg.exists()) {
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
				LOGGER.info("O arquivo {} {} <CONVERTIDO> com sucesso!! ", tif.getName(), arquivoDetalhe.megabytes(tif));
			} finally {
				writer.dispose();
				writer = null;
			}
		} else {
			LOGGER.info("O arquivo {} <NÃO EXISTE> ou <NÃO FOI CONVERTIDO PELO PROCESSO ANTERIOR> ", jpg.getName());
		}
	}

	private void csv(Diretorio diretorio) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		String resumo = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File file = new File(ambiente.getAppPath(), "RESUMO_" + resumo + ".csv");
		if (!file.exists()) {
			file.createNewFile();
			sb.append("DIRETORIO;ARQUIVOS;KB;MB;GB;KB-ATUAL;MB-ATUAL;GB-ATUAL;INICIO;FIM\n");
		}
		FileWriter fileWriter = new FileWriter(file, true);
		try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
			sb.append(diretorio.getNome() + ";");
			sb.append(diretorio.getTotal() + ";");
			sb.append(String.format("%.2f", diretorio.getKb()) + ";");
			sb.append(String.format("%.2f", diretorio.getMb()) + ";");
			sb.append(String.format("%.2f", diretorio.getGb()) + ";");
			sb.append(String.format("%.2f", diretorio.getKbNew()) + ";");
			sb.append(String.format("%.2f", diretorio.getMbNew()) + ";");
			sb.append(String.format("%.2f", diretorio.getGbNew()) + ";");
			sb.append(sdf.format(diretorio.getInicio()) + ";");
			sb.append(sdf.format(diretorio.getFim()) + ";");
			printWriter.println(sb.toString());
			printWriter.close();
		} finally {
			LOGGER.info("<RESUMO> csv do diretório: {} ", diretorio.getNome());
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

	
	
	public void gerarOcrZip(String diretorio) throws Exception {
		File dir = new File(diretorio);
		File[] files = dir.listFiles();
        FileOutputStream fos = new FileOutputStream(new File(dir, "OCR.zip"));
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (File file : files) {
        	if(file.getName().contains(".hocr")) {
	            FileInputStream fis = new FileInputStream(file);
	            ZipEntry zipEntry = new ZipEntry(file.getName());
	            zipOut.putNextEntry(zipEntry); 
	            byte[] bytes = new byte[1024];
	            int length;
	            while((length = fis.read(bytes)) >= 0) {
	                zipOut.write(bytes, 0, length);
	            }
	            fis.close();
        	}
        }
        zipOut.close();
        fos.close();
        for (File file : files) {
        	if(file.getName().contains(".hocr")) {
        		if(file.exists())
        			file.delete();
        	}
        }
	}
	public void removerArquivos(String diretorio, String extensao) {
		
	}
}
