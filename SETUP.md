# 🍽️ Sabor Gourmet - Sistema de Gestión de Restaurante

## 📋 Descripción
Aplicación web para gestión integral de restaurantes construida con **Spring Boot 3.5.7**, **Thymeleaf**, **MySQL/H2** y **Bootstrap 5**.

## ⚙️ Requisitos Previos
- Java 17+
- Maven 3.8+
- MySQL 8.0+ (o H2 para desarrollo)
- Git

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/papoi-dvv/Sabor-Gourmet.git
cd Sabor-Gourmet
```

### 2. Compilar el Proyecto
```bash
mvn clean install
```

### 3. Configuración de Base de Datos

#### Opción A: MySQL (Producción)
```bash
# Crear base de datos
mysql -u root -p < schema-database.sql

# Insertar datos iniciales
mysql -u root -p restaurante_db < data-inicial.sql
```

Actualizar `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurante_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

#### Opción B: H2 (Desarrollo)
Ya está configurado. Solo descomentar en `application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:restaurante_db
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
```

### 4. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: **http://localhost:8080**

## 👤 Usuarios de Prueba

| Usuario | Contraseña | Rol | Permisos |
|---------|-----------|-----|---------|
| admin | Password123 | ADMIN | Acceso total |
| mozo | Password123 | MOZO | Mesas, Clientes, Pedidos |
| cocinero | Password123 | COCINERO | Panel de Cocina |
| cajero | Password123 | CAJERO | Facturación, Reportes |

## 📂 Estructura del Proyecto

```
Sabor-Gourmet/
├── src/main/java/com/saborgourmet/
│   ├── aspect/              # AOP - Auditoría
│   ├── config/              # Spring Security, Web Config
│   ├── controller/          # Controladores MVC
│   ├── model/               # Entidades JPA + Enums
│   ├── repository/          # Interfaces JPA
│   ├── service/             # Lógica de negocio
│   └── SaborGourmetApplication.java
├── src/main/resources/
│   ├── templates/           # Vistas Thymeleaf
│   ├── static/              # CSS, JS, Imágenes
│   └── application.properties
├── pom.xml                  # Dependencias Maven
└── schema-database.sql      # Esquema BD
```

## 🔑 Funcionalidades Principales

### 👤 Gestión de Clientes
- ✅ CRUD completo
- ✅ Búsqueda por DNI
- ✅ Historial de pedidos

### 🪑 Gestión de Mesas
- ✅ Dashboard visual
- ✅ Control de estado (Disponible, Ocupada, Reservada)
- ✅ Estadísticas en tiempo real

### 🍽️ Menú de Platos
- ✅ Categorización por tipo (Entrada, Fondo, Postre, Bebida)
- ✅ Precios y descripciones
- ✅ Menú público

### 📋 Gestión de Pedidos
- ✅ Creación de pedidos
- ✅ Asignación a mesa y cliente
- ✅ Detalles con cantidades y precios
- ✅ Flujo de estados: Pendiente → En Preparación → Servido → Cerrado

### 👨‍🍳 Panel de Cocina
- ✅ Vista de pedidos pendientes
- ✅ Cambio de estado
- ✅ Organización visual

### 📊 Panel Administrativo
- ✅ Dashboard con estadísticas
- ✅ Bitácora de auditoría
- ✅ Acciones rápidas

## 🔒 Seguridad

- **Spring Security** con autenticación por roles
- **BCrypt** para encriptación de contraseñas
- **CSRF Protection** habilitado
- **SQL Injection Prevention** mediante JPA
- **XSS Protection** en Thymeleaf

## 🛠️ Tecnologías Usadas

### Backend
- **Spring Boot 3.5.7**
- **Spring Security 6**
- **Spring Data JPA**
- **Hibernate 6.6.33**
- **Maven**

### Frontend
- **Thymeleaf 3**
- **Bootstrap 5.3**
- **Bootstrap Icons 1.11**

### Base de Datos
- **MySQL 8.0**
- **H2 (para desarrollo)**

### Dependencias Adicionales
- **Lombok** - Reducción de código boilerplate
- **DevTools** - Hot reload durante desarrollo

## 📝 Ejemplos de Uso

### Crear un Nuevo Pedido
1. Ir a Pedidos → Nuevo Pedido
2. Seleccionar Mesa y Cliente (opcional)
3. Agregar platos
4. Guardar

### Gestionar Mesas
1. Ir a Mesas → Dashboard
2. Visualizar estado de cada mesa
3. Ocupar/Liberar según sea necesario

### Panel de Cocina
1. Ir a Pedidos → Cocina (Solo Cocinero)
2. Ver pedidos pendientes
3. Marcar como "En Preparación"
4. Marcar como "Servido"

## 🐛 Resolución de Problemas

### Error: "Failed to initialize JPA EntityManagerFactory"
- Verificar que las contraseñas de BD sean correctas
- Asegurar que MySQL esté corriendo
- Ejecutar `schema-database.sql`

### Error: "Access denied for user 'root'@'localhost'"
- Verificar usuario y contraseña en `application.properties`
- Asegurar que MySQL está configurado

### H2 Console No Aparece
- Asegurar que el perfil H2 esté activo
- Acceder a: http://localhost:8080/h2-console

## 📚 Documentación Adicional

- [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/3.5.7/reference/)
- [Spring Security](https://docs.spring.io/spring-security/docs/6.2.x/reference/)
- [Thymeleaf](https://www.thymeleaf.org/)

## 📄 Licencia
Este proyecto está bajo licencia MIT

## 👨‍💻 Autor
papoi-dvv

## 🤝 Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o pull request.

---

**Última actualización:** Noviembre 11, 2025

