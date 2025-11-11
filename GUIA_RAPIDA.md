# 🚀 Guía Rápida - Sabor Gourmet REST API

## Inicio Rápido

### 1️⃣ Compilar
```bash
cd c:\Sabor-Gourmet
mvn clean install
```

### 2️⃣ Ejecutar
```bash
mvn spring-boot:run
```

### 3️⃣ Acceder
```
http://localhost:8080
```

---

## 🔐 Login

| Usuario | Contraseña | Rol |
|---------|-----------|-----|
| admin | Password123 | ADMIN |
| mozo | Password123 | MOZO |
| cocinero | Password123 | COCINERO |
| cajero | Password123 | CAJERO |

---

## 📱 Páginas Disponibles

### Dashboard (`/`)
- Estadísticas en tiempo real
- Acceso rápido a módulos
- Información del usuario

### Mesas (`/mesas.html`)
- ✅ Ver todas las mesas
- ✅ Crear mesa nueva
- ✅ Editar mesa
- ✅ Eliminar mesa
- ✅ Marcar como ocupada/disponible
- ✅ Estadísticas (total, disponibles, ocupadas, tasa)

### Platos (`/platos.html`)
- ✅ Ver todos los platos
- ✅ Crear plato nuevo
- ✅ Editar plato
- ✅ Eliminar plato
- ✅ Formateo de precios
- ✅ Filtro por tipo

### Clientes (`/clientes.html`)
- ✅ Ver todos los clientes
- ✅ Crear cliente nuevo
- ✅ Editar cliente
- ✅ Eliminar cliente
- ✅ Búsqueda por DNI
- ✅ Estado activo/inactivo

### Pedidos (`/pedidos.html`)
- ✅ Ver todos los pedidos
- ✅ Crear pedido (multi-select de platos)
- ✅ Cambiar estado
- ✅ Eliminar pedido
- ✅ Cálculo automático de total
- ✅ Búsqueda por estado

---

## 🔌 API REST Endpoints

### Autenticación
```
POST   /api/auth/login     - Iniciar sesión
GET    /api/auth/me        - Obtener usuario actual
POST   /api/auth/logout    - Cerrar sesión
```

### Clientes
```
GET    /api/clientes           - Listar todos
POST   /api/clientes           - Crear
GET    /api/clientes/{id}      - Obtener por ID
PUT    /api/clientes/{id}      - Actualizar
DELETE /api/clientes/{id}      - Eliminar
GET    /api/clientes/dni/{dni} - Buscar por DNI
GET    /api/clientes/activos   - Listar activos
```

### Mesas
```
GET    /api/mesas                    - Listar todas
POST   /api/mesas                    - Crear
GET    /api/mesas/{id}               - Obtener por ID
PUT    /api/mesas/{id}               - Actualizar
DELETE /api/mesas/{id}               - Eliminar
GET    /api/mesas/dashboard/stats    - Estadísticas
PUT    /api/mesas/{id}/ocupar        - Marcar ocupada
PUT    /api/mesas/{id}/liberar       - Marcar disponible
GET    /api/mesas/disponibles        - Listar disponibles
```

### Platos
```
GET    /api/platos                    - Listar todos
POST   /api/platos                    - Crear
GET    /api/platos/{id}               - Obtener por ID
PUT    /api/platos/{id}               - Actualizar
DELETE /api/platos/{id}               - Eliminar
GET    /api/platos/tipo/{tipo}        - Por tipo
GET    /api/platos/tipo/{tipo}/activos - Por tipo activos
GET    /api/platos/menu               - Menú completo
GET    /api/platos/activos            - Solo activos
```

### Pedidos
```
GET    /api/pedidos                       - Listar todos
POST   /api/pedidos                       - Crear
GET    /api/pedidos/{id}                  - Obtener por ID
PUT    /api/pedidos/{id}                  - Actualizar
DELETE /api/pedidos/{id}                  - Eliminar
POST   /api/pedidos/{id}/detalles         - Agregar plato
GET    /api/pedidos/estado/{estado}       - Por estado
GET    /api/pedidos/pendientes            - Pendientes
GET    /api/pedidos/enpreparacion         - En preparación
PUT    /api/pedidos/{id}/estado           - Cambiar estado
PUT    /api/pedidos/{id}/en-preparacion   - Marcar en prep.
PUT    /api/pedidos/{id}/servido          - Marcar servido
PUT    /api/pedidos/{id}/cerrar           - Cerrar pedido
GET    /api/pedidos/cocina/panel          - Panel cocina
```

### Admin
```
GET    /api/admin/dashboard/stats - Estadísticas
GET    /api/admin/bitacora        - Auditoría
```

---

## 💻 Uso en JavaScript

### Importar librerías
```html
<script src="/js/api.js"></script>
<script src="/js/utils.js"></script>
```

### Obtener datos
```javascript
// Obtener todos los clientes
const clientes = await ApiService.getClientes();

// Obtener cliente específico
const cliente = await ApiService.getCliente(1);

// Crear cliente
const nuevoCliente = {
  dni: "12345678",
  nombres: "Juan",
  apellidos: "Pérez",
  telefono: "9876543210",
  correo: "juan@email.com",
  estado: "ACTIVO"
};
await ApiService.createCliente(nuevoCliente);
```

### Mostrar notificaciones
```javascript
// Toast de éxito
UIUtils.showToast('Cliente creado correctamente', 'success');

// Toast de error
UIUtils.showToast('Error al crear cliente', 'danger');

// Toast de info
UIUtils.showToast('Cargando datos...', 'info');
```

### Formatear datos
```javascript
// Moneda (S/.)
UIUtils.formatCurrency(1500.50);  // S/. 1,500.50

// Fecha
UIUtils.formatDate(new Date());   // 11/11/2025 14:30:45

// Estado con badge
UIUtils.getStatusBadge('DISPONIBLE', 'mesa');  // badge-disponible
```

### Confirmar acciones
```javascript
UIUtils.showConfirmModal(
  'Eliminar',
  '¿Está seguro?',
  async () => {
    // Acción confirmada
    await ApiService.deleteCliente(1);
  }
);
```

### Control de permisos
```javascript
// Verificar si tiene rol
if (AuthHelper.hasRole('ADMIN')) {
  // Solo para admin
}

// Verificar múltiples roles
if (AuthHelper.hasAnyRole(['ADMIN', 'MOZO'])) {
  // Para admin o mozo
}

// Obtener roles
const roles = AuthHelper.getRoles();  // ['ROLE_ADMIN']
```

---

## 🎨 Personalización

### Colores
```css
:root {
  --primary: #d4541a;          /* Naranja */
  --secondary: #2c3e50;        /* Azul oscuro */
  --success: #27ae60;          /* Verde */
  --warning: #f39c12;          /* Amarillo */
  --danger: #e74c3c;           /* Rojo */
}
```

### Usar en CSS
```css
.mi-elemento {
  background-color: var(--primary);
  color: var(--secondary);
}
```

---

## 🐛 Troubleshooting

### Error: "Usuario no autenticado"
- Ir a http://localhost:8080/login.html
- Ingresar credenciales válidas
- Refrescar la página

### Error: "Acceso denegado"
- Verificar que el usuario tiene el rol correcto
- Revisar SecurityConfig.java para permisos
- Logout y login con otro usuario

### Error: "404 No encontrado"
- Verificar que el servidor está corriendo (http://localhost:8080)
- Revisar que la URL es correcta
- Revisar console del navegador para más detalles

### Error: "CORS error"
- Ya está configurado en SecurityConfig.java
- Si agregar nuevos orígenes, actualizar CORS config

---

## 📊 Ejemplos de Uso

### Crear una mesa
```javascript
const mesa = {
  numero: 5,
  capacidad: 4,
  estado: "DISPONIBLE"
};
const nuevaMesa = await ApiService.createMesa(mesa);
console.log('Mesa creada:', nuevaMesa);
```

### Crear un pedido
```javascript
const pedido = {
  mesa: { idMesa: 1 },
  cliente: { idCliente: 1 },
  estado: "PENDIENTE",
  detalles: [
    { plato: { idPlato: 1 }, cantidad: 2, precioUnitario: 25.50 },
    { plato: { idPlato: 3 }, cantidad: 1, precioUnitario: 15.00 }
  ]
};
const nuevoPedido = await ApiService.createPedido(pedido);
```

### Obtener panel de cocina
```javascript
const panel = await ApiService.getPanelCocina();
console.log('Pendientes:', panel.pendientes);
console.log('En preparación:', panel.enPreparacion);
```

---

## 📱 Responsividad

La aplicación está optimizada para:
- 📱 Móviles (320px y más)
- 📱 Tablets (768px y más)
- 💻 Desktops (1200px y más)

Todos los elementos se adaptan automáticamente.

---

## 🔒 Seguridad

- ✅ Session-based authentication
- ✅ CORS configurado
- ✅ Role-based access control
- ✅ Input validation en cliente
- ✅ CSRF disabled (API REST)
- ✅ Spring Security enabled

---

## 📞 Soporte

Para problemas o preguntas:
1. Revisar esta guía
2. Verificar RESUMEN_MIGRACION.md
3. Revisar MIGRACION.md para detalles técnicos
4. Revisar logs de Spring Boot

---

**Última actualización**: 11 de Noviembre, 2025
**Versión**: 1.0.0-REST-API
