package com.italo.automacoes.infra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.italo.automacoes.modelo.RegistroPlanilha;

@Service
public class GerenciadorPlanilha {

    public List<RegistroPlanilha> extrairDados(String caminhoArquivo) throws Exception {
        List<RegistroPlanilha> registros = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(caminhoArquivo);
        Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                String codigo = row.getCell(0).getStringCellValue();
                int quantidade = (int) row.getCell(1).getNumericCellValue();
                boolean atualizado = false;
                if (row.getCell(2) != null) {
                    atualizado = row.getCell(2).getBooleanCellValue();
                }
                registros.add(new RegistroPlanilha(codigo, quantidade, atualizado));
            }
        }
        return registros;
    }
    public void salvarPlanilha(List<RegistroPlanilha> registros, String caminhoArquivo) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Estoque Atualizado");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Codigo Item");
            headerRow.createCell(1).setCellValue("Quantidade");
            headerRow.createCell(2).setCellValue("Atualizado (Status)");
            int rowNum = 1;
            for (RegistroPlanilha registro : registros) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(registro.codigoItem());
                row.createCell(1).setCellValue(registro.quantidade());
                row.createCell(2).setCellValue(registro.atualizado());
            }
            try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
                workbook.write(fileOut);
            }
        }
    }
}