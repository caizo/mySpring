-- src/main/resources/sql/schema.sql
CREATE TABLE usuario
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(25)  NOT NULL
);

CREATE TABLE restaurante
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono  VARCHAR(15)  NOT NULL,
    email     VARCHAR(255) NOT NULL,
    imagen    BLOB
);