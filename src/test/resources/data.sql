INSERT INTO tipo_restaurante (nombre) VALUES ('Espanola');
INSERT INTO tipo_restaurante (nombre) VALUES ('Mexicana');
INSERT INTO tipo_restaurante (nombre) VALUES ('Japonesa');


INSERT INTO restaurante (nombre, direccion, telefono, email, imagen, tipo_restaurante_id)
VALUES ('Restaurante El Buen Sabor', 'Calle Falsa 123', '1234567890', 'contacto@elbuen.com', NULL, 1),
       ('La Casa de las Empanadas', 'Avenida Siempre Viva 742', '0987654321', 'info@empanadas.com', NULL, 2),
       ('Pizzeria Don Pepe', 'Boulevard de los Sueños 456', '1122334455', 'reservas@donpepe.com', NULL, 3),
       ('Sushi Express', 'Calle del Mar 789', '6677889900', 'contacto@sushiexpress.com', NULL, 3),
       ('Tacos El Gordo', 'Avenida Central 101', '5566778899', 'info@tacoselgordo.com', NULL, 2);

INSERT INTO usuario (username, email, telefono, password, role)
VALUES ('juan', 'juan@gmail.com', '673418121', 'password123', 'CLIENTE');

INSERT INTO usuario (username, email, telefono, password, role)
VALUES ('paco', 'paco@outlook.com', '673418120', 'password456', 'CLIENTE');

INSERT INTO usuario (username, email, telefono, password, role)
VALUES ('anton', 'anton@yahooo.com', '673418123', 'password789', 'CLIENTE');

