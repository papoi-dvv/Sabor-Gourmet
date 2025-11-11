package com.saborgourmet.service;

import com.saborgourmet.model.DetallePedido;
import com.saborgourmet.model.Pedido;
import com.saborgourmet.model.Plato;
import com.saborgourmet.model.enums.EstadoPedido;
import com.saborgourmet.repository.PedidoRepository;
import com.saborgourmet.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obtenerPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    public Pedido actualizarPedido(Integer id, Pedido pedidoActualizado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
        pedido.setEstado(pedidoActualizado.getEstado());
        return pedidoRepository.save(pedido);
    }

    public Pedido agregarDetallePedido(Integer idPedido, DetallePedido detalleNuevo) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));

        detalleNuevo.setPedido(pedido);
        pedido.agregarDetalle(detalleNuevo);
        pedidoRepository.save(pedido);
        return pedido;
    }

    public void cambiarEstado(Integer id, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
        pedido.setEstado(nuevoEstado);
        pedidoRepository.save(pedido);
    }

    public void marcarEnPreparacion(Integer id) {
        cambiarEstado(id, EstadoPedido.EN_PREPARACION);
    }

    public void marcarServido(Integer id) {
        cambiarEstado(id, EstadoPedido.SERVIDO);
    }

    public void cerrarPedido(Integer id) {
        cambiarEstado(id, EstadoPedido.CERRADO);
    }

    public List<Pedido> obtenerPendientes() {
        return pedidoRepository.findByEstado(EstadoPedido.PENDIENTE);
    }

    public List<Pedido> obtenerEnPreparacion() {
        return pedidoRepository.findByEstado(EstadoPedido.EN_PREPARACION);
    }

    public List<Pedido> obtenerPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> obtenerPorMesa(Integer idMesa) {
        return pedidoRepository.findByMesaIdMesa(idMesa);
    }

    public List<Pedido> obtenerPorCliente(Integer idCliente) {
        return pedidoRepository.findByClienteIdCliente(idCliente);
    }

    public void eliminarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }
}
