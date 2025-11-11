package com.saborgourmet.controller;

import com.saborgourmet.model.DetallePedido;
import com.saborgourmet.model.Pedido;
import com.saborgourmet.model.enums.EstadoPedido;
import com.saborgourmet.service.PedidoService;
import com.saborgourmet.service.MesaService;
import com.saborgourmet.service.ClienteService;
import com.saborgourmet.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private MesaService mesaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Integer id) {
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crearPedido(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.actualizarPedido(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Integer id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/detalles")
    public ResponseEntity<DetallePedido> agregarDetalle(@PathVariable Integer id, @RequestBody DetallePedido detalle) {
        pedidoService.agregarDetallePedido(id, detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> obtenerPorEstado(@PathVariable EstadoPedido estado) {
        return ResponseEntity.ok(pedidoService.obtenerPorEstado(estado));
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Pedido>> obtenerPendientes() {
        return ResponseEntity.ok(pedidoService.obtenerPendientes());
    }

    @GetMapping("/enpreparacion")
    public ResponseEntity<List<Pedido>> obtenerEnPreparacion() {
        return ResponseEntity.ok(pedidoService.obtenerEnPreparacion());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        EstadoPedido nuevoEstado = EstadoPedido.valueOf(body.get("estado"));
        pedidoService.cambiarEstado(id, nuevoEstado);
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/en-preparacion")
    public ResponseEntity<Pedido> marcarEnPreparacion(@PathVariable Integer id) {
        pedidoService.marcarEnPreparacion(id);
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/servido")
    public ResponseEntity<Pedido> marcarServido(@PathVariable Integer id) {
        pedidoService.marcarServido(id);
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Pedido> cerrarPedido(@PathVariable Integer id) {
        pedidoService.cerrarPedido(id);
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cocina/panel")
    public ResponseEntity<Map<String, List<Pedido>>> obtenerPanelCocina() {
        Map<String, List<Pedido>> panel = new HashMap<>();
        panel.put("pendientes", pedidoService.obtenerPendientes());
        panel.put("enPreparacion", pedidoService.obtenerEnPreparacion());
        return ResponseEntity.ok(panel);
    }
}
