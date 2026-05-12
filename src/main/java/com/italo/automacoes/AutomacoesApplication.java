package com.italo.automacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomacoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomacoesApplication.class, args);
    }
}
