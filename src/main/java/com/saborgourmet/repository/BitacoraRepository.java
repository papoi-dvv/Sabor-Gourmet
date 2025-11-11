package com.saborgourmet.repository;

import com.saborgourmet.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Integer> {
    List<Bitacora> findByTablaAfectada(String tablaAfectada);
    List<Bitacora> findByUsuarioIdUsuario(Integer idUsuario);
}
