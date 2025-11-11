package com.saborgourmet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 100, nullable = false)
    private String apellidos;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVO'")
    private String estado = "ACTIVO";

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference("cliente-pedidos")
    private List<Pedido> pedidos;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
