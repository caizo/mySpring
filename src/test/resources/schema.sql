/*CREATE DATABASE IF NOT EXISTS myspring;

USE myspring;
*/
CREATE TABLE usuarios
(
    id                 BIGINT       NOT NULL AUTO_INCREMENT,
    username           VARCHAR(255) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    role               VARCHAR(255) NOT NULL,
    activo             BOOLEAN      NOT NULL,
    fecha_creacion     TIMESTAMP    NOT NULL,
    fecha_modificacion TIMESTAMP    NOT NULL,

    CONSTRAINT pk_usuarios
        PRIMARY KEY (id),

    CONSTRAINT uk_usuarios_email UNIQUE (email),
    CONSTRAINT uk_usuarios_username UNIQUE (username)
);

CREATE TABLE publicaciones
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo             VARCHAR(255) NOT NULL,
    descripcion        TEXT         NOT NULL,
    fecha_inicio       DATETIME(6)  NOT NULL,
    fecha_fin          DATETIME(6)  NOT NULL,
    empresa_id         BIGINT       NOT NULL,
    tipo               VARCHAR(50)  NOT NULL,
    estado             VARCHAR(50)  NOT NULL,
    fecha_creacion     DATETIME(6)  NOT NULL,
    fecha_modificacion DATETIME(6)  NOT NULL
);

CREATE TABLE publicacion_imagenes
(
    publicacion_id BIGINT       NOT NULL,
    imagen_url     VARCHAR(500) NOT NULL,

    CONSTRAINT fk_publicacion_imagen FOREIGN KEY (publicacion_id) REFERENCES publicaciones (id) ON DELETE CASCADE,
    CONSTRAINT uk_publicacion_imagen UNIQUE (publicacion_id, imagen_url)
);
