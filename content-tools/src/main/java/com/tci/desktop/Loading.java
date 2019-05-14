package com.tci.desktop;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tci.util.ImagemUtil;

public class Loading extends JPanel {
	private JLabel img=new JLabel("CONTENT");
	private JLabel text = new JLabel("PROCESSANDO ... ");
	public Loading() {
		text.setFont(new Font("Tahoma", Font.BOLD, 13));
		text.setForeground(Color.BLUE);
		img = new JLabel(ImagemUtil.gif("loading"));
		add(img);
		add(text);
		ocultar();
	}
	private void setGif(String gif) {
		img = new JLabel(ImagemUtil.gif(gif));
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
