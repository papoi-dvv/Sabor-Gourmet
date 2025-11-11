package com.saborgourmet.model.enums;

public enum EstadoMesa {
    DISPONIBLE("Disponible"),
    OCUPADA("Ocupada"),
    RESERVADA("Reservada"),
    MANTENIMIENTO("Mantenimiento");

    private final String descripcion;

    EstadoMesa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
