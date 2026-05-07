package com.musa_jeans.service_cliente.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa_jeans.service_cliente.model.Cliente;
import com.musa_jeans.service_cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorRut(String rut) {
        return clienteRepository.findByRutIgnoreCase(rut);
    }

    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public String eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
        return "Cliente eliminado";
    }
}
