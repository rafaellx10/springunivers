package com.tci.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tci.ContentTools;
import com.tci.beans.Sessao;
import com.tci.controller.DiretorioDetalhe;
import com.tci.controller.Gerenciador;
import com.tci.controller.OcrProcessClient;
import com.tci.controller.WebserviceClient;
import com.tci.model.Diretorio;
import com.tci.util.FileWritterUtil;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
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
	private JTextArea textLogFile = new JTextArea();
	private JTextArea textLogs = new JTextArea();
	private JButton btnUniContent = new JButton("UNI CONTENT");
	private JButton btnConverter = new JButton("Converter Jpg to Tiff");
	private JButton btnValidaOcr = new JButton("Existe OCR.zip?");
	private JButton btnRemoverImagem = new JButton("Remover Imagem");
	private JButton btnRemoverOcrZip = new JButton("Remover OCR.zip");
	private JButton btnCsvXDiretorio = new JButton("Csv X Diretorio");
	private Loading loding=new Loading();
	private JButton btnGerarTxtHocr = new JButton("Gerar TXT e HOCR");
	private JButton btnGerarOcrzip = new JButton("Gerar OCR.zip");
	private JButton btnScanDiretorios = new JButton("Diretório Scan");
	private JButton btnLerLog = new JButton("Start LOG");
	private JButton btnCancelar = new JButton("Cancelar Processo");
	private DateFormat df = new SimpleDateFormat("ddHHmmss");
	private Thread processoAtual;
	private Thread processoLog;

	private JPanel pnlLogFile = new JPanel();
	
	private JLabel lblLinhaAtual = new JLabel("Linha Inicial");
	private JTextField txtInicial = new JTextField();
	private JLabel lblPaginao = new JLabel("Paginação");
	private JTextField txtPaginacao = new JTextField();
	
	private Long inicio=1L;
	private JLabel lblIntervaloSegundos = new JLabel("Intervalo (segundos)");
	private JTextField txtIntervaloSegundos = new JTextField();
	
	private JTabbedPane tabbedPane;
	@Autowired
	private WebserviceClient wsClient;
	@Autowired
	private Sessao ambiente;
	public Desktop() {
		txtIntervaloSegundos.setColumns(5);
		txtInicial.setText("1");
		txtIntervaloSegundos.setText("60");
		txtPaginacao.setText("100");
		textDir.setFont(new Font("Arial", Font.PLAIN, 11));
		textDir.setLineWrap(true);
		textDir.setWrapStyleWord(true);
		textLogs.setLineWrap(true);
		textLogs.setWrapStyleWord(true);
		textLogs.setFont(new Font("Arial", Font.PLAIN, 11));
		
		textLogFile.setFont(new Font("Arial", Font.PLAIN, 10));
		textLogFile.setLineWrap(true);
		textLogFile.setWrapStyleWord(true);
		
		JPanel pnlDir = new JPanel(new BorderLayout());
		pnlDir.setBorder(new TitledBorder(null, "Lista de Diret\u00F3rios", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		
		JPanel pnlLog = new JPanel(new BorderLayout());
		pnlLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log da opera\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		JScrollPane scrollDir = new JScrollPane();
		scrollDir.setViewportView(textDir);
		JScrollPane scrollLogs = new JScrollPane();
		scrollLogs.setViewportView(textLogs);
		pnlDir.add(scrollDir,BorderLayout.CENTER);
		pnlLog.add(scrollLogs,BorderLayout.CENTER);
		
		JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, pnlDir, pnlLog); 
		split.setOneTouchExpandable(true);
		split.setDividerLocation(0.5);
		split.setResizeWeight(0.5);
		split.setDividerSize(15);
		//split.setResizeWeight(1.0); // equal weights to top and bottom    
		
		JPanel pnlContent = new JPanel();
		pnlContent.setLayout(new BorderLayout());
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Operações: " , null, pnlContent, null);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		pnlContent.add(split,BorderLayout.CENTER);
		
		JPanel pAcoes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pAcoes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pAcoes.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				converter();
			}
		});
		pAcoes.add(btnUniContent);
		pAcoes.add(btnValidaOcr);
		pAcoes.add(btnScanDiretorios);
		pAcoes.add(btnCsvXDiretorio);
		pAcoes.add(btnGerarTxtHocr);
		pAcoes.add(btnGerarOcrzip);
		pAcoes.add(btnConverter);
		pAcoes.add(btnRemoverOcrZip);
		pAcoes.add(btnRemoverImagem);
		pAcoes.add(btnLerLog);
		pAcoes.add(btnCancelar);
		pAcoes.add(loding);
		
		pnlContent.add(pAcoes,BorderLayout.SOUTH);
		
		tabbedPane.addTab("Log File", null, pnlLogFile, null);
		pnlLogFile.setLayout(new BorderLayout(0, 0));
		
		txtPaginacao.setColumns(5);
		txtInicial.setColumns(5);
		
		JPanel pnlIntervalo = new JPanel();
		FlowLayout l = new FlowLayout();
		l.setAlignment(FlowLayout.LEFT);
		pnlIntervalo.setLayout(l);
		
		pnlIntervalo.add(lblLinhaAtual);
		pnlIntervalo.add(txtInicial);
		pnlIntervalo.add(lblPaginao);
		pnlIntervalo.add(txtPaginacao);
		
		JScrollPane scrollLogFile = new JScrollPane();
		scrollLogFile.setViewportView(textLogFile);

		pnlLogFile.add(pnlIntervalo, BorderLayout.NORTH);
		
		pnlIntervalo.add(lblIntervaloSegundos);
		
		pnlIntervalo.add(txtIntervaloSegundos);
		pnlLogFile.add(scrollLogFile, BorderLayout.CENTER);
		
		btnCancelar.setVisible(false);
		btnValidaOcr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existeOcrZip();
			}
		});
		btnCsvXDiretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				csvVersusDiretorio();
			}
		});
		btnRemoverImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerImagens();
			}
		});
		btnRemoverOcrZip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerOcrZip();
			}
		});
		
		btnGerarTxtHocr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerarTxtHocr();
			}
		});
		
		btnGerarOcrzip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerarOcrZip();
			}
		});
		btnScanDiretorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				diretorioScan();
			}
		});
		btnLerLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lerLog();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarProcesso();
			}
		});
		btnUniContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uniContent();
			}
		});
	}
	public void exibir() {
		setSize(1200, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Content Tools - Porta OCR Processor: " + ambiente.getOcrProcessorPorta());
	}
	private void uniContent() {
	
		UniContent frmLogin = ContentTools.getBean(UniContent.class);
		frmLogin.setApi("API:" + ambiente.getUniprofUrl());
		frmLogin.setVisible(true);
	
	}
	
	private void cancelarProcesso() {
		try {
			processoAtual.interrupt();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			procesando(false);
		}
	}
	private void procesando(boolean processando) {
		btnCancelar.setVisible(processando);
		btnLerLog.setVisible(processando);
		
		btnConverter.setVisible(!processando);
		btnValidaOcr.setVisible(!processando);
		btnScanDiretorios.setVisible(!processando);
		btnRemoverImagem.setVisible(!processando);
		btnCsvXDiretorio.setVisible(!processando);
		btnGerarTxtHocr.setVisible(!processando);
		btnGerarOcrzip.setVisible(!processando);
		btnRemoverOcrZip.setVisible(!processando);
		loding.exibir(processando);
		if(!processando) {
			detalhe.limparLog();
			JOptionPane.showMessageDialog(null, "PROCESSO FINALIZADO");
		}
	}
	
	private void existeOcrZip() {
		processoAtual= new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String[] diretorios = textDir.getText().split("\\n");
					boolean arvore=diretorios.length==1;
					if(arvore)
						JOptionPane.showMessageDialog(null, "Você só informou um diretório, o processo será executado em toda árvore","Atenção",JOptionPane.WARNING_MESSAGE);
						textLogs.setText("");
						if (JOptionPane.showConfirmDialog(null, "Esta rotina valida a existência dos arquivos OCR.zip, deseja prosseguir?",
								"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							procesando(true);
						textLogs.setText("");
						procesando(true);
						for (int i = 0; i < diretorios.length; i++) {
							String var = diretorios[i];
							detalhe.existeOcrZip(arvore, var);
							textLogs.append(var+"\n");
						}
						new FileWritterUtil().writer("EXISTE_OCR_SCAN.csv", detalhe.getLog());
						LOGGER.info("FIM DO PROCESSO");
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();

	}
	private void diretorioScan() {
		processoAtual=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					new FileWritterUtil().writerHeader(FileWritterUtil.DIRETORIO_OCR_TXT_HOCR_TIFEERO,"DIRETORIO;OCR.zip;ENDERECO;NOME;MB;GB;TXT;HOCR;TIFERRO;ACAO;ITEM");
					String[] diretorios = textDir.getText().split("\\n");
					boolean arvore=diretorios.length==1;
					if(arvore)
						JOptionPane.showMessageDialog(null, "Você só informou um diretório, o processo será executado em toda árvore","Atenção",JOptionPane.WARNING_MESSAGE);
					textLogs.setText("");
					if (JOptionPane.showConfirmDialog(null, "Esta rotina valida a existência dos arquivos OCR.zip, .txt, .hocr e tif com erro, deseja prosseguir?",
							"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						procesando(true);
						for (int i = 0; i < diretorios.length; i++) {
							String diretorio = diretorios[i];
							detalhe.diretorioScan(arvore, diretorio);
							String log ="<SCAN OCR.zip, .txt, .hocr e tif erro> do diretório " + diretorio+"\n";
							textLogs.append(log);
							LOGGER.info(log);
						}
						LOGGER.info("FIM DO PROCESSO DE GERAÇÃO DE OCR.zip");
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();
	}
	private void gerarOcrZip() {
		processoAtual= new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					if (JOptionPane.showConfirmDialog(null, "Esta rotina vai compactar os arquivos .hocr dentro do OCR.zip, deseja prosseguir?",
							"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						procesando(true);
						String[] diretorios = textDir.getText().split("\\n");
						for (int i = 0; i < diretorios.length; i++) {
							String diretorio = diretorios[i];
							String log="<GERANDO OCR.zip> do diretório " + diretorio;
							textLogs.append(log);
							LOGGER.info(log);
							conversor.gerarOcrZip(diretorio);
							log="\n<FIM GERAÇÃO OCR.zip> do diretório " + diretorio + "\n";
							textLogs.append(log);
							LOGGER.info(log);
						}
						LOGGER.info("FIM DO PROCESSO DE GERAÇÃO DE OCR.zip");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();
	}
	private void gerarTxtHocr() {
		//log=\\BPOCSCOCR05\logs$\DNIT-DF
		//\\172.20.0.15\f$\Data\Shares\Projetos\dnit_df\tcibpo-bureau-itinerante-dnit2\2018\09\05\2785
		processoAtual= new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					if (JOptionPane.showConfirmDialog(null, "Esta rotina vai chamar o OCR Processar para gerar os arquivos txt e hocr, deseja prosseguir?",
							"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						procesando(true);
						String[] diretorios = textDir.getText().split("\\n");
						for (int i = 0; i < diretorios.length; i++) {
							String diretorio = diretorios[i];
							String log="<PROCESSANDO GERAÇÃO txt e hocr> do diretório " + diretorio;
							textLogs.append(log);
							LOGGER.info(log);
							client.gerarTxtHocr(diretorio);
							log="\n<FIM PROCESSO GERAÇÃO txt e hocr> do diretório " + diretorio+"\n";
							textLogs.append(log);
							LOGGER.info(log);
						}
						LOGGER.info("FIM DO PROCESSO DE GERAÇÃO txt e hocr");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();
	}
	private void csvVersusDiretorio() {
		processoAtual= new Thread(new Runnable() {
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
					new FileWritterUtil().writer(nomeAquivoComHorario("CSV_X_DIRETORIO.csv"),"IMAGEM;DIRETORIO;CSV", textLogs.getText().toString());
					LOGGER.info("FIM DO PROCESSO");
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();
		
	}
	
	private void removerImagens() {
		processoAtual =  new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					if (JOptionPane.showConfirmDialog(null, "Para remover a imagem deve estar no padrão diretorio;nome.extensao. Podemos prosseguir? ",
							"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						procesando(true);
						String[] linhas = textDir.getText().split("\\n");
						conversor.criarRepositorio();
						for (int i = 0; i < linhas.length; i++) {
							String linha = linhas[i];
							conversor.atualizarRepositorio(linha);
						}
						for(Diretorio diretorio: conversor.getRepositorio()) {
							textLogs.append(conversor.removerImagens(diretorio));
						}
						textLogs.append("\nFINALIZADO");
						LOGGER.info("FIM DO PROCESSO");
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();
	}
	private void removerOcrZip() {
		processoAtual= new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					textLogs.setText("");
					if (JOptionPane.showConfirmDialog(null, "Esta rotina rotina remove os arquivos ocr.zip, deseja prosseguir? ",
							"ALERTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						procesando(true);
						String[] diretorios = textDir.getText().split("\\n");
						for (int i = 0; i < diretorios.length; i++) {
							String diretorio = diretorios[i];
							textLogs.append(detalhe.removerOcrZip(diretorio));
						}
						textLogs.append("\nFINALIZADO");
						LOGGER.info("FIM DO PROCESSO");
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}finally {
					procesando(false);
				}

			}
		});
		processoAtual.start();
	}
	private void converter() {
		processoAtual =new Thread(new Runnable() {
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
								textLogs.append(conversor.converter(new Diretorio(endereco)));
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
		});
		processoAtual.start();
		
	}
	private void lerLog() {
		if(processoLog==null) {
			btnLerLog.setText("Stop LOG");
			tabbedPane.setSelectedIndex(1);
			processoLog= new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try {
							while(true) {
								inicio = Long.valueOf(txtInicial.getText());
								int limit = Integer.valueOf(txtPaginacao.getText());
								int sleep = Integer.valueOf(txtIntervaloSegundos.getText());
								LOGGER.info("Lendo log.log inicio {} limit {}", inicio,limit);
								textLogFile.setText("");
								try (BufferedReader reader = Files.newBufferedReader(
								        Paths.get(ambiente.getAppPath() +"\\logs","log.log"), StandardCharsets.UTF_8)) {
								    List<String> line = reader.lines()
								                              .skip(inicio)
								                              .limit(limit)
								                              .collect(Collectors.toList());
		
								    line.stream().forEach(l->{textLogFile.append(inicio++ +"--" +l+"\n");});
								    Thread.sleep(1000L*sleep);
								}
								txtInicial.setText(inicio.toString());
							}
						} catch (Exception e) {
							e.printStackTrace();
							LOGGER.error(e.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error(e.getMessage());
					}
				}
			});
			processoLog.start();
		}else {
			btnLerLog.setText("Start LOG");
			processoLog.interrupt();
			processoLog=null;
		}
		
		
	}
	private String nomeAquivoComHorario(String nome) {
		String horario = df.format(new Date());
		return horario + "_"+ nome;
	}
	
	

}