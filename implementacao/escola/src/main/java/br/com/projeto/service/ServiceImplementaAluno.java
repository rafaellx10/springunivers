package br.com.projeto.service;

import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.entity.EntidadeRegistroAula;
import br.com.projeto.entity.EtidadeAluno;
import br.com.projeto.repository.AlunoRepository;

@Service
@Transactional
public class ServiceImplementaAluno implements AutoServiceAluno {

	@Autowired
	private AlunoRepository repository;
	
	@Override
	public void salvar(EtidadeAluno aluno) {
		//valida os dados
		if (aluno == null) {
			throw new ValidationException("Dados do aluno devem ser informados");
		}
		if (StringUtils.isBlank(aluno.getNome())) {
			throw new ValidationException("Nome do aluno deve ser informado");
		}
		if (StringUtils.isBlank(aluno.getEmail())) {
			throw new ValidationException("Email do aluno deve ser informado");
		}
		repository.save(aluno);
		
	}

	@Override
	public void editar(EtidadeAluno aluno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EtidadeAluno buscarId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<EtidadeAluno> buscarTodos() {
		
		
		return null;
	}
	
	
	
	

	 
}
