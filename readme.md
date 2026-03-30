# 🏢 API de Cadastro de Empresas e Funcionários (EmpCad API)

Esta é uma API RESTful desenvolvida com **Spring Boot 3** para o gerenciamento de empresas e seus respectivos funcionários. O sistema conta com autenticação via **JWT (JSON Web Token)**, controle de permissões, tratamento de erros customizado e versionamento de banco de dados com **Flyway**.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 25
* **Framework:** Spring Boot 4.0.5
* **Segurança:** Spring Security & JWT
* **Banco de Dados:** PostgreSQL
* **Migrações:** Flyway
* **Persistência:** Spring Data JPA
* **Ferramenta de Build:** Maven

## 🛠️ Funcionalidades Principal

* **Gestão de Empresas:** Cadastro, listagem (paginada), atualização e exclusão lógica.
* **Gestão de Funcionários:** Cadastro vinculado a empresas, controle de cargos e status ativo/inativo.
* **Autenticação e Autorização:**
    * Criação de usuários com senhas criptografadas (BCrypt).
    * Geração de tokens JWT para acesso seguro aos endpoints.
    * Filtros de segurança para validação de tokens em cada requisição.
* **Arquitetura:** Uso de **DTOs (Data Transfer Objects)** para validação e exposição segura de dados.
* **Banco de Dados:** Evolução estrutural controlada via scripts SQL (Flyway).

## 📂 Estrutura do Projeto

O projeto segue uma organização por domínio para facilitar a escalabilidade:
- `empresa`: Entidades, Repositories e Controllers relacionados às empresas.
- `funcionario`: Regras de negócio e persistência de colaboradores.
- `user`: Gerenciamento de usuários e serviços de autenticação.
- `infra`: Configurações de segurança, filtros e tratamento global de exceções.

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Java 17 ou superior.
* Maven instalado.
* PostgreSQL rodando localmente ou via Docker.

