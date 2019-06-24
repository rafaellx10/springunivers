package br.com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.entity.EtidadeAluno;

public interface AlunoRepository extends JpaRepository<EtidadeAluno, Integer> {

}
