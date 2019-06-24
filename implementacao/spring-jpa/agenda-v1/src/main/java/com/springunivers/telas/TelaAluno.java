package com.springunivers.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springunivers.entity.EtidadeAluno;
import com.springunivers.services.ServiceImplementaAluno;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
public class TelaAluno extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textField_Nome;
	private JTextField textField_Email;
	private JTextField textField_DataVenc;
    private JTextField  textField_Wats;
    private JTextField textFieldFiltracodigo;
	private JTextField textField_FiltraNome;
	
	private MaskFormatter mascaraData;
    private MaskFormatter mascaraTel;
	
    
    @Autowired
    
	//private ApplicationContext context;
	
	private ServiceImplementaAluno serviceAluno;
	private EtidadeAluno aluno;
	
    
    

	

	

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaAluno() throws ParseException {
		setTitle("CADASTRO DE ALUNO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(640,480);
		setLocationRelativeTo(null);
		
		JLabel lblCdigo = new JLabel("CÓDIGO");
		lblCdigo.setBounds(10, 91, 61, 14);
		contentPane.add(lblCdigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(94, 88, 86, 20);
		contentPane.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 133, 61, 14);
		contentPane.add(lblNome);
		
		textField_Nome = new JTextField();
		textField_Nome.setBounds(94, 130, 275, 20);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 176, 61, 14);
		contentPane.add(lblEmail);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(94, 173, 275, 20);
		contentPane.add(textField_Email);
		textField_Email.setColumns(10);
		
		JLabel lblWhatzap = new JLabel("Whatzap");
		lblWhatzap.setBounds(10, 217, 61, 14);
		contentPane.add(lblWhatzap);
		
		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(14, 253, 142, 14);
		contentPane.add(lblDataDeVencimento);
		
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(70, 298, 75, 23);
		contentPane.add(btnIncluir);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(362, 298, 75, 23);
		contentPane.add(btnDeletar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(278, 298, 75, 23);
		contentPane.add(btnEditar);
		
		
		
		this.mascaraData = new MaskFormatter("##/##/####");
		this.mascaraData.setPlaceholderCharacter('_');
		
		JFormattedTextField texto_DataVenc = new JFormattedTextField(mascaraData);
		texto_DataVenc.setBounds(166, 250, 118, 20);
		textField_DataVenc = texto_DataVenc;
		contentPane.add(textField_DataVenc);
		
		JButton btnTestar = new JButton("Salvar");
		btnTestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar(e);
			}
		});
		btnTestar.setBounds(166, 298, 89, 23);
		contentPane.add(btnTestar);
		
		this.mascaraTel= new MaskFormatter("(##)#####-####");
		this.mascaraTel.setPlaceholderCharacter('_');
		
		JFormattedTextField formatteWats = new JFormattedTextField(mascaraTel);
		formatteWats.setBounds(94, 214, 198, 20);
		textField_Wats = formatteWats;
		contentPane.add(formatteWats);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(209, 58, 46, 14);
		contentPane.add(lblFiltro);
		
		textFieldFiltracodigo = new JTextField();
		textFieldFiltracodigo.setBounds(265, 55, 86, 20);
		contentPane.add(textFieldFiltracodigo);
		textFieldFiltracodigo.setColumns(10);
		
		textField_FiltraNome = new JTextField();
		textField_FiltraNome.setBounds(362, 55, 86, 20);
		contentPane.add(textField_FiltraNome);
		textField_FiltraNome.setColumns(10);
		
		JButton btnFiltrar = new JButton("filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnFiltrar.setBounds(394, 87, 75, 23);
		contentPane.add(btnFiltrar);
		//formatarData();
		
		
		
		
	}
	
	

	
	
	protected void liparText() {
		this.textFieldCodigo.setText("");
		this.textField_Nome.setText(" ");
		this.textField_Email.setText(" ");
		this.textField_Wats.setText(" ");
		this.textField_DataVenc.setText(" ");
		

		}
	
	
			
			
		protected void salvar(ActionEvent e) {
			try {
				
				if (aluno == null) {
					aluno = new EtidadeAluno();
				}
				
				aluno.setNome(textField_Nome.getText());
				aluno.setEmail(textField_Email.getText());
				aluno.setWhatzap(textField_Wats.getText());
				
				serviceAluno.salvar(aluno);
				JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
				
				liparText();
				
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
}
