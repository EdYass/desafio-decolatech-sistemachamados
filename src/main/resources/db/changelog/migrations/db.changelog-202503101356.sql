--liquibase formatted sql
--changeset jun:202403101356
--comment: Criação da tabela de usuários

CREATE TABLE USUARIOS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB;

--rollback DROP TABLE USUARIOS;