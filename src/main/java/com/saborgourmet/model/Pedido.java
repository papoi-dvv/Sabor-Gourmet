package com.saborgourmet.model;

import com.saborgourmet.model.enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonBackReference("cliente-pedidos")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_mesa", nullable = false)
    @JsonBackReference("mesa-pedidos")
    private Mesa mesa;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    @Column(precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("pedido-detalles")
    private List<DetallePedido> detalles = new ArrayList<>();

    public void agregarDetalle(DetallePedido detalle) {
        detalles.add(detalle);
        detalle.setPedido(this);
        recalcularTotal();
    }

    public void recalcularTotal() {
        total = detalles.stream()
                .map(DetallePedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean estaPendiente() {
        return estado == EstadoPedido.PENDIENTE;
    }

    public boolean estaEnPreparacion() {
        return estado == EstadoPedido.EN_PREPARACION;
    }
}
