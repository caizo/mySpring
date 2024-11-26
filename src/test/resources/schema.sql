-- src/main/resources/sql/schema.sql
CREATE TABLE USUARIO
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

CREATE TABLE TipoPlato
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE plato
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio      DECIMAL(10, 2) NOT NULL,
    imagen      BLOB,
    restaurante_id BIGINT NOT NULL,
    tipo        VARCHAR(50) NOT NULL,
    FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
);

CREATE TABLE menu
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    descripcion    VARCHAR(100) NOT NULL,
    fecha          DATE NOT NULL,
    precio         DECIMAL(10, 2) NOT NULL,
    restaurante_id BIGINT NOT NULL,
    FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
);

CREATE TABLE menu_plato
(
    menu_id  BIGINT NOT NULL,
    plato_id BIGINT NOT NULL,
    PRIMARY KEY (menu_id, plato_id),
    FOREIGN KEY (menu_id) REFERENCES menu (id),
    FOREIGN KEY (plato_id) REFERENCES plato (id)
);