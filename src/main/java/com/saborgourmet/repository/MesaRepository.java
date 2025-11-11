package com.saborgourmet.repository;

import com.saborgourmet.model.Mesa;
import com.saborgourmet.model.enums.EstadoMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    Optional<Mesa> findByNumero(Integer numero);
    List<Mesa> findByEstado(EstadoMesa estado);
    List<Mesa> findByCapacidadGreaterThanEqualOrderByNumero(Integer capacidad);
}
