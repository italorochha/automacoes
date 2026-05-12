package com.italo.automacoes.scraping;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EstudoSelenium {
    static class Citacao {
        String autor;
        String texto;
        Citacao(String autor, String texto) {
            this.autor = autor;
            this.texto = texto;
        }
    }
    public static void main(String[] args) {
        System.out.println(" Iniciando Motor Fantasma Integrado...");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        List<Citacao> listaGeral = new ArrayList<>();
        try {
            driver.get("http://quotes.toscrape.com/");
            while (true) {
                List<WebElement> blocos = driver.findElements(By.className("quote"));
                for (WebElement b : blocos) {
                    listaGeral.add(new Citacao(
                        b.findElement(By.className("author")).getText(),
                        b.findElement(By.className("text")).getText()
                    ));
                }
                List<WebElement> proximo = driver.findElements(By.cssSelector(".next > a"));
                if (!proximo.isEmpty()) {
                    proximo.get(0).click();
                } else {
                    break;
                }
            }
            System.out.println(" Raspagem concluída. Itens na memória: " + listaGeral.size());
            gerarExcel(listaGeral);
        } catch (Exception e) {
            System.out.println(" Erro: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
    private static void gerarExcel(List<Citacao> dados) {
        String caminho = "relatorios_gerados/Relatorio_Citacoes_Mundiais.xlsx";
        new File("relatorios_gerados").mkdirs();
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Citações Extraídas");
            Font fonteCabecalho = workbook.createFont();
            fonteCabecalho.setBold(true);
            fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
            CellStyle estiloCabecalho = workbook.createCellStyle();
            estiloCabecalho.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
            estiloCabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            estiloCabecalho.setFont(fonteCabecalho);
            estiloCabecalho.setAlignment(HorizontalAlignment.CENTER);
            Row cabecalho = sheet.createRow(0);
            String[] colunas = {"AUTOR", "FRASE CÉLEBRE"};
            for (int i = 0; i < colunas.length; i++) {
                Cell celula = cabecalho.createCell(i);
                celula.setCellValue(colunas[i]);
                celula.setCellStyle(estiloCabecalho);
            }
            int indiceLinha = 1;
            for (Citacao c : dados) {
                Row linha = sheet.createRow(indiceLinha++);
                linha.createCell(0).setCellValue(c.autor);
                linha.createCell(1).setCellValue(c.texto);
            }
            sheet.autoSizeColumn(0);
            sheet.setColumnWidth(1, 15000);
            try (FileOutputStream out = new FileOutputStream(caminho)) {
                workbook.write(out);
            }
            System.out.println(" EXCEL GERADO COM SUCESSO EM: " + caminho);
        } catch (Exception e) {
            System.out.println(" Erro ao criar Excel: " + e.getMessage());
        }
    }
}