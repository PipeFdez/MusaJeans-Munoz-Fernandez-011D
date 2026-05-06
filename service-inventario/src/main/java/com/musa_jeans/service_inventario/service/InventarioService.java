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

    public Inventario obtenerInventarioCompleto(Long id){

        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if(inventario != null){
            return enriquecerConJean(inventario);
        }
        return null;

    }

     private Inventario enriquecerConJean(Inventario inventario){
        if(inventario.getJeanId() != null){
            try{
                Object jean = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/jean/" + inventario.getJeanId())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

                inventario.setDatosJean(jean);
            }catch(Exception e){
                inventario.setDatosJean("información no disponible");
            }
        }
        return inventario;
    }
}


