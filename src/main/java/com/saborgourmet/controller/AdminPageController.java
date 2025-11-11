package com.saborgourmet.controller;

import com.saborgourmet.service.BitacoraService;
import com.saborgourmet.service.PedidoService;
import com.saborgourmet.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);
    
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BitacoraService bitacoraService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPedidos", pedidoService.obtenerTodos().size());
        stats.put("pedidosPendientes", pedidoService.obtenerPendientes().size());
        stats.put("totalUsuarios", usuarioService.obtenerTodos().size());
        model.addAllAttributes(stats);
        return "admin/dashboard";
    }

    @GetMapping({"", "/"})
    public String root() {
        // Redirige /admin al dashboard para evitar que el ResourceHttpRequestHandler
        // busque un recurso estático `admin.html` y devuelva 404.
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/bitacora")
    public String bitacora(Model model) {
        logger.info("Accediendo a /admin/bitacora");
        try {
            // Crear algunos registros de prueba si no existen
            if (bitacoraService.obtenerTodos().isEmpty()) {
                bitacoraService.registrar(null, "LOGIN", "usuarios", 1, "Usuario admin inició sesión");
                bitacoraService.registrar(null, "CREAR", "pedidos", 1, "Se creó un nuevo pedido");
                bitacoraService.registrar(null, "ACTUALIZAR", "mesas", 1, "Se actualizó el estado de la mesa");
            }
            model.addAttribute("registros", bitacoraService.obtenerTodos());
        } catch (Exception e) {
            logger.error("Error al obtener bitácora: ", e);
            model.addAttribute("registros", java.util.Collections.emptyList());
        }
        return "admin/bitacora";
    }
}
