-- =============================================
-- DATOS DE PRUEBA
-- =============================================

-- USUARIOS (Contraseña: "password" cifrada con BCrypt)
-- BCrypt de "password": $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy
INSERT INTO usuario (nombre_usuario, contrasena, rol, estado) VALUES
                                                                  ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 'ACTIVO'),
                                                                  ('mozo1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'MOZO', 'ACTIVO'),
                                                                  ('cocinero1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'COCINERO', 'ACTIVO'),
                                                                  ('cajero1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'CAJERO', 'ACTIVO');

-- CLIENTES
INSERT INTO cliente (dni, nombres, apellidos, telefono, correo) VALUES
                                                                    ('12345678', 'Juan', 'Pérez García', '987654321', 'juan.perez@email.com'),
                                                                    ('87654321', 'María', 'López Rodríguez', '987654322', 'maria.lopez@email.com'),
                                                                    ('11223344', 'Carlos', 'González Martínez', '987654323', 'carlos.gonzalez@email.com'),
                                                                    ('44332211', 'Ana', 'Fernández Silva', '987654324', 'ana.fernandez@email.com'),
                                                                    ('55667788', 'Luis', 'Ramírez Torres', '987654325', 'luis.ramirez@email.com');

-- MESAS
INSERT INTO mesa (numero, capacidad, estado) VALUES
                                                 (1, 2, 'DISPONIBLE'),
                                                 (2, 4, 'DISPONIBLE'),
                                                 (3, 4, 'OCUPADA'),
                                                 (4, 6, 'DISPONIBLE'),
                                                 (5, 2, 'RESERVADA'),
                                                 (6, 8, 'DISPONIBLE'),
                                                 (7, 4, 'MANTENIMIENTO'),
                                                 (8, 2, 'DISPONIBLE');

-- PLATOS
INSERT INTO plato (nombre, tipo, precio, descripcion) VALUES
-- ENTRADAS
('Ensalada César', 'ENTRADA', 18.50, 'Lechuga romana, crutones, parmesano y aderezo César'),
('Tequeños', 'ENTRADA', 15.00, 'Deditos de queso envueltos en masa wonton'),
('Causa Limeña', 'ENTRADA', 22.00, 'Papa amarilla, pollo, palta y mayonesa'),

-- FONDOS
('Lomo Saltado', 'FONDO', 35.00, 'Carne de res salteada con cebolla, tomate y papas fritas'),
('Arroz con Mariscos', 'FONDO', 42.00, 'Arroz con langostinos, calamares y mejillones'),
('Pollo a la Plancha', 'FONDO', 28.00, 'Pechuga de pollo con ensalada y papas'),
('Tallarines a la Huancaína', 'FONDO', 25.00, 'Pasta en salsa de ají amarillo con pollo'),

-- POSTRES
('Suspiro Limeño', 'POSTRE', 12.00, 'Manjar blanco con merengue de vino oporto'),
('Torta de Chocolate', 'POSTRE', 14.00, 'Bizcocho de chocolate con mousse'),
('Crema Volteada', 'POSTRE', 10.00, 'Flan de leche con caramelo'),

-- BEBIDAS
('Chicha Morada', 'BEBIDA', 8.00, 'Bebida de maíz morado con frutas'),
('Limonada Frozen', 'BEBIDA', 10.00, 'Limonada con hielo frappe'),
('Inca Kola 1L', 'BEBIDA', 7.00, 'Gaseosa peruana'),
('Agua Mineral', 'BEBIDA', 4.00, 'Agua sin gas 600ml');

-- PEDIDOS DE EJEMPLO
INSERT INTO pedido (id_cliente, id_mesa, estado, total) VALUES
                                                            (1, 3, 'EN_PREPARACION', 85.50),
                                                            (2, 5, 'PENDIENTE', 0.00);

-- DETALLES DEL PEDIDO 1 (Mesa 3)
INSERT INTO detalle_pedido (id_pedido, id_plato, cantidad, precio_unitario, subtotal) VALUES
                                                                                          (1, 1, 2, 18.50, 37.00),  -- 2 Ensaladas César
                                                                                          (1, 4, 1, 35.00, 35.00),  -- 1 Lomo Saltado
                                                                                          (1, 11, 1, 8.00, 8.00),   -- 1 Chicha Morada
                                                                                          (1, 14, 1, 4.00, 4.00);   -- 1 Agua Mineral