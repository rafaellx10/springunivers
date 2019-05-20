package com.tci.controller;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tci.model.Diretorio;
import com.tci.util.FileWritterUtil;

@Component
public class DiretorioDetalhe {
	@Autowired
	private ArquivoDetalhe arquivoDetalhe;
	private static final Logger LOGGER = LogManager.getLogger(DiretorioDetalhe.class);
	private StringBuilder log = new StringBuilder();
	public void limparLog() {
		log = new StringBuilder();
	}
	public String getLog() {
		return log.toString();
	}
	public String existeOcrZip(boolean arvore,String diretorio) {
		String log="";
		if(arvore) {
			File[] dirs = new File(diretorio).listFiles();
			for (File dir : dirs) {
				if(dir.isDirectory()) {
					existeOcrZip(true, dir.getAbsolutePath());
				}
			}
			log=existeOcrZip(diretorio);
		}else
			log=existeOcrZip(diretorio);
		if(log!=null)
			this.log.append(log);
		return this.log.toString();
	}
	public String removerOcrZip(String diretorio) {
		File file = new File(diretorio,"OCR.zip");
		if(file.exists()) {
			file.delete();
			LOGGER.warn("<REMOVIDO OCR.zip> " + file.getAbsolutePath() + " " + file.length() + "bytes");
		}
		return file.getAbsolutePath() + " REMOVIDO ";
	}
	private String existeOcrZip(String diretorio) {
		try {
			if(diretorioFinal(new File(diretorio))) {
				Diretorio ocr = contemOcrZip(new Diretorio(diretorio));
				String scan = diretorio+";"+ocr.getContemOcrZip()+"\n";
				LOGGER.warn("<ANALISANO OCR.zip> em" + diretorio);
				return scan ;
			}else return null;
		}catch (Exception e) {
			LOGGER.error("<ERRO - ANALISE OCR.zip> em" + diretorio);
			return null;
		}
	}
	
	public boolean diretorioFinal(File dir) {
		return dir.isDirectory() &&  dir.listFiles()[0].isFile();
	}
	public void diretorioScan(boolean arvore,String diretorio) {
		String log="";
		try {
			if(arvore) {
				File[] dirs = new File(diretorio).listFiles();
				for (File dir : dirs) {
					if(dir.isDirectory()) {
						diretorioScan(true, dir.getAbsolutePath());
					}
				}
				log=diretorioScan(diretorio);
			}else
				log=diretorioScan(diretorio);
			if(log!=null)
				new FileWritterUtil().writer(FileWritterUtil.DIRETORIO_OCR_TXT_HOCR_TIFEERO,log);
		}catch (Exception e) {
			LOGGER.error("<ERRO> AO PROCESSAR O <SCAN DIR> NO DIRETORIO: " + diretorio);
		}
	}
	private String diretorioScan(String diretorio) {
		if(diretorioFinal(new File(diretorio))) {
			Diretorio dir = contemOcrZip(new Diretorio(diretorio));
			StringBuilder csv = new StringBuilder(dir.getNome()+";"+dir.getContemOcrZip());
			File[] files = dir.getEndereco().listFiles();
			try {
				if(!dir.getContemOcrZip().equals("S")) {
					for(File file: files) {
						if(arquivoDetalhe.isTif(file) || arquivoDetalhe.jpgJpegOriginal(file)) {
							File txt = new File(diretorio, file.getName().replaceAll("tif", "txt"));
							File hocr = new File(diretorio, file.getName().replaceAll("tif", "hocr"));
							boolean bTxt = txt.exists();
							boolean bHocr=hocr.exists();
							boolean ocrZip = bTxt && bHocr;
							String acao = ocrZip?"GERAR OCR.zip":"GERAR txt e hocr";
							csv.append("\n;;"+file.getParent()+";"+file.getName()+";"+String.format("%.3f",arquivoDetalhe.Mbytes(file.length())) +";"+String.format("%.3f",arquivoDetalhe.Gbytes(file.length())) +";"+ simNao(bTxt)+";"+simNao(bHocr)+";"+(arquivoDetalhe.jpgJpegOriginal(file)?"S":"N")+";"+acao+";1");
						}
					}
					csv.append("\n");
				}
				LOGGER.info("<SCANDIR> em: " + diretorio);
			}catch (Exception e) {
				LOGGER.error("ERRO AO TENTAR VALIDAR OS TIPOS txt e hocr no diretório:" + diretorio);
			}
			return csv.toString() ;
		}else return null;
	}
	private String simNao(boolean v) {
		return (v?"S":"N");
	}
	private Diretorio contemOcrZip(Diretorio diretorio) {
		File ocr = new File(diretorio.getNome(),"OCR.zip");
		diretorio.setContemOcrZip((ocr.exists()?"S" + (ocr.length()<=22?"0bytes":""):"N")); //22bytes=zip vazio
		return diretorio ;
	}
	public String csvVersusDiretorio(String diretorio, String imagem) {
		if(imagem.contains("pdf")) {
			File img = new File(diretorio,imagem); 
			boolean existDir=img.exists();
			imagem = img.getAbsolutePath()+";" + (existDir?"S":"N") + "; ;";
		}
		return imagem;
	}
	
}
