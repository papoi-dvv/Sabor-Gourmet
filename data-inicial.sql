-- =============================================
-- DATOS INICIALES - SABOR GOURMET
-- =============================================

-- Limpiar datos existentes
DELETE FROM detalle_pedido;
DELETE FROM pedido;
DELETE FROM bitacora;
DELETE FROM cliente;
DELETE FROM plato;
DELETE FROM mesa;
DELETE FROM usuario;

-- =============================================
-- USUARIOS (Contraseñas: todas son "password")
-- Hash BCrypt generado: $2a$10$slYQmyNdGzin7olVXiYm2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMm6
-- =============================================
INSERT INTO usuario (nombre_usuario, contrasena, rol, estado, fecha_creacion) VALUES
('admin', '$2a$10$slYQmyNdGzin7olVXiYm2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMm6', 'ADMIN', 'ACTIVO', NOW()),
('mozo', '$2a$10$slYQmyNdGzin7olVXiYm2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMm6', 'MOZO', 'ACTIVO', NOW()),
('cocinero', '$2a$10$slYQmyNdGzin7olVXiYm2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMm6', 'COCINERO', 'ACTIVO', NOW()),
('cajero', '$2a$10$slYQmyNdGzin7olVXiYm2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMm6', 'CAJERO', 'ACTIVO', NOW());

-- =============================================
-- CLIENTES
-- =============================================
INSERT INTO cliente (dni, nombres, apellidos, telefono, correo, estado, fecha_registro) VALUES
('12345678', 'Juan', 'García', '987654321', 'juan@example.com', 'ACTIVO', NOW()),
('87654321', 'María', 'López', '987654322', 'maria@example.com', 'ACTIVO', NOW()),
('11111111', 'Carlos', 'Martínez', '987654323', 'carlos@example.com', 'ACTIVO', NOW()),
('22222222', 'Ana', 'Rodríguez', '987654324', 'ana@example.com', 'ACTIVO', NOW()),
('33333333', 'Pedro', 'Fernández', '987654325', 'pedro@example.com', 'ACTIVO', NOW());

-- =============================================
-- MESAS
-- =============================================
INSERT INTO mesa (numero, capacidad, estado, fecha_actualizacion) VALUES
(1, 2, 'DISPONIBLE', NOW()),
(2, 2, 'DISPONIBLE', NOW()),
(3, 4, 'DISPONIBLE', NOW()),
(4, 4, 'DISPONIBLE', NOW()),
(5, 6, 'DISPONIBLE', NOW()),
(6, 6, 'DISPONIBLE', NOW()),
(7, 8, 'DISPONIBLE', NOW()),
(8, 8, 'DISPONIBLE', NOW());

-- =============================================
-- PLATOS
-- =============================================
INSERT INTO plato (nombre, tipo, precio, descripcion, estado, fecha_creacion) VALUES
-- ENTRADAS
('Tabla de Quesos y Embutidos', 'ENTRADA', 35.00, 'Selección de quesos y embutidos ibéricos', 'ACTIVO', NOW()),
('Ceviche Clásico', 'ENTRADA', 28.00, 'Pez blanco, limón, cebolla roja y ají', 'ACTIVO', NOW()),
('Causa Limeña', 'ENTRADA', 22.00, 'Puré de papa amarilla con pollo desmenuzado', 'ACTIVO', NOW()),
('Tiradito de Salmón', 'ENTRADA', 32.00, 'Salmón fresco con citrus y ají', 'ACTIVO', NOW()),

-- FONDOS
('Lomo a la Parrilla', 'FONDO', 68.00, 'Corte premium con papas gratinadas', 'ACTIVO', NOW()),
('Ají de Gallina', 'FONDO', 38.00, 'Pollo desmenuzado en crema de ají con papas', 'ACTIVO', NOW()),
('Ceviche Mixto', 'FONDO', 45.00, 'Pescado, camarones, pulpo y mariscos', 'ACTIVO', NOW()),
('Pato a la Naranja', 'FONDO', 55.00, 'Pato confitado con salsa de naranja', 'ACTIVO', NOW()),
('Pollo a la Brasa', 'FONDO', 42.00, 'Pollo entero marinado y asado a leña', 'ACTIVO', NOW()),
('Tallarín de Mariscos', 'FONDO', 48.00, 'Pasta fresca con camarones y almejas', 'ACTIVO', NOW()),

-- POSTRES
('Tiramisú', 'POSTRE', 16.00, 'Postre italiano con café y mascarpone', 'ACTIVO', NOW()),
('Flan de Caramelo', 'POSTRE', 14.00, 'Flan casero con caramelo', 'ACTIVO', NOW()),
('Cheesecake de Frutos Rojos', 'POSTRE', 18.00, 'Queso crema con frutos rojos', 'ACTIVO', NOW()),
('Helado Artesanal', 'POSTRE', 12.00, 'Selección de sabores del día', 'ACTIVO', NOW()),

-- BEBIDAS
('Vino Tinto Reserva', 'BEBIDA', 65.00, 'Vino peruano de excelente cosecha', 'ACTIVO', NOW()),
('Vino Blanco Crianza', 'BEBIDA', 55.00, 'Vino blanco fresco y aromático', 'ACTIVO', NOW()),
('Cerveza Artesanal', 'BEBIDA', 18.00, 'Cerveza local premium', 'ACTIVO', NOW()),
('Agua Mineral', 'BEBIDA', 5.00, 'Agua embotellada', 'ACTIVO', NOW()),
('Refresco Natural', 'BEBIDA', 12.00, 'Jugo recién exprimido', 'ACTIVO', NOW()),
('Chicha Morada', 'BEBIDA', 10.00, 'Bebida tradicional de maíz morado', 'ACTIVO', NOW());

-- =============================================
-- PEDIDOS DE EJEMPLO
-- =============================================
INSERT INTO pedido (id_cliente, id_mesa, fecha_hora, estado, total) VALUES
(1, 1, DATE_SUB(NOW(), INTERVAL 2 HOUR), 'CERRADO', 126.00),
(2, 3, DATE_SUB(NOW(), INTERVAL 1 HOUR), 'SERVIDO', 85.50),
(3, 5, NOW(), 'EN_PREPARACION', 156.00);

-- =============================================
-- DETALLES DE PEDIDOS
-- =============================================
INSERT INTO detalle_pedido (id_pedido, id_plato, cantidad, precio_unitario, subtotal) VALUES
-- Pedido 1
(1, 2, 1, 28.00, 28.00),  -- Ceviche
(1, 6, 2, 38.00, 76.00),  -- Ají de Gallina
(1, 13, 2, 12.00, 24.00), -- Helado

-- Pedido 2
(2, 1, 1, 35.00, 35.00),  -- Tabla de Quesos
(2, 8, 1, 55.00, 55.00),  -- Pato a la Naranja
(2, 11, -5.50, -5.50),   -- Descuento

-- Pedido 3
(3, 4, 1, 32.00, 32.00),  -- Tiradito
(3, 9, 2, 42.00, 84.00),  -- Pollo a la Brasa
(3, 19, 2, 18.00, 36.00), -- Cerveza
(3, 12, 1, 16.00, 16.00); -- Tiramisú

-- =============================================
-- BITÁCORA (Ejemplos de auditoría)
-- =============================================
INSERT INTO bitacora (id_usuario, accion, tabla_afectada, registro_id, descripcion, fecha_hora) VALUES
(1, 'CREAR', 'CLIENTE', 1, 'Cliente Juan García creado', DATE_SUB(NOW(), INTERVAL 5 DAY)),
(1, 'CREAR', 'MESA', 1, 'Mesa 1 creada', DATE_SUB(NOW(), INTERVAL 5 DAY)),
(2, 'CREAR', 'PEDIDO', 1, 'Pedido #1 creado por mozo', DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(3, 'ACTUALIZAR', 'PEDIDO', 1, 'Pedido #1 marcado como EN_PREPARACION', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(3, 'ACTUALIZAR', 'PEDIDO', 1, 'Pedido #1 marcado como SERVIDO', DATE_SUB(NOW(), INTERVAL 45 MINUTE));
