-- Cria a tabela principal de Comissões
CREATE TABLE comissoes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tema VARCHAR(100) NOT NULL,
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE
);

-- Cria a tabela de ligação (Muitos para Muitos)
CREATE TABLE comissoes_funcionarios (
    comissao_id BIGINT NOT NULL,
    funcionario_id BIGINT NOT NULL,
    PRIMARY KEY (comissao_id, funcionario_id),
    CONSTRAINT fk_comissao FOREIGN KEY (comissao_id) REFERENCES comissoes(id),
    CONSTRAINT fk_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);