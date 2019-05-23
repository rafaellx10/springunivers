package com.tci.desktop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JDialog {
	private JTextField txtLogin = new JTextField();
	private JPasswordField txtSenha = new JPasswordField();
	private final JButton cmdLogin = new JButton("Login");
	public FrmLogin() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEmail = new JLabel("E-mail");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEmail.insets = new Insets(10, 3, 0, 0);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 0;
		getContentPane().add(lblEmail, gbc_lblEmail);
		
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.weightx = 1.0;
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtEmail.insets = new Insets(10, 3, 0, 3);
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 0;
		getContentPane().add(txtLogin, gbc_txtEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.insets = new Insets(3, 3, 5, 0);
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
		gbc_cmdLogin.insets = new Insets(3, 3, 3, 3);
		gbc_cmdLogin.weighty = 1.0;
		gbc_cmdLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_cmdLogin.gridx = 1;
		gbc_cmdLogin.gridy = 2;
		cmdLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(cmdLogin, gbc_cmdLogin);
		setModal(true);
		setSize(300, 150);
		setLocationRelativeTo(null);
	}
	
	public String getLogin() {
		return txtLogin.getText();
	}
	public String getSenha() {
		return txtSenha.getText();
	}
}
