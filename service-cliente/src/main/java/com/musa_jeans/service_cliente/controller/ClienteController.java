package com.musa_jeans.service_cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_cliente.model.Cliente;
import com.musa_jeans.service_cliente.service.ClienteService;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Cliente> buscarPorTalla(@PathVariable String rut) {
        Cliente cliente = clienteService.buscarPorRut(rut);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.registrarCliente(cliente));
    }

}
