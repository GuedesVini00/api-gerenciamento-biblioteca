# API de Gerenciamento de Biblioteca

Uma API REST para gerenciamento de bibliotecas, desenvolvida com Java e Spring Boot.

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

## Estrutura do Projeto

- Controllers
- Services
- Repositories
- DTOs
- Models (Entidades)
- Enums
- Tratamento de Exceções

## Regras de Negócio

- Uma cópia só pode ser emprestada se estiver disponível.
- Um leitor possui um limite de empréstimos ativos.
- Reservas são validadas antes da criação de um empréstimo.
- Não é permitido criar um empréstimo com data de devolução anterior à data do empréstimo.
- O status da cópia é atualizado automaticamente de acordo com a operação realizada.

## Autor

**Vinicius Guedes**
