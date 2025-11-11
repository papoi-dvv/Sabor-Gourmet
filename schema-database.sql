-- =============================================
-- BASE DE DATOS: SISTEMA GESTIÓN RESTAURANTE
-- Sabor Gourmet
-- =============================================

DROP DATABASE IF EXISTS restaurante_db;
CREATE DATABASE restaurante_db;
USE restaurante_db;

-- =============================================
-- TABLA: USUARIO (Spring Security)
-- =============================================
CREATE TABLE usuario (
                         id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                         nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
                         contrasena VARCHAR(255) NOT NULL,
                         rol ENUM('ADMIN', 'MOZO', 'COCINERO', 'CAJERO') NOT NULL,
                         estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO',
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLA: CLIENTE
-- =============================================
CREATE TABLE cliente (
                         id_cliente INT AUTO_INCREMENT PRIMARY KEY,
                         dni VARCHAR(8) NOT NULL UNIQUE,
                         nombres VARCHAR(100) NOT NULL,
                         apellidos VARCHAR(100) NOT NULL,
                         telefono VARCHAR(15),
                         correo VARCHAR(100),
                         estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO',
                         fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLA: MESA
-- =============================================
CREATE TABLE mesa (
                      id_mesa INT AUTO_INCREMENT PRIMARY KEY,
                      numero INT NOT NULL UNIQUE,
                      capacidad INT NOT NULL,
                      estado ENUM('DISPONIBLE', 'OCUPADA', 'RESERVADA', 'MANTENIMIENTO') DEFAULT 'DISPONIBLE',
                      fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLA: PLATO
-- =============================================
CREATE TABLE plato (
                       id_plato INT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       tipo ENUM('ENTRADA', 'FONDO', 'POSTRE', 'BEBIDA') NOT NULL,
                       precio DECIMAL(10, 2) NOT NULL,
                       descripcion TEXT,
                       estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO',
                       fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLA: PEDIDO
-- =============================================
CREATE TABLE pedido (
                        id_pedido INT AUTO_INCREMENT PRIMARY KEY,
                        id_cliente INT,
                        id_mesa INT NOT NULL,
                        fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        estado ENUM('PENDIENTE', 'EN_PREPARACION', 'SERVIDO', 'CERRADO') DEFAULT 'PENDIENTE',
                        total DECIMAL(10, 2) DEFAULT 0.00,
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL,
                        FOREIGN KEY (id_mesa) REFERENCES mesa(id_mesa) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLA: DETALLE_PEDIDO
-- =============================================
CREATE TABLE detalle_pedido (
                                id_detalle_pedido INT AUTO_INCREMENT PRIMARY KEY,
                                id_pedido INT NOT NULL,
                                id_plato INT NOT NULL,
                                cantidad INT NOT NULL DEFAULT 1,
                                precio_unitario DECIMAL(10, 2) NOT NULL,
                                subtotal DECIMAL(10, 2) NOT NULL,
                                FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE CASCADE,
                                FOREIGN KEY (id_plato) REFERENCES plato(id_plato) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLA: BITACORA (AOP Auditoría)
-- =============================================
CREATE TABLE bitacora (
                          id_bitacora INT AUTO_INCREMENT PRIMARY KEY,
                          id_usuario INT,
                          accion VARCHAR(50) NOT NULL,
                          tabla_afectada VARCHAR(50) NOT NULL,
                          registro_id INT,
                          descripcion TEXT,
                          fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;