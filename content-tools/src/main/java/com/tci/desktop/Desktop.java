package com.tci.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tci.controller.Gerenciador;
import com.tci.controller.OcrProcessClient;
import com.tci.controller.DiretorioDetalhe;
import com.tci.model.Arquivo;
import com.tci.util.FileWritterUtil;
import java.awt.Font;

@Component
public class Desktop extends JFrame {
	public static List<String> logs = new ArrayList<String>();
	private static final Logger LOGGER = LogManager.getLogger(Desktop.class);
	@Autowired
	private Gerenciador conversor;
	@Autowired
	private DiretorioDetalhe detalhe;
	@Autowired
	private OcrProcessClient client;
	private JTextArea textDir = new JTextArea();
	private JTextArea textLogs = new JTextArea();
	private JButton btnConverter = new JButton("Converter");
	private JButton btnValidaOcr = new JButton("Validar OCR");
	private JButton btnRemoverImagem = new JButton("Remover Imagem");
	private JButton btnCsvXDiretorio = new JButton("Csv X Diretorio");
	private Loading loding=new Loading();
	private final JButton btnGerarOcr = new JButton("Gerar OCR");
	public Desktop() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel pAcoes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pAcoes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pAcoes.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel pDiretorios = new JPanel();
		pDiretorios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Diret\u00F3rios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel pLog = new JPanel();
		pLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LOG", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Content Tools", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();

		panel.setLayout(gbl_panel);

		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.weighty = 1.0;
		gbc_textArea.weightx = 1.0;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		textDir.setFont(new Font("Arial", Font.PLAIN, 12));

		textDir.setLineWrap(true);
		textDir.setWrapStyleWord(true);

		textLogs.setLineWrap(true);
		textLogs.setWrapStyleWord(true);
		textLogs.setFont(new Font("Arial", Font.PLAIN, 12));

		JScrollPane scrollDir = new JScrollPane();
		scrollDir.setViewportView(textDir);

		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;

		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				converter();
			}
		});
		pAcoes.add(btnConverter);

		btnValidaOcr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarOcr();
			}
		});
		pAcoes.add(btnValidaOcr);

		JScrollPane scrollLogs = new JScrollPane();
		scrollLogs.setViewportView(textLogs);
		GridBagConstraints gbc_scrollLogs = new GridBagConstraints();
		gbc_scrollLogs.weighty = 1.0;
		gbc_scrollLogs.weightx = 1.0;
		gbc_scrollLogs.fill = GridBagConstraints.BOTH;
		gbc_scrollLogs.gridx = 0;
		gbc_scrollLogs.gridy = 2;
		pDiretorios.setLayout(new BorderLayout(0, 0));

		pDiretorios.add(scrollDir);

		panel.add(pDiretorios, gbc_textArea);

		panel.add(pAcoes, gbc_panel_1);
		btnRemoverImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerImagens();
			}
		});
		
		
		pAcoes.add(btnRemoverImagem);
		btnCsvXDiretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				csvVersusDiretorio();
			}
		});
		
		
		pAcoes.add(btnCsvXDiretorio);
		btnGerarOcr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerarOcr();
			}
		});
		
		pAcoes.add(btnGerarOcr);
		
		pAcoes.add(loding);
		pLog.setLayout(new BorderLayout(0, 0));

		pLog.add(scrollLogs);
		panel.add(pLog, gbc_scrollLogs);

	}
	private void procesando(boolean processando) {
		btnConverter.setEnabled(!processando);
		btnValidaOcr.setEnabled(!processando);
		btnRemoverImagem.setEnabled(!processando);
		btnCsvXDiretorio.setEnabled(!processando);
		btnGerarOcr.setEnabled(!processando);
		loding.exibir(processando);
		if(!processando) {
			JOptionPane.showMessageDialog(null, "PROCESSO FINALIZADO");
		}
	}
	private void gerarOcr() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					procesando(true);
					String[] diretorios = textDir.getText().split("\\n");
					for (int i = 0; i < diretorios.length; i++) {
						String diretorio = diretorios[i];
						String log="\n<PROCESSANDO OCR> do diretório " + diretorio;
						textLogs.append(log);
						LOGGER.info(log);
						client.gerarOcr(diretorio);
						log="\n<FIM PROCESSO OCR> do diretório " + diretorio;
						textLogs.append(log);
						LOGGER.info(log);
					}
					LOGGER.info("FIM DO PROCESSO DE GERAÇÃO DE OCR");
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		}).start();
	}
	private void csvVersusDiretorio() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String diretorio = JOptionPane.showInputDialog( "Informe o diretorio" );
					textLogs.setText("");
					procesando(true);
					String[] imagens = textDir.getText().split("\\n");
					List<String> nomes = new ArrayList<String>();
					for (int i = 0; i < imagens.length; i++) {
						String imagem = imagens[i];
						String[] split =imagem.split("\\|");
						imagem =split[split.length-1];
						if(imagem!=null && imagem.length() > 0 && imagem.contains("pdf")) {
							textLogs.append(detalhe.csvVersusDiretorio(diretorio, imagem) +"\n");
							nomes.add(imagem);
						}
					}
					Collections.sort(nomes);
					List<String>csvImagens = Arrays.asList(new File(diretorio).list());
					Collections.sort(csvImagens);
					for (String img: csvImagens) {
						boolean exists = false;
						for(String nome: nomes) {
							if(exists=nome.equals(img))
								break;
						}
						textLogs.append(diretorio+"\\"+img +"; ;" + (exists?"S":"N") + "\n");
					}
					new FileWritterUtil().writer("CSV_X_DIRETORIO.csv","IMAGEM;DIRETORIO;CSV", textLogs.getText().toString());
					LOGGER.info("FIM DO PROCESSO");
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		}).start();
		
	}
	private void validarOcr() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					procesando(true);
					String[] scanDiretorios = textDir.getText().split("\\n");
					for (int i = 0; i < scanDiretorios.length; i++) {
						String var = scanDiretorios[i];
						var = detalhe.contemOcrZip(var);
						if(var!=null && var.trim().length() >0)
							textLogs.append(var + "\n");
					}
					new FileWritterUtil().writer("OCR_SCAN.csv", textLogs.getText().toString());
					LOGGER.info("FIM DO PROCESSO");
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		}).start();

	}
	private void removerImagens() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					procesando(true);
					String[] linhas = textDir.getText().split("\\n");
					conversor.criarRepositorio();
					for (int i = 0; i < linhas.length; i++) {
						String linha = linhas[i];
						conversor.atualizarRepositorio(linha);
					}
					for(Arquivo diretorio: conversor.getRepositorio()) {
						textLogs.append(conversor.removerImagens(diretorio));
					}
					textLogs.append("\nFINALIZADO");
					LOGGER.info("FIM DO PROCESSO");
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		}).start();
	}
	private void converter() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					try {
						textLogs.setText("");
						if (JOptionPane.showConfirmDialog(null, "Confirmar iniciar a conversão das imagens dos diretórios acima?",
								"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							String[] scanDiretorios = textDir.getText().split("\\n");
							procesando(true);
							for (int i = 0; i < scanDiretorios.length; i++) {
								String endereco = scanDiretorios[i];
								textLogs.append(conversor.converter(new Arquivo(endereco)));
							}
							LOGGER.info("FIM DO PROCESSO");
						}
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error(e.getMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		}).start();
		
	}

	public void exibir() {
		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}