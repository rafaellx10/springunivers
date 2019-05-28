package com.springunivers.desktop;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springunivers.dao.ContatoDao;
import com.springunivers.model.Contato;
import com.springunivers.repository.ContatoRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
public class FormularioAgenda extends JFrame {
	private JTextField tNome;
	private JTextField tSobrenome;
	private JTextField tDdd;
	private JTextField tTelefone;
	private JTextField tUf;
	private JTextField tCidade;
	
	@Autowired
	private ContatoRepository repositorio;
	@Autowired
	private ContatoDao dao;
	
	private Contato contato;
	private ContatoTableModel modelo;
	private JTable tabela = new JTable(ContatoTableModel.vazio());
	private JTextField tFiltro;
	public FormularioAgenda() {
		JPanel painelCampos = new JPanel();
		
		JScrollPane barraRolagem = new JScrollPane();
		barraRolagem.setViewportView(tabela);
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, painelCampos, barraRolagem);
		
		
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
				salvar();
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
		
		JLabel lblFiltro = new JLabel("FILTRO");
		lblFiltro.setBounds(10, 174, 46, 14);
		painelCampos.add(lblFiltro);
		
		tFiltro = new JTextField();
		tFiltro.setBounds(64, 171, 150, 20);
		painelCampos.add(tFiltro);
		tFiltro.setColumns(10);
		
		JButton btnFiltar = new JButton("Filtar");
		btnFiltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarComFiltro();
			}
		});
		btnFiltar.setBounds(215, 170, 59, 23);
		painelCampos.add(btnFiltar);
		split.setDividerLocation(200);
		split.setOneTouchExpandable(true); 
		getContentPane().setLayout( new BorderLayout());
		getContentPane().add(split, BorderLayout.CENTER);
		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				carregar();
			}
		});
	}
	public void exibir() {
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		listar();
	}
	private void salvar() {
		if(contato==null)
			contato = new Contato();
		
		contato.setNome(tNome.getText());
		contato.setSobrenome(tSobrenome.getText());
		contato.setDdd(Integer.valueOf(tDdd.getText()));
		contato.setNumero(Long.valueOf(tTelefone.getText()));
		contato.setCidade(tCidade.getText());
		contato.setEstado(tUf.getText());
		//repositorio.save(contato);
		if(contato.getId()==null)
			dao.incluir(contato);
		else
			dao.alterar(contato);
		listar();
		JOptionPane.showMessageDialog(null, "SALVAMOS O CONTATO " + contato.getNome());
	}
	private void carregar() {
		int linhaSelecionada = -1;
		linhaSelecionada = tabela.getSelectedRow();
		if (linhaSelecionada >= 0) {
			contato = modelo.getContato(linhaSelecionada);
			System.out.println(dao.getNomeComTelefone(contato.getId()));
		} else {
			JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
		}
		if(contato!=null) {
			tNome.setText(contato.getNome());
			tSobrenome.setText(contato.getSobrenome());
			tDdd.setText(contato.getDdd().toString());
			tTelefone.setText(contato.getNumero().toString());
			tCidade.setText(contato.getCidade());
			tUf.setText(contato.getEstado());
		}else {
			tNome.setText("");
			tSobrenome.setText("");
			tDdd.setText("");
			tTelefone.setText("");
			tCidade.setText("");
			tUf.setText("");
		}
		 
		 
		 
	}
	private void listar() {
		//List<Contato>lista = dao.findAll();
		List<Contato>lista = dao.findAllCriteria();
	    modelo = new ContatoTableModel(lista);
	    tabela.setModel(modelo);  
	}
	private void listarComFiltro() {
		String filtro = tFiltro.getText();
		List<Contato>lista = dao.findByNomeContaining(filtro);
		modelo = new ContatoTableModel(lista);
	    tabela.setModel(modelo);  
	}
}
