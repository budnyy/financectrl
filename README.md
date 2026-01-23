# 💸 FinanceCTRL

🌎 [English version](/README.en.md)

## 📌 Resumo

Aplicação web para controle financeiro, com foco em gerenciamento de despesas, atualizações em tempo real, e exportação em CSV para ferramentas de análise externas.

## 🎯 Objetivo

O objetivo desse projeto foi **aprender** mais sobre **desenvolvimento backend**, **estrutura API REST**, **padrão de design MVC**, fundamentos do **Spring Boot** e suas conexões com HTML usando **Thymeleaf**.

Além disso, foquei em desenvolver funcionalidades essenciais para uso real, como atualizar o banco de dados sem precisar recarregar a página, para a qual usei a função ` fetch ` do Javascript.

## 🛠️ Tecnologias usadas

### ⚙️ Backend
* Java 
* Spring Boot
* PostgreSQL
* Thymeleaf 

### 💻 Frontend
* HTML
* CSS
* Javascript (funções fetch)

## ☑️ Escopo do projeto
* Criar usuário
* Criar despesa
* Ler despesas
* Editar despesa
* Deletar despesa
* Exportar despesas em um arquivo .csv
* Ler a soma das despesas (todas / último mês)

## 🧠 Conceitos aplicados
* Padrão de Design MVC, utilizando Model, View, Controller (e Service)
* Javascript para buscar endpoints da API Java e manipular UI
* HTML e CSS para formar a estrutura web

## ▶️ Como usar
1. Clone este repositório
2. Abra em uma IDE (como IntelliJ IDEA)
3. Crie uma database no PostgreSQL
4. Configure as credenciais da database em `application.properties`
5. Execute a aplicação Spring Boot
6. Abra ` http://localhost:8080/financectrl/user ` no seu browser

## 🚧 Limitações e possíveis melhorias
* Finalização melhor do frontend usando Bootstrap
* Filtros de data/valor para a tabela de despesas
* Categorias para despesas recorrentes
* Distribuição usando Docker ou AWS EC2
* Gerenciamento de usuário (deletar usuário, atualizar perfil, etc) 
* Swagger para visualizar endpoints da API
