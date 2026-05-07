package com.italo.automacoes.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.italo.automacoes.dto.AutomacaoDTO;
import com.italo.automacoes.model.Automacao;
import com.italo.automacoes.repository.AutomacaoRepository;

@Service
public class AutomacaoService {
    @Autowired
    private AutomacaoRepository repository;
    public Automacao salvarAutomacao(AutomacaoDTO dto) {
        var automacao = new Automacao();
        BeanUtils.copyProperties(dto, automacao);
        return repository.save(automacao);
    }
    public List<Automacao> listarTodas() {
        return repository.findAll();
    }
}