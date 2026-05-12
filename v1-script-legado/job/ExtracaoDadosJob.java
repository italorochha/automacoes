package com.italo.automacoes.job;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.italo.automacoes.model.Automacao;
import com.italo.automacoes.repository.AutomacaoRepository;

@Component
public class ExtracaoDadosJob {

    private static final Logger logger = LoggerFactory.getLogger(ExtracaoDadosJob.class);
    private final AutomacaoRepository repository;

    public ExtracaoDadosJob(AutomacaoRepository repository) {
        this.repository = repository;
    }
    @Scheduled(cron = "0 0 8 * * MON-FRI")
    public void rotinaPrincipal() {
        logger.info(" INICIANDO A VARREDURA DE TAREFAS NO BANCO DE DADOS...");
        List<Automacao> tarefas = repository.findAll();

        if (tarefas.isEmpty()) {
            logger.warn(" Nenhuma automação cadastrada para processar.");
            return;
        }

        for (Automacao tarefa : tarefas) {
            if (tarefa.isStatusAtivo()) {
                logger.info(" Processando Automação ID: " + tarefa.getId() + " - " + tarefa.getNomeTarefa());
                gerarPlanilhaFisica(tarefa);
            }
        }
        logger.info("💤 ROTINA DE EXTRAÇÃO CONCLUÍDA. O sistema volta a dormir.");
    }
    private void gerarPlanilhaFisica(Automacao tarefa) {
        String nomeDiretorio = "relatorios_gerados";
        new File(nomeDiretorio).mkdirs();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        String nomeArquivo = nomeDiretorio + "/Extracao_" + tarefa.getSistemaOrigem() + "_" + timestamp + ".xlsx";
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Dados Extraidos");
            Row linhaCabecalho = sheet.createRow(0);
            linhaCabecalho.createCell(0).setCellValue("ID Tarefa");
            linhaCabecalho.createCell(1).setCellValue("Nome da Rotina");
            linhaCabecalho.createCell(2).setCellValue("Data Base (Âncora)");
            linhaCabecalho.createCell(3).setCellValue("Status Extração");
            Row linhaDados = sheet.createRow(1);
            linhaDados.createCell(0).setCellValue(tarefa.getId());
            linhaDados.createCell(1).setCellValue(tarefa.getNomeTarefa());
            linhaDados.createCell(2).setCellValue(tarefa.getDataAncora().toString());
            linhaDados.createCell(3).setCellValue("SUCESSO - Dados Consolidados");
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream outputStream = new FileOutputStream(nomeArquivo)) {
                workbook.write(outputStream);
            }
            logger.info(" PLANILHA GERADA NO DISCO: " + nomeArquivo);
        } catch (Exception e) {
            logger.error(" ERRO GRAVE AO CRIAR O EXCEL: " + e.getMessage());
        }
    }
}