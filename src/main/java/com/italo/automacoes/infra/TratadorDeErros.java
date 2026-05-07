package com.italo.automacoes.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> tratarErroValidacao(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacaoDTO::new).toList());
    }
    public record ErroValidacaoDTO(String campo, String mensagem) {
        public ErroValidacaoDTO(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}