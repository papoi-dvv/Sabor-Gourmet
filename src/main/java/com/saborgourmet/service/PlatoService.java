package com.saborgourmet.service;

import com.saborgourmet.model.Plato;
import com.saborgourmet.model.enums.TipoPlato;
import com.saborgourmet.repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlatoService {
    @Autowired
    private PlatoRepository platoRepository;

    public Plato crearPlato(Plato plato) {
        return platoRepository.save(plato);
    }

    public Optional<Plato> obtenerPorId(Integer id) {
        return platoRepository.findById(id);
    }

    public Plato actualizarPlato(Integer id, Plato platoActualizado) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado"));
        plato.setNombre(platoActualizado.getNombre());
        plato.setTipo(platoActualizado.getTipo());
        plato.setPrecio(platoActualizado.getPrecio());
        plato.setDescripcion(platoActualizado.getDescripcion());
        plato.setEstado(platoActualizado.getEstado());
        return platoRepository.save(plato);
    }

    public void eliminarPlato(Integer id) {
        platoRepository.deleteById(id);
    }

    public List<Plato> obtenerPorTipo(TipoPlato tipo) {
        return platoRepository.findByTipo(tipo);
    }

    public List<Plato> obtenerActivos() {
        return platoRepository.findByEstado("ACTIVO");
    }

    public List<Plato> obtenerPlatosPorTipoActivos(TipoPlato tipo) {
        return platoRepository.findByTipoAndEstado(tipo, "ACTIVO");
    }

    public List<Plato> obtenerTodos() {
        return platoRepository.findAll();
    }
}
