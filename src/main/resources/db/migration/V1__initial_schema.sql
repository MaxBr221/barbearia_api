CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE barbeiro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE servico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE agendamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    data_agendamento DATETIME NOT NULL,

    user_id BIGINT NOT NULL,
    barbeiro_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,

    status_agendamento VARCHAR(20) NOT NULL,

    CONSTRAINT fk_agendamento_usuario
        FOREIGN KEY (user_id)
        REFERENCES usuario(id),

    CONSTRAINT fk_agendamento_barbeiro
        FOREIGN KEY (barbeiro_id)
        REFERENCES barbeiro(id),

    CONSTRAINT fk_agendamento_servico
        FOREIGN KEY (servico_id)
        REFERENCES servico(id)
);