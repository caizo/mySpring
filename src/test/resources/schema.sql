CREATE DATABASE IF NOT EXISTS myspring;

USE myspring;

CREATE TABLE usuarios
(
    id                 BIGINT    NOT NULL AUTO_INCREMENT,
    username           VARCHAR(255),
    email              VARCHAR(255),
    password           VARCHAR(255),
    role               VARCHAR(255),
    activo             BOOLEAN   NOT NULL,
    fecha_creacion     TIMESTAMP NOT NULL,
    fecha_modificacion TIMESTAMP NOT NULL,

    CONSTRAINT pk_usuarios
        PRIMARY KEY (id),

    CONSTRAINT uk_usuarios_email
        UNIQUE (email)
);







