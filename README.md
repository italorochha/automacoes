# Motor de Extracao Autonomo (Web Scraper & API REST)

Sistema backend desenvolvido em Java com Spring Boot para execucao de rotinas automatizadas de web scraping, persistencia de dados e distribuicao via API REST.

## Arquitetura e Tecnologias

O projeto foi estruturado utilizando os principios de responsabilidade unica e separacao de conceitos.

* **Linguagem:** Java 21+
* **Framework Principal:** Spring Boot 3+
* **Web Scraping:** Selenium WebDriver (Edge Headless)
* **Persistencia:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL (via Docker)
* **Manipulacao de Arquivos:** Apache POI (Geracao de planilhas XLSX)

## Fluxo de Execucao

1. O servico de scraping (`ScraperService`) e acionado via contexto do Spring.
2. O Selenium WebDriver inicia uma instancia invisivel do navegador, navega pelas paginas alvo e extrai os dados estruturados.
3. Os dados sao convertidos em entidades de dominio e persistidos no PostgreSQL de forma transacional.
4. A API REST (`CitacaoController`) expoe os dados armazenados em formato JSON.

## Como Executar o Projeto

### Pre-requisitos

* JDK 21 ou superior
* Docker e Docker Compose
* Maven

### Passos para execucao

1. Suba a infraestrutura de banco de dados:
docker-compose up -d

2. Execute a aplicacao via Maven Wrapper:
./mvnw clean spring-boot:run

A API estara acessivel localmente na porta 8080.
A rota principal de consulta e: `GET http://localhost:8080/api/citacoes`