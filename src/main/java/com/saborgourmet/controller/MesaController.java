package com.saborgourmet.controller;

import com.saborgourmet.model.Mesa;
import com.saborgourmet.model.enums.EstadoMesa;
import com.saborgourmet.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<Mesa>> listarMesas() {
        return ResponseEntity.ok(mesaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obtenerMesa(@PathVariable Integer id) {
        return mesaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mesa> crearMesa(@RequestBody Mesa mesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.crearMesa(mesa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> actualizarMesa(@PathVariable Integer id, @RequestBody Mesa mesa) {
        return ResponseEntity.ok(mesaService.actualizarMesa(id, mesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMesa(@PathVariable Integer id) {
        mesaService.eliminarMesa(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        List<Mesa> mesas = mesaService.obtenerTodas();
        long disponibles = mesas.stream().filter(Mesa::estaDisponible).count();
        long ocupadas = mesas.size() - disponibles;
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", mesas.size());
        stats.put("disponibles", disponibles);
        stats.put("ocupadas", ocupadas);
        stats.put("tasaOcupacion", Math.round((ocupadas * 100.0) / mesas.size()));
        
        return ResponseEntity.ok(stats);
    }

    @PutMapping("/{id}/ocupar")
    public ResponseEntity<Mesa> ocuparMesa(@PathVariable Integer id) {
        mesaService.ocuparMesa(id);
        return mesaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/liberar")
    public ResponseEntity<Mesa> liberarMesa(@PathVariable Integer id) {
        mesaService.liberarMesa(id);
        return mesaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Mesa>> obtenerDisponibles() {
        return ResponseEntity.ok(mesaService.obtenerDisponibles());
    }

    @GetMapping("/disponibles/capacidad/{capacidad}")
    public ResponseEntity<List<Mesa>> obtenerDisponiblesPorCapacidad(@PathVariable Integer capacidad) {
        return ResponseEntity.ok(mesaService.obtenerDisponiblesPorCapacidad(capacidad));
    }
}
