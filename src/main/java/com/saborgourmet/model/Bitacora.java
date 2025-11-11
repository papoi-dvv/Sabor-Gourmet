package com.saborgourmet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitacora")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBitacora;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(length = 50, nullable = false)
    private String accion;

    @Column(length = 50, nullable = false)
    private String tablaAfectada;

    @Column
    private Integer registroId;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    public Bitacora(String accion, String tablaAfectada, Integer registroId, String descripcion) {
        this.accion = accion;
        this.tablaAfectada = tablaAfectada;
        this.registroId = registroId;
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
    }
}
