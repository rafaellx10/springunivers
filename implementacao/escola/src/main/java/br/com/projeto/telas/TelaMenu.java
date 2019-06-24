package br.com.projeto.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import br.com.projeto.ProjetoApplication;

import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

@Component
public class TelaMenu extends JFrame {

	private JPanel contentPane;
	
	
	
	
	public TelaMenu() {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		
		setBackground(Color.BLACK);
		setTitle("PERSONAL -Gestão do Educador Fisico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setExtendedState(MAXIMIZED_BOTH);
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("CADASTROS");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmUsuario = new JMenuItem("Usuario");
		mnCadastros.add(mntmUsuario);
		
		JMenuItem mntmAlunos = new JMenuItem("Alunos");
		mntmAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//TODO:REVER ESTE CODIGO
					TelaAlunoFiltrar cliente =ProjetoApplication.getBean(TelaAlunoFiltrar.class);
					// porque vc colocou o metodo aqui? - jaja
					cliente.carregar();
					cliente.setVisible(true);						
				}
				
			
		});
		mnCadastros.add(mntmAlunos);
		
		JMenu mnRegistroDeAula = new JMenu("REGISTROS DE AULA");
		mnRegistroDeAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuBar.add(mnRegistroDeAula);
		
		JMenuItem mntmAdicionarAula = new JMenuItem("Adicionar Aula");
		mntmAdicionarAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRegistroAula telR;
				try {
					telR = new TelaRegistroAula();
					telR.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnRegistroDeAula.add(mntmAdicionarAula);
		
		JMenu mnRelatrio = new JMenu("RELATÓRIO");
		mnRelatrio.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnRelatrio);
		
		JMenu mnSair = new JMenu("SAIR");
		menuBar.add(mnSair);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnSair.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
        
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		
		
	}

}
