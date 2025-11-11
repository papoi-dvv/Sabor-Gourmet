package com.saborgourmet.controller;

import com.saborgourmet.model.Plato;
import com.saborgourmet.model.enums.TipoPlato;
import com.saborgourmet.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {
    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<Plato>> listarPlatos() {
        return ResponseEntity.ok(platoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> obtenerPlato(@PathVariable Integer id) {
        return platoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plato> crearPlato(@RequestBody Plato plato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platoService.crearPlato(plato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plato> actualizarPlato(@PathVariable Integer id, @RequestBody Plato plato) {
        return ResponseEntity.ok(platoService.actualizarPlato(id, plato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPlato(@PathVariable Integer id) {
        platoService.eliminarPlato(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Plato>> obtenerPorTipo(@PathVariable TipoPlato tipo) {
        return ResponseEntity.ok(platoService.obtenerPorTipo(tipo));
    }

    @GetMapping("/tipo/{tipo}/activos")
    public ResponseEntity<List<Plato>> obtenerPlatosPorTipoActivos(@PathVariable TipoPlato tipo) {
        return ResponseEntity.ok(platoService.obtenerPlatosPorTipoActivos(tipo));
    }

    @GetMapping("/menu")
    public ResponseEntity<Map<String, List<Plato>>> obtenerMenu() {
        Map<String, List<Plato>> menu = new HashMap<>();
        menu.put("entradas", platoService.obtenerPlatosPorTipoActivos(TipoPlato.ENTRADA));
        menu.put("fondos", platoService.obtenerPlatosPorTipoActivos(TipoPlato.FONDO));
        menu.put("postres", platoService.obtenerPlatosPorTipoActivos(TipoPlato.POSTRE));
        menu.put("bebidas", platoService.obtenerPlatosPorTipoActivos(TipoPlato.BEBIDA));
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Plato>> obtenerActivos() {
        return ResponseEntity.ok(platoService.obtenerActivos());
    }
}
