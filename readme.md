🏢 API de Cadastro de Empresas e Funcionários (EmpCad API)

Esta é uma API RESTful desenvolvida com Spring Boot 4 para o gerenciamento de empresas, seus respectivos funcionários, e a organização interna através de comissões e reuniões. O sistema é o back-end de uma aplicação Web e conta com autenticação via JWT (JSON Web Token), controle de permissões, documentação automatizada e versionamento de banco de dados com Flyway.
 Tecnologias Utilizadas

    Linguagem: Java 25

    Framework: Spring Boot 4.0.5

    Segurança: Spring Security 7 & JWT (Auth0)

    Banco de Dados: PostgreSQL

    Migrações: Flyway

    Persistência: Spring Data JPA / Hibernate

    Documentação: SpringDoc OpenAPI 3 (Swagger UI)

    Ferramenta de Build: Maven

 Funcionalidades Principais

    Gestão de Empresas: Cadastro, listagem paginada, atualização, exclusão lógica e sistema de Score ESG (indicador de práticas ambientais, sociais e de governança corporativa).

    Gestão de Funcionários: Cadastro vinculado a empresas, controle de cargos e status (ativo/inativo).

    Gestão de Comissões: Criação de comissões internas e gerenciamento de membros (relacionamento Many-to-Many com funcionários).

    Gestão de Reuniões: * Agendamento inteligente com validação rigorosa de regras de negócio (antecedência mínima de 24h, prevenção de conflitos de horário).

        Sistema de RSVP para confirmação de presença dos funcionários nas reuniões.

    Autenticação e Autorização:

        Criação de usuários com senhas criptografadas (BCrypt).

        Geração de tokens JWT para acesso seguro aos endpoints.

        Filtros de segurança para validação de tokens em cada requisição.

 Arquitetura e Boas Práticas

    Padrões de Projeto: Uso extensivo do Design Pattern Strategy para a injeção dinâmica de regras de negócio (validadores de agendamento e presença), garantindo o princípio Open/Closed do SOLID.

    Segurança de Dados: Uso rigoroso de DTOs (Data Transfer Objects) (via Java Records) para evitar a exposição excessiva de dados sensíveis e prevenir problemas de serialização (como loops infinitos no Jackson).

    Tratamento de Erros: Captura global de exceções via @RestControllerAdvice, padronizando as respostas de erro da API.

    Padrões REST: Respostas HTTP semânticas (201 Created com cabeçalho Location, 204 No Content para exclusões/desvinculações, 200 OK para consultas).

 Estrutura do Projeto

O projeto segue uma organização orientada a domínios para facilitar a escalabilidade e manutenção:

    empresa: Entidades, Repositories e Controllers relacionados às empresas e seus scores.

    funcionario: Regras de negócio e persistência de colaboradores.

    comissao: Gerenciamento de grupos de trabalho e vínculo de membros.

    reuniao: Lógica de agendamentos, pautas, validações de horário e lista de presença.

    user: Gerenciamento de credenciais e serviços de autenticação.

    infra: Configurações globais (Segurança, Swagger, tratamento de exceções).

 Como Executar o Projeto
Pré-requisitos

    Java 25 instalado na máquina.

    Maven instalado.

    PostgreSQL rodando localmente (porta 5432) ou via Docker.

Passos para Execução

    Clone o repositório.

    Crie um banco de dados no PostgreSQL chamado cieam (ou ajuste o nome no arquivo application.properties).

    Configure as credenciais do banco de dados no arquivo application.properties.

    Execute o projeto pela sua IDE de preferência ou via linha de comando (mvn spring-boot:run).

    O Flyway criará todas as tabelas e relacionamentos automaticamente na primeira execução.

📖 Documentação da API (Swagger)

A API possui documentação interativa gerada automaticamente. Com o projeto em execução, acesse o link abaixo no seu navegador para explorar todos os endpoints, schemas de DTOs e realizar testes (lembre-se de gerar o token na rota de login e inseri-lo no botão Authorize):

👉 http://localhost:8080/swagger-ui.html