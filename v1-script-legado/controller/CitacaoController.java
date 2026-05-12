package com.italo.automacoes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italo.automacoes.model.Citacao;
import com.italo.automacoes.repository.CitacaoRepository;

@RestController
@RequestMapping("/api/citacoes")
public class CitacaoController {

    private final CitacaoRepository repository;
    public CitacaoController(CitacaoRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Citacao> listarTodas() {
        return repository.findAll();
    }
}