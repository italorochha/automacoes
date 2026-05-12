# Automação de Processamento de Dados (Java & Spring Boot)

[Português] | [English](#english)

## 🇧🇷 Português

### Descrição do Projeto
Este sistema foi desenvolvido para automatizar a rotina de extração, processamento e atualização de dados em planilhas corporativas da **Atual Auto Peças**. O objetivo principal é garantir a integridade dos dados de estoque e compras, eliminando falhas manuais e otimizando o tempo de operação.

O projeto evoluiu de um script procedural simples para uma aplicação robusta utilizando o ecossistema **Spring**.

### Arquitetura e Tecnologias
Para garantir um código limpo e de fácil manutenção, utilizei os princípios **SOLID** e a separação em camadas:
- **Modelo (Model):** Estruturas de dados imutáveis (Java Records).
- **Infraestrutura (Infra):** Manipulação de arquivos físicos utilizando **Apache POI**.
- **Negócio (Business):** Lógica de processamento e validação isolada.
- **Spring Boot Scheduler:** Automação de tarefas agendadas via expressões Cron.

### Funcionalidades
- **Extração Automática:** Leitura de arquivos `.xlsx` protegendo a memória do sistema (Try-with-resources).
- **Agendamento Profissional:** Execução automática em horários configuráveis.
- **Logs de Auditoria:** Monitoramento completo da execução via Logger profissional (SLF4J/Logback).

---

## 🇺🇸 English <a name="english"></a>

### Project Description
This system was developed to automate the extraction, processing, and updating of data in corporate spreadsheets for **Atual Auto Peças**. The main goal is to ensure the integrity of inventory and purchasing data, eliminating manual errors and optimizing operational time.

The project evolved from a simple procedural script to a robust application using the **Spring** ecosystem.

### Architecture and Technologies
To ensure clean and maintainable code, I applied **SOLID** principles and layered architecture:
- **Model:** Immutable data structures (Java Records).
- **Infrastructure:** Physical file manipulation using **Apache POI**.
- **Business:** Isolated processing and validation logic.
- **Spring Boot Scheduler:** Automated task scheduling via Cron expressions.

### Key Features
- **Automatic Extraction:** Reading `.xlsx` files with system memory protection (Try-with-resources).
- **Professional Scheduling:** Automatic execution at configurable times.
- **Audit Logs:** Full execution monitoring via professional Logger (SLF4J/Logback).

---
**Desenvolvido por / Developed by:** Italo Rocha de Almeida Ferreira