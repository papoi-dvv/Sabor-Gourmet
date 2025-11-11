package com.saborgourmet.repository;

import com.saborgourmet.model.Plato;
import com.saborgourmet.model.enums.TipoPlato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
    List<Plato> findByTipo(TipoPlato tipo);
    List<Plato> findByEstado(String estado);
    List<Plato> findByTipoAndEstado(TipoPlato tipo, String estado);
}
