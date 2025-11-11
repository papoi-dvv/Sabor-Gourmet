package com.saborgourmet.service;

import com.saborgourmet.model.Cliente;
import com.saborgourmet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new IllegalArgumentException("El DNI ya existe");
        }
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> obtenerPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> obtenerPorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    public Cliente actualizarCliente(Integer id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        cliente.setNombres(clienteActualizado.getNombres());
        cliente.setApellidos(clienteActualizado.getApellidos());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setCorreo(clienteActualizado.getCorreo());
        cliente.setEstado(clienteActualizado.getEstado());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> obtenerActivos() {
        return clienteRepository.findByEstado("ACTIVO");
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }
}
