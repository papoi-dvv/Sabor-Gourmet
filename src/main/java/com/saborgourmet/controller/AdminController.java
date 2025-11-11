package com.saborgourmet.controller;

import com.saborgourmet.model.Bitacora;
import com.saborgourmet.service.BitacoraService;
import com.saborgourmet.service.PedidoService;
import com.saborgourmet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private BitacoraService bitacoraService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPedidos", pedidoService.obtenerTodos().size());
        stats.put("pedidosPendientes", pedidoService.obtenerPendientes().size());
        stats.put("totalUsuarios", usuarioService.obtenerTodos().size());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/bitacora")
    public ResponseEntity<List<Bitacora>> obtenerBitacora() {
        return ResponseEntity.ok(bitacoraService.obtenerTodos());
    }
}
