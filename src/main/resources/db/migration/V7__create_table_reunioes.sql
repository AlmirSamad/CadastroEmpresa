-- 1. Cria a tabela de Reuniões
CREATE TABLE reunioes (
    id BIGSERIAL PRIMARY KEY,
    comissao_id BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    pauta VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    -- Chave estrangeira ligando a reunião à comissão dona dela
    CONSTRAINT fk_reuniao_comissao FOREIGN KEY (comissao_id) REFERENCES comissoes(id)
);

-- 2. Cria a tabela de ligação (Participantes Confirmados)
CREATE TABLE reunioes_participantes (
    reuniao_id BIGINT NOT NULL,
    funcionario_id BIGINT NOT NULL,

    PRIMARY KEY (reuniao_id, funcionario_id),
    CONSTRAINT fk_participante_reuniao FOREIGN KEY (reuniao_id) REFERENCES reunioes(id),
    CONSTRAINT fk_participante_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);