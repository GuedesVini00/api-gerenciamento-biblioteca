# API de Gerenciamento de Biblioteca

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Hibernate-59666C)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D)

Uma API REST para gerenciamento de biblioteca, desenvolvida com Java e Spring Boot.

O sistema permite controlar obras, cópias, leitores, funcionários, empréstimos e reservas, aplicando regras de negócio e boas práticas de desenvolvimento backend.

## Funcionalidades

- CRUD de obras
- Gerenciamento de cópias
- Gerenciamento de leitores
- Gerenciamento de funcionários
- Gerenciamento de empréstimos
- Gerenciamento de reservas
- Validação de regras de negócio
- Tratamento global de exceções
- Utilização do padrão DTO
- Arquitetura RESTful

## Tecnologias

- Java 26
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Swagger/OpenAPI

## Estrutura do Projeto

- Controllers
- Services
- Repositories
- DTOs
- Model
- Enums
- Exception Handler

## Regras de Negócio

- Uma cópia só pode ser emprestada se estiver disponível.
- Um leitor possui um limite de empréstimos ativos.
- Reservas são validadas antes da criação de um empréstimo.
- Não é permitido criar um empréstimo com data de devolução anterior à data do empréstimo.
- O status da cópia é atualizado automaticamente de acordo com a operação realizada.

## Banco de Dados

O projeto utiliza PostgreSQL para persistência dos dados.

Principais entidades:

- Obra
- Cópia
- Leitor
- Funcionário
- Empréstimo
- Reserva

## Como executar o projeto

### Pré-requisitos

- Java 26
- Maven
- PostgreSQL

### Clonar o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```
### Configurar o banco de dados
Criar um banco PostgreSQL e configurar as informações no arquivo:
src/main/resources/application.properties

### Executar Aplicação
```bash
mvn spring-boot:run
```
## Documentação da API (Swagger) 

A documentação da API foi desenvolvida utilizando Swagger/OpenAPI, permitindo visualizar e testar os endpoints disponíveis.

- Acesse a documentação:
 🔗 http://localhost:8080/swagger-ui/index.html 

## Endpoints principais
- `/obras`
- `/copias`
- `/leitor`
- `/funcionario`
- `/emprestimo`
- `/reserva`
## Autor 
**Vinicius Guedes**
