package com.musa_jeans.service_inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_inventario.model.Inventario;
import com.musa_jeans.service_inventario.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List <Inventario> listarPorNombre(){
        return inventarioRepository.findAll();
    }
}
