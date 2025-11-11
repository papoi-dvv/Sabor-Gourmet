package com.saborgourmet.service;

import com.saborgourmet.model.Bitacora;
import com.saborgourmet.model.Usuario;
import com.saborgourmet.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BitacoraService {
    @Autowired
    private BitacoraRepository bitacoraRepository;

    public Bitacora registrar(Usuario usuario, String accion, String tablaAfectada, 
                              Integer registroId, String descripcion) {
        Bitacora bitacora = new Bitacora(accion, tablaAfectada, registroId, descripcion);
        bitacora.setUsuario(usuario);
        return bitacoraRepository.save(bitacora);
    }

    public List<Bitacora> obtenerPorTabla(String tablaAfectada) {
        return bitacoraRepository.findByTablaAfectada(tablaAfectada);
    }

    public List<Bitacora> obtenerPorUsuario(Integer idUsuario) {
        return bitacoraRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Bitacora> obtenerTodos() {
        return bitacoraRepository.findAll();
    }
}
