package com.tci.desktop;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tci.ContentTools;
import com.tci.controller.WebserviceClient;
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
public class FrmLogin extends JDialog {
	private static final Logger LOGGER = LogManager.getLogger(FrmLogin.class);
	@Autowired
	private WebserviceClient client;
	private JTextField txtLogin = new JTextField();
	private JPasswordField txtSenha = new JPasswordField();
	private final JButton cmdLogin = new JButton("Login");
	private final JPanel panel = new JPanel();
	private final JTextField txtServio = new JTextField();
	private final JButton btnCriarLote = new JButton("Criar Lote");
	public FrmLogin() {
		txtServio.setText("Service");
		txtServio.setColumns(5);
		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEmail = new JLabel("E-mail");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEmail.insets = new Insets(10, 3, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 0;
		getContentPane().add(lblEmail, gbc_lblEmail);
		
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.weightx = 1.0;
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtEmail.insets = new Insets(10, 3, 5, 3);
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 0;
		getContentPane().add(txtLogin, gbc_txtEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.insets = new Insets(3, 3, 5, 5);
		gbc_lblSenha.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 1;
		getContentPane().add(lblSenha, gbc_lblSenha);
		
		GridBagConstraints gbc_txtSenha = new GridBagConstraints();
		gbc_txtSenha.insets = new Insets(3, 3, 5, 3);
		gbc_txtSenha.weightx = 1.0;
		gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSenha.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtSenha.gridx = 1;
		gbc_txtSenha.gridy = 1;
		getContentPane().add(txtSenha, gbc_txtSenha);
		
		GridBagConstraints gbc_cmdLogin = new GridBagConstraints();
		gbc_cmdLogin.insets = new Insets(3, 3, 5, 3);
		gbc_cmdLogin.weighty = 1.0;
		gbc_cmdLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_cmdLogin.gridx = 1;
		gbc_cmdLogin.gridy = 2;
		cmdLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		getContentPane().add(cmdLogin, gbc_cmdLogin);
		setModal(true);
		setSize(300, 241);
		setLocationRelativeTo(null);
		txtLogin.setText("gleyson.sampaio@tcibpo.com");
		txtSenha.setText("12345678");
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel, gbc_panel);
		
		panel.add(txtServio);
		btnCriarLote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarLote();
			}
		});
		
		panel.add(btnCriarLote);
	}
	public void criarLote() {
		client.criarLote(44, "LOTE TESTE");
	}
	public void login() {
		try {
			String login=txtLogin.getText();
			String senha = txtSenha.getText();
			client.logar(login, senha);
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			LOGGER.error(e);
		}
		
	}
	public void setApi(String api) {
		setTitle(api);
	}
	
}
