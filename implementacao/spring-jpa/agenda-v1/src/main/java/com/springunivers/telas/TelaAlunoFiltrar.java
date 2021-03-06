package com.springunivers.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springunivers.entity.EtidadeAluno;
import com.springunivers.repository.AlunoRepository;
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)	
public class TelaAlunoFiltrar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	@Autowired
    private AlunoRepository repository;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaAlunoFiltrar() {
		setTitle("Buscar Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 124, 484, 241);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setSize(640,480);
		setLocationRelativeTo(null);
	}
	public void carregar() {
		buscarTodos();
	}
	private void buscarTodos() {
		
		DefaultTableModel ttable = new DefaultTableModel();
		ttable.addColumn("Código");
		ttable.addColumn("Nome");
		ttable.addColumn("E-mail");
		ttable.addColumn("Whatzap");
		ttable.addColumn("Data de Vencimento");
		Iterable<EtidadeAluno> findAll = repository.findAll();
		for(EtidadeAluno a : findAll) {
			ttable.addRow(new Object[] {
					a.getDataVencimento(),
					a.getId(),
					a.getNome(),
					a.getEmail(),
					a.getWhatzap(),
					a.getDataVencimento()});	
		}
		
		
		
		
	}
}
