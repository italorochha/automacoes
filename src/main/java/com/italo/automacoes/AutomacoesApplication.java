package com.italo.automacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.italo.automacoes.scraping.ScraperService;

@SpringBootApplication
@EnableScheduling
public class AutomacoesApplication {
    public static void main(String[] args) {
        org.springframework.context.ApplicationContext contexto = SpringApplication.run(AutomacoesApplication.class, args);

        ScraperService scraperService = contexto.getBean(ScraperService.class);

        scraperService.executarExtracaoParaBanco();
    }
}
