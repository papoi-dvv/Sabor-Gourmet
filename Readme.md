## 📋 DESCRIPCIÓN DE CADA CAPA

### **1. MODEL (Entidades JPA)**
- `Usuario.java` - Entidad para autenticación
- `Cliente.java` - Datos de clientes
- `Mesa.java` - Gestión de mesas
- `Plato.java` - Menú del restaurante
- `Pedido.java` - Pedidos realizados
- `DetallePedido.java` - Items de cada pedido
- `Bitacora.java` - Registro de auditoría
- `enums/` - Enumeraciones (Rol, Estados, etc.)

### **2. REPOSITORY (Acceso a Datos)**
Interfaces que extienden `JpaRepository<T, ID>`
- Métodos CRUD automáticos
- Consultas personalizadas con `@Query`

### **3. SERVICE (Lógica de Negocio)**
- Clases con `@Service`
- Lógica de negocio y validaciones
- Transacciones con `@Transactional`

### **4. CONTROLLER (Controladores MVC)**
- Clases con `@Controller`
- Mapeo de rutas con `@GetMapping`, `@PostMapping`
- Retornan vistas Thymeleaf

### **5. ASPECT (AOP)**
- `AuditoriaAspect.java` - Intercepta operaciones y registra en bitácora

### **6. CONFIG (Configuraciones)**
- `SecurityConfig.java` - Configuración de Spring Security
- `WebConfig.java` - Configuraciones adicionales (opcional)

### **7. TEMPLATES (Vistas Thymeleaf)**
- **fragments/** - Componentes reutilizables (header, navbar, footer)
- **cliente/**, **mesa/**, **plato/**, **pedido/** - CRUDs por módulo
- **admin/** - Panel administrativo
- `login.html` - Página de login
- `index.html` - Dashboard principal

### **8. STATIC (Recursos Estáticos)**
- **css/** - Bootstrap 5 + estilos personalizados
- **js/** - Scripts JavaScript
- **images/** - Imágenes y logos

---

## 🎯 DISTRIBUCIÓN DE ARCHIVOS POR ROL

### **ADMIN tiene acceso a:**
```
/admin/dashboard
/admin/bitacora
/clientes/** (CRUD completo)
/mesas/** (CRUD completo)
/platos/** (CRUD completo)
/pedidos/** (CRUD completo)
```

### **MOZO tiene acceso a:**
```
/clientes/** (crear, listar)
/mesas/dashboard (ver disponibilidad)
/mesas/asignar, /mesas/liberar
/pedidos/nuevo
/pedidos/lista
```

### **COCINERO tiene acceso a:**
```
/pedidos/cocina (pedidos pendientes)
/pedidos/cambiar-estado
```

### **CAJERO tiene acceso a:**
```
/pedidos/lista (solo lectura)
/pedidos/facturar
/ventas/** (futuro)
```

---

## 📊 ESTADÍSTICAS DEL PROYECTO

```
Total Clases Java:     ~30 archivos
Total Templates HTML:  ~20 archivos
Total Líneas de Código: ~3000-4000 LOC
Tiempo Estimado:       6-8 horas (básico funcional)
```

---