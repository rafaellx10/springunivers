package com.tci.desktop;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Loading extends JPanel {
	private JLabel img=new JLabel("CONTENT");
	private JLabel text = new JLabel("PROCESSANDO ... ");
	public Loading() {
		text.setFont(new Font("Tahoma", Font.BOLD, 13));
		text.setForeground(Color.BLUE);
		//setImg("loading.gif");
		add(img);
		add(text);
		ocultar();
	}
	private void setImg(String imagem) {
		//ImageIcon icon= new ImageIcon(ClassLoader.getSystemClassLoader().getResource("loading.jpg"));
		ImageIcon icon= new ImageIcon(ClassLoader.getSystemClassLoader().getResource(imagem));
    	img = new JLabel(icon);
	}
	public void ocultar() {
		exibir(false);
	}
	public void exibir() {
		exibir(true);
	}
	public void exibir(boolean exibir) {
		this.setVisible(exibir);
	}
}
