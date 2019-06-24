package com.springunivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springunivers.entity.EtidadeAluno;

public interface AlunoRepository extends JpaRepository<EtidadeAluno, Integer> {

}
