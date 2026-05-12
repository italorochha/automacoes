package com.italo.automacoes.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.italo.automacoes.modelo.RegistroPlanilha;

@Service
public class ProcessadorDados {
    public List<RegistroPlanilha> atualizarRegistros(List<RegistroPlanilha> registrosBrutos) {
        List<RegistroPlanilha> registrosAtualizados = new ArrayList<>();
        for (RegistroPlanilha registro : registrosBrutos) {
            int novaQuantidade = registro.quantidade() + 20;
            registrosAtualizados.add(new RegistroPlanilha(registro.codigoItem(), novaQuantidade, true));
        }
        return registrosAtualizados;
    }
}