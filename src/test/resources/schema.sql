-- src/main/resources/sql/schema.sql
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL unique,
    telefono VARCHAR(15)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(25)  NOT NULL
);

DROP TABLE IF EXISTS restaurante;

CREATE TABLE restaurante
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre               VARCHAR(100) NOT NULL,
    direccion            VARCHAR(200) NOT NULL,
    telefono             VARCHAR(15)  NOT NULL,
    email                VARCHAR(255) NOT NULL,
    imagen               BLOB,
    tipo_restaurante     VARCHAR(100) NOT NULL,
    descripcion          VARCHAR(255) NULL
);









