package com.italo.automacoes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.italo.automacoes.infra.GerenciadorPlanilha;
import com.italo.automacoes.modelo.RegistroPlanilha;
import com.italo.automacoes.negocio.ProcessadorDados;

@Component
public class AutomacaoMain {
    private static final Logger logger = Logger.getLogger(AutomacaoMain.class.getName());
    private final GerenciadorPlanilha gerenciador;
    private final ProcessadorDados processador;

    public AutomacaoMain(GerenciadorPlanilha gerenciador, ProcessadorDados processador) {
        this.gerenciador = gerenciador;
        this.processador = processador;
    }
@Scheduled(cron = "0 0 8 * * MON-FRI")
    public void executarRotinaDiaria() {
        String caminhoArquivo = "C:/dados/planilha_estoque.xlsx";
        logger.info("=== Iniciando a rotina AUTOMATICA de atualizacao de planilhas ===");

        try {
            List<RegistroPlanilha> dadosBrutos = gerenciador.extrairDados(caminhoArquivo);
            logger.log(Level.INFO, "Foram extraidos {0} registros com sucesso.", dadosBrutos.size());

            List<RegistroPlanilha> dadosProcessados = processador.atualizarRegistros(dadosBrutos);
            logger.info("Regras de negocio aplicadas aos registros.");

            gerenciador.salvarPlanilha(dadosProcessados, caminhoArquivo);
            logger.info("Planilha atualizada e salva com sucesso.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha critica ao processar a planilha: {0}", e.getMessage());
        }
    }
}
