package com.saborgourmet.service;

import com.saborgourmet.model.Mesa;
import com.saborgourmet.model.enums.EstadoMesa;
import com.saborgourmet.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

    public Mesa crearMesa(Mesa mesa) {
        mesa.setFechaActualizacion(LocalDateTime.now());
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> obtenerPorId(Integer id) {
        return mesaRepository.findById(id);
    }

    public Optional<Mesa> obtenerPorNumero(Integer numero) {
        return mesaRepository.findByNumero(numero);
    }

    public Mesa actualizarMesa(Integer id, Mesa mesaActualizada) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada"));
        mesa.setNumero(mesaActualizada.getNumero());
        mesa.setCapacidad(mesaActualizada.getCapacidad());
        mesa.setEstado(mesaActualizada.getEstado());
        mesa.setFechaActualizacion(LocalDateTime.now());
        return mesaRepository.save(mesa);
    }

    public void cambiarEstado(Integer id, EstadoMesa nuevoEstado) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada"));
        mesa.setEstado(nuevoEstado);
        mesa.setFechaActualizacion(LocalDateTime.now());
        mesaRepository.save(mesa);
    }

    public void ocuparMesa(Integer id) {
        cambiarEstado(id, EstadoMesa.OCUPADA);
    }

    public void liberarMesa(Integer id) {
        cambiarEstado(id, EstadoMesa.DISPONIBLE);
    }

    public List<Mesa> obtenerDisponibles() {
        return mesaRepository.findByEstado(EstadoMesa.DISPONIBLE);
    }

    public List<Mesa> obtenerDisponiblesPorCapacidad(Integer capacidad) {
        return mesaRepository.findByCapacidadGreaterThanEqualOrderByNumero(capacidad);
    }

    public void eliminarMesa(Integer id) {
        mesaRepository.deleteById(id);
    }

    public List<Mesa> obtenerTodas() {
        return mesaRepository.findAll();
    }
}
