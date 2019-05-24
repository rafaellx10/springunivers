package com.springunivers.desktop;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
public class FormularioAgenda extends JFrame {
	private JPanel painelCampos = new JPanel();
	private JPanel painelTabela = new JPanel();
	private JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, painelCampos, painelTabela);
	private JTextField tNome;
	private JTextField tSobrenome;
	private JTextField tDdd;
	private JTextField tTelefone;
	private JTextField tUf;
	private JTextField tCidade;
	
	public FormularioAgenda() {
		painelCampos.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		painelCampos.add(lblNome);
		
		tNome = new JTextField();
		tNome.setBounds(66, 8, 208, 20);
		painelCampos.add(tNome);
		tNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(10, 38, 59, 14);
		painelCampos.add(lblSobrenome);
		
		tSobrenome = new JTextField();
		tSobrenome.setColumns(10);
		tSobrenome.setBounds(66, 35, 208, 20);
		painelCampos.add(tSobrenome);
		
		JLabel lblDdd = new JLabel("DDD");
		lblDdd.setBounds(10, 65, 46, 14);
		painelCampos.add(lblDdd);
		
		tDdd = new JTextField();
		tDdd.setColumns(10);
		tDdd.setBounds(66, 62, 35, 20);
		painelCampos.add(tDdd);
		
		JLabel lblFone = new JLabel("Telefone");
		lblFone.setBounds(104, 66, 46, 14);
		painelCampos.add(lblFone);
		
		tTelefone = new JTextField();
		tTelefone.setColumns(10);
		tTelefone.setBounds(150, 62, 124, 20);
		painelCampos.add(tTelefone);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(10, 93, 46, 14);
		painelCampos.add(lblUf);
		
		tUf = new JTextField();
		tUf.setColumns(10);
		tUf.setBounds(66, 90, 35, 20);
		painelCampos.add(tUf);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(104, 94, 46, 14);
		painelCampos.add(lblCidade);
		
		tCidade = new JTextField();
		tCidade.setColumns(10);
		tCidade.setBounds(150, 90, 124, 20);
		painelCampos.add(tCidade);
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btSalvar.setBounds(61, 125, 89, 23);
		painelCampos.add(btSalvar);
		
		JButton btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btFechar.setBounds(150, 125, 89, 23);
		painelCampos.add(btFechar);
		split.setDividerLocation(150);
		split.setOneTouchExpandable(true); 
		getContentPane().setLayout( new BorderLayout());
		getContentPane().add(split, BorderLayout.CENTER);
	}
	public void exibir() {
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
