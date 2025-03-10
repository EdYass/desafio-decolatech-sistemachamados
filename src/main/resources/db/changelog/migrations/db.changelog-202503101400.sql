--liquibase formatted sql
--changeset junior:202403101201
--comment: Criação da tabela de chamados

CREATE TABLE CHAMADOS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    data_abertura DATETIME NOT NULL,
    data_conclusao DATETIME NULL,
    status ENUM('ABERTO', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO') NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id) ON DELETE CASCADE
) ENGINE=InnoDB;

--rollback DROP TABLE CHAMADOS;