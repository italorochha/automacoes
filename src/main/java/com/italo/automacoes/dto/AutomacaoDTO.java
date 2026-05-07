package com.italo.automacoes.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutomacaoDTO(
        @NotBlank(message = "O nome da tarefa não pode estar vazio!")
        String nomeTarefa,
        @NotBlank(message = "O sistema de origem é obrigatório!")
        String sistemaOrigem,
        @NotNull(message = "A data âncora é obrigatória para a lógica de extração!")
        LocalDate dataAncora,
        boolean statusAtivo
) {
}