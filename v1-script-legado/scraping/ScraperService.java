package com.italo.automacoes.scraping;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.stereotype.Service;

import com.italo.automacoes.model.Citacao;
import com.italo.automacoes.repository.CitacaoRepository;

@Service
public class ScraperService {
    private final CitacaoRepository repository;
    public ScraperService(CitacaoRepository repository) {
        this.repository = repository;
    }
    public void executarExtracaoParaBanco() {
        System.out.println(" Iniciando Motor Fantasma com persistência em PostgreSQL...");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        List<Citacao> listaParaSalvar = new ArrayList<>();
        try {
            driver.get("http://quotes.toscrape.com/");
            while (true) {
                List<WebElement> blocos = driver.findElements(By.className("quote"));
                for (WebElement b : blocos) {
                    // Criamos o objeto da nossa Entidade
                    Citacao novaCitacao = new Citacao(
                        b.findElement(By.className("author")).getText(),
                        b.findElement(By.className("text")).getText()
                    );
                    listaParaSalvar.add(novaCitacao);
                }
                List<WebElement> proximo = driver.findElements(By.cssSelector(".next > a"));
                if (!proximo.isEmpty()) {
                    proximo.get(0).click();
                } else {
                    break;
                }
            }
            System.out.println(" Salvando " + listaParaSalvar.size() + " registros no banco de dados...");
            repository.saveAll(listaParaSalvar);
            System.out.println(" Todos os dados estão seguros no PostgreSQL!");
        } catch (Exception e) {
            System.err.println(" Erro na extração: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}