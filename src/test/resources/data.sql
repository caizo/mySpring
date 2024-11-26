
INSERT INTO restaurante (nombre, direccion, telefono, email, imagen)
VALUES ('Restaurante El Buen Sabor', 'Calle Falsa 123', '1234567890', 'contacto@elbuen.com', NULL),
       ('La Casa de las Empanadas', 'Avenida Siempre Viva 742', '0987654321', 'info@empanadas.com', NULL),
       ('Pizzeria Don Pepe', 'Boulevard de los Sueños 456', '1122334455', 'reservas@donpepe.com', NULL),
       ('Sushi Express', 'Calle del Mar 789', '6677889900', 'contacto@sushiexpress.com', NULL),
       ('Tacos El Gordo', 'Avenida Central 101', '5566778899', 'info@tacoselgordo.com', NULL);

INSERT INTO usuario (username, email, password, role)
VALUES ('juan', 'juan@gmail.com', 'password123', 'CLIENTE');

INSERT INTO usuario (username, email, password, role)
VALUES ('paco', 'paco@outlook.com', 'password456', 'ADMIN');

INSERT INTO usuario (username, email, password, role)
VALUES ('anton', 'anton@yahooo.com', 'password789', 'CLIENTE');

INSERT INTO TipoPlato (tipo) VALUES ('PRIMERO');
INSERT INTO TipoPlato (tipo) VALUES ('SEGUNDO');
INSERT INTO TipoPlato (tipo) VALUES ('POSTRE');

INSERT INTO plato (nombre, descripcion, precio, imagen, restaurante_id, tipo)
VALUES
('Ensalada César', 'Ensalada fresca con aderezo César', 5.99, NULL, 1, 'PRIMERO'),
('Sopa de Tomate', 'Sopa cremosa de tomate', 4.50, NULL, 1, 'PRIMERO'),
('Pollo a la Parrilla', 'Pollo marinado y asado a la parrilla', 10.99, NULL, 1, 'SEGUNDO'),
('Pasta Alfredo', 'Pasta con salsa Alfredo y queso parmesano', 8.99, NULL, 1, 'SEGUNDO'),
('Tarta de Queso', 'Tarta de queso con base de galleta', 3.99, NULL, 1, 'POSTRE'),
('Helado de Vainilla', 'Helado cremoso de vainilla', 2.99, NULL, 1, 'POSTRE'),

('Empanada de Carne', 'Empanada rellena de carne', 3.50, NULL, 2, 'PRIMERO'),
('Empanada de Pollo', 'Empanada rellena de pollo', 3.50, NULL, 2, 'PRIMERO'),
('Milanesa de Res', 'Milanesa de res empanizada', 9.99, NULL, 2, 'SEGUNDO'),
('Asado Argentino', 'Carne asada al estilo argentino', 12.99, NULL, 2, 'SEGUNDO'),
('Flan Casero', 'Flan de leche con caramelo', 4.50, NULL, 2, 'POSTRE'),
('Alfajor', 'Dulce de leche entre dos galletas', 2.50, NULL, 2, 'POSTRE'),

('Pizza Margherita', 'Pizza clásica con tomate, mozzarella y albahaca', 9.99, NULL, 3, 'PRIMERO'),
('Pizza Pepperoni', 'Pizza con pepperoni y queso', 10.99, NULL, 3, 'PRIMERO'),
('Lasaña', 'Lasaña de carne con salsa de tomate', 11.99, NULL, 3, 'SEGUNDO'),
('Spaghetti Bolognese', 'Spaghetti con salsa bolognesa', 8.99, NULL, 3, 'SEGUNDO'),
('Tiramisú', 'Postre italiano con café y mascarpone', 5.50, NULL, 3, 'POSTRE'),
('Cannoli', 'Dulce italiano relleno de ricotta', 4.00, NULL, 3, 'POSTRE'),

('Sushi Roll', 'Roll de sushi con salmón y aguacate', 12.50, NULL, 4, 'PRIMERO'),
('Sashimi', 'Lonjas de pescado crudo', 14.00, NULL, 4, 'PRIMERO'),
('Ramen', 'Sopa de fideos con carne y vegetales', 9.50, NULL, 4, 'SEGUNDO'),
('Tempura', 'Verduras y mariscos fritos', 11.00, NULL, 4, 'SEGUNDO'),
('Mochi', 'Dulce de arroz relleno de helado', 3.50, NULL, 4, 'POSTRE'),
('Dorayaki', 'Pastel japonés relleno de anko', 4.00, NULL, 4, 'POSTRE'),

('Tacos al Pastor', 'Tacos de cerdo al pastor con piña', 7.99, NULL, 5, 'PRIMERO'),
('Guacamole', 'Puré de aguacate con tomate y cebolla', 5.50, NULL, 5, 'PRIMERO'),
('Burrito de Carne', 'Burrito relleno de carne y frijoles', 8.99, NULL, 5, 'SEGUNDO'),
('Quesadilla', 'Tortilla rellena de queso y carne', 7.50, NULL, 5, 'SEGUNDO'),
('Churros', 'Masa frita espolvoreada con azúcar', 3.00, NULL, 5, 'POSTRE'),
('Flan Mexicano', 'Flan de leche con caramelo', 4.00, NULL, 5, 'POSTRE');


