package com.italo.automacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.italo.automacoes.model.Citacao;

@Repository
public interface CitacaoRepository extends JpaRepository<Citacao, Long> {
}
