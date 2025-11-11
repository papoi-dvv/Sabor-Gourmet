# Migración de Thymeleaf a REST API + JavaScript

## Cambios Realizados

### 1. **Dependencias Maven (pom.xml)**
- ❌ Eliminado: `spring-boot-starter-thymeleaf`
- ❌ Eliminado: `thymeleaf-extras-springsecurity6`
- ✅ Agregado: `jackson-databind` (para JSON serialization)
- ✅ Mantenido: `spring-boot-starter-web`

### 2. **Configuración de Seguridad (SecurityConfig.java)**
- ✅ Agregado: CORS support para localhost:8080 y localhost:3000
- ✅ Actualizado: Endpoints a `/api/*` para REST
- ✅ CSRF deshabilitado (típico en APIs REST)
- ✅ Form login URL: `/api/auth/login`
- ✅ Logout URL: `/api/auth/logout`

### 3. **Controladores - Migración a REST**

#### AuthController.java (NUEVO)
```java
@RestController
@RequestMapping("/api/auth")
- GET  /api/auth/me          → Obtener usuario actual
- POST /api/auth/logout      → Cerrar sesión
```

#### ClienteController.java
```java
@RestController
@RequestMapping("/api/clientes")
- GET    /api/clientes              → Listar todos
- GET    /api/clientes/{id}         → Obtener por ID
- POST   /api/clientes              → Crear
- PUT    /api/clientes/{id}         → Actualizar
- DELETE /api/clientes/{id}         → Eliminar
- GET    /api/clientes/dni/{dni}    → Buscar por DNI
- GET    /api/clientes/activos      → Listar activos
```

#### MesaController.java
```java
@RestController
@RequestMapping("/api/mesas")
- GET    /api/mesas                           → Listar todos
- GET    /api/mesas/{id}                      → Obtener por ID
- POST   /api/mesas                           → Crear
- PUT    /api/mesas/{id}                      → Actualizar
- DELETE /api/mesas/{id}                      → Eliminar
- GET    /api/mesas/dashboard/stats           → Estadísticas
- PUT    /api/mesas/{id}/ocupar               → Marcar como ocupada
- PUT    /api/mesas/{id}/liberar              → Marcar como disponible
- GET    /api/mesas/disponibles               → Listar disponibles
- GET    /api/mesas/disponibles/capacidad/{n} → Filtrar por capacidad
```

#### PlatoController.java
```java
@RestController
@RequestMapping("/api/platos")
- GET  /api/platos                      → Listar todos
- GET  /api/platos/{id}                 → Obtener por ID
- POST /api/platos                      → Crear
- PUT  /api/platos/{id}                 → Actualizar
- DELETE /api/platos/{id}               → Eliminar
- GET  /api/platos/tipo/{tipo}          → Buscar por tipo
- GET  /api/platos/tipo/{tipo}/activos  → Platos activos por tipo
- GET  /api/platos/menu                 → Obtener menú completo
- GET  /api/platos/activos              → Listar platos activos
```

#### PedidoController.java
```java
@RestController
@RequestMapping("/api/pedidos")
- GET    /api/pedidos                      → Listar todos
- GET    /api/pedidos/{id}                 → Obtener por ID
- POST   /api/pedidos                      → Crear
- PUT    /api/pedidos/{id}                 → Actualizar
- DELETE /api/pedidos/{id}                 → Eliminar
- POST   /api/pedidos/{id}/detalles        → Agregar detalle
- GET    /api/pedidos/estado/{estado}      → Buscar por estado
- GET    /api/pedidos/pendientes           → Listar pendientes
- GET    /api/pedidos/enpreparacion        → En preparación
- PUT    /api/pedidos/{id}/estado          → Cambiar estado
- PUT    /api/pedidos/{id}/en-preparacion  → Marcar en preparación
- PUT    /api/pedidos/{id}/servido         → Marcar servido
- PUT    /api/pedidos/{id}/cerrar          → Cerrar pedido
- GET    /api/pedidos/cocina/panel         → Panel de cocina
```

#### AdminController.java
```java
@RestController
@RequestMapping("/api/admin")
- GET /api/admin/dashboard/stats → Estadísticas del dashboard
- GET /api/admin/bitacora       → Obtener auditoría
```

### 4. **Archivos Estáticos - Interfaz Frontend**

#### CSS (style.css)
- Variables CSS personalizadas (colores, fuentes)
- Estilos Bootstrap 5 extendidos
- Navbars, cards, tables, forms, badges
- Responsive design (móvil-first)
- Animaciones y transiciones
- Temas de estado (disponible, ocupada, pendiente, etc.)

#### JavaScript - Módulos

**api.js** - Cliente REST
- Clase `ApiService` con métodos para cada endpoint
- Gestión automática de credenciales (`credentials: 'include'`)
- Headers JSON preconfigurados
- Manejo de errores centralizado

**utils.js** - Utilidades UI
- Clase `UIUtils`: Toasts, modales, formateos, validación
- Clase `AuthHelper`: Gestión de autenticación y roles
- Clase `PageHelper`: Control de permisos de página
- Inicialización automática de usuario

#### HTML - Páginas Principales

1. **index.html** - Dashboard
   - Navbar con menú basado en roles
   - Estadísticas en tiempo real
   - Cards de acceso a módulos
   - Footer

2. **login.html** - Login
   - Formulario con Bootstrap 5
   - Credenciales de prueba visibles
   - Diseño atractivo con gradientes
   - Responsive en móviles

3. **mesas.html** - Gestión de Mesas
   - Tabla con estadísticas
   - Grid de mesas con cards
   - Modal para crear/editar
   - Botones ocupar/liberar
   - Auto-refresh de datos

4. **platos.html** - Gestión de Platos
   - Tabla con precios y estado
   - Modal para CRUD
   - Filtro por tipo
   - Formateo de moneda

5. **clientes.html** - Gestión de Clientes
   - Tabla con datos personales
   - Modal para CRUD
   - Búsqueda y estado
   - Validación de DNI

6. **pedidos.html** - Gestión de Pedidos
   - Tabla con detalles
   - Modal de creación multi-select
   - Estado con badges
   - Cálculo automático de totales

### 5. **Flujo de Autenticación**

```
1. Usuario accede a /login.html
2. Ingresa credenciales (form-based)
3. POST a /api/auth/login
4. Spring Security gestiona la sesión
5. Redirect a /
6. JavaScript verifica estado con /api/auth/me
7. Si autenticado → Renderiza dashboard
8. Si no → Redirige a login.html
```

### 6. **Control de Acceso por Roles**

```html
<!-- En HTML: atributo data-require-role -->
<a href="/admin.html" data-require-role="ADMIN">
  Panel Admin
</a>

<!-- JavaScript oculta elementos no autorizados -->
PageHelper.hideElementsForUnauthorized(['ADMIN', 'MOZO']);
```

### 7. **Características de la Nueva Interfaz**

✅ **Responsive**: Mobile-first, 100% adaptable
✅ **Bootstrap 5**: Componentes profesionales
✅ **Temas**: Colores personalizados (#d4541a, #2c3e50)
✅ **Notificaciones**: Toast automáticos
✅ **Validación**: Forma-level validation
✅ **Loading**: Spinners y feedback visual
✅ **Confirmaciones**: Modales de confirmación
✅ **Mensajes**: Alert boxes contextuales
✅ **Formateo**: Moneda, fechas, estados
✅ **Modales**: Para CRUD operations
✅ **Tablas**: Con hover y responsive
✅ **Paginación**: Preparada para implementación

### 8. **Cómo Iniciar**

```bash
# 1. Limpiar y compilar
mvn clean install

# 2. Ejecutar
mvn spring-boot:run

# 3. Acceder a
http://localhost:8080

# 4. Credenciales (dependen del archivo SQL cargado)
# - Si cargaste `data-database.sql` (por defecto en este repo):
#     admin / password
#     mozo1 / password
#     cocinero1 / password
#     cajero1 / password
# - Si cargaste `data-inicial.sql`:
#     admin / Password123
#     mozo / Password123
#     cocinero / Password123
#     cajero / Password123
```

### 9. **Estructura de Carpetas Static**

```
src/main/resources/static/
├── index.html          # Dashboard
├── login.html          # Login
├── mesas.html          # Gestión mesas
├── platos.html         # Gestión platos
├── clientes.html       # Gestión clientes
├── pedidos.html        # Gestión pedidos
├── css/
│   └── style.css       # Estilos personalizados
├── js/
│   ├── api.js          # Cliente REST
│   └── utils.js        # Utilidades UI
└── images/             # (vacío, listo para agregar)
```

### 10. **Ventajas de esta Arquitectura**

- ✅ **Separación de capas**: Frontend totalmente desacoplado
- ✅ **Escalabilidad**: Fácil de agregar más páginas
- ✅ **Performance**: Carga inicial rápida, lazy loading
- ✅ **Mantenibilidad**: Código limpio y modular
- ✅ **Testing**: APIs REST fáciles de testear
- ✅ **Flexibilidad**: Posible usar cualquier otro frontend (React, Vue, etc.)
- ✅ **Seguridad**: CORS + Session-based authentication
- ✅ **Accesibilidad**: Bootstrap 5 + semantic HTML
- ✅ **UX**: Interfaces intuitivas y responsive

### 11. **Próximos Pasos (Opcionales)**

1. Agregar panel de cocina (cocina.html)
2. Agregar panel de reportes (reportes.html)
3. Implementar paginación en tablas
4. Agregar búsqueda/filtros avanzados
5. Exportar reportes a PDF
6. Agregar gráficos con Chart.js
7. Implementar WebSockets para updates en tiempo real
8. Agregar autenticación por JWT (en lugar de sesión)
9. Deploy a producción (Tomcat, Docker)
10. Agregar PWA features (offline support)

---

**Estado**: ✅ Migración completada
**Fecha**: 11 de Noviembre, 2025
**Versión**: 1.0.0-REST-API

