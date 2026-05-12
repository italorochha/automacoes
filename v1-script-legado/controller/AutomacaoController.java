package com.italo.automacoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italo.automacoes.dto.AutomacaoDTO;
import com.italo.automacoes.model.Automacao;
import com.italo.automacoes.service.AutomacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/automacoes")
public class AutomacaoController {
    @Autowired
    private AutomacaoService service;

@PostMapping
    public ResponseEntity<Automacao> salvar(@RequestBody @Valid AutomacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarAutomacao(dto));
    }

    @GetMapping
    public ResponseEntity<List<Automacao>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }
}