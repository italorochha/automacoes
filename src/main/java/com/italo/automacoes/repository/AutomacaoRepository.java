package com.italo.automacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.italo.automacoes.model.Automacao;

public interface AutomacaoRepository extends JpaRepository<Automacao, Long> {
}