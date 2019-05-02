package com.springunivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springunivers.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
