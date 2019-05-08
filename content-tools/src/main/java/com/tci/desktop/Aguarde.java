package com.tci.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Aguarde extends JFrame {
    private JLabel logotipo=new JLabel("Processando");
    public Aguarde() {
    	ImageIcon icon= new ImageIcon(ClassLoader.getSystemClassLoader().getResource("loading.jpg"));
    	logotipo = new JLabel(icon);
        getContentPane().add(logotipo, BorderLayout.CENTER);
        logotipo.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblVersao = new JLabel("Content Scan");
        lblVersao.setBorder(BorderFactory.createEtchedBorder());
        lblVersao.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblVersao.setForeground(Color.BLUE);
        lblVersao.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblVersao, BorderLayout.SOUTH);
        setSize(250,150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
 
    public void fechar() {
    	this.dispose();
    }
}