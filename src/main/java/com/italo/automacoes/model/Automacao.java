package com.italo.automacoes.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_automacoes")
@Data
public class Automacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTarefa;

    @Column(nullable = false)
    private String sistemaOrigem;

    @Column(nullable = false)
    private LocalDate dataAncora;

    private boolean statusAtivo;

}
