package com.saborgourmet.repository;

import com.saborgourmet.model.Pedido;
import com.saborgourmet.model.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByMesaIdMesa(Integer idMesa);
    List<Pedido> findByClienteIdCliente(Integer idCliente);
}
