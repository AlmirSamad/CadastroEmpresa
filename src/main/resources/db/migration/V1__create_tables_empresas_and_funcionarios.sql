CREATE TABLE empresas (
    id BIGSERIAL PRIMARY KEY,
    nome_fantasia VARCHAR(255) NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    diretor VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(20),
    polo VARCHAR(100),
    subsetor VARCHAR(100),

    -- Campos oriundos da classe @Embedded Endereco
    logradouro VARCHAR(255),
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf CHAR(2),
    cep VARCHAR(9)
);

CREATE TABLE funcionarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(20),
    cpf VARCHAR(20) NOT NULL UNIQUE,
    empresa_id BIGINT NOT NULL,

    CONSTRAINT fk_funcionarios_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresas(id)
);