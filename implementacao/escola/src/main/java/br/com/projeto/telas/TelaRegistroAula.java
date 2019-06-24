package br.com.projeto.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;

public class TelaRegistroAula extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textField_Aluno;
	private JTextField textField_Resumo;
	private MaskFormatter data;
	private JTextField textField_DataVenc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRegistroAula frame = new TelaRegistroAula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaRegistroAula() throws ParseException {
		setTitle("REGISTRO DE AULAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCdigo = new JLabel("CÓDIGO");
		lblCdigo.setBounds(10, 44, 46, 14);
		panel.add(lblCdigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(66, 41, 102, 20);
		panel.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DATA");
		lblNewLabel.setBounds(10, 88, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblAluno = new JLabel("ALUNO");
		lblAluno.setBounds(10, 133, 46, 14);
		panel.add(lblAluno);
		
		textField_Aluno = new JTextField();
		textField_Aluno.setBounds(68, 130, 289, 20);
		panel.add(textField_Aluno);
		textField_Aluno.setColumns(10);
		
		JLabel lblResumoDoDia = new JLabel("RESUMO DO DIA");
		lblResumoDoDia.setBounds(10, 185, 93, 14);
		panel.add(lblResumoDoDia);
		
		textField_Resumo = new JTextField();
		textField_Resumo.setBounds(100, 182, 337, 28);
		panel.add(textField_Resumo);
		textField_Resumo.setColumns(10);
		
		this.data= new MaskFormatter("##/##/####");
		this.data.setPlaceholderCharacter('_');
		
		JFormattedTextField formattedData = new JFormattedTextField(data);
		formattedData.setBounds(66, 85, 102, 20);
		textField_DataVenc = formattedData;
		panel.add(formattedData);
		setSize(640,480);
		setLocationRelativeTo(null);
	}
	
	
	
}
