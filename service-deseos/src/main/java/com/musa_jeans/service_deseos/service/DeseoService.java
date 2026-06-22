package com.musa_jeans.service_deseos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_deseos.model.Deseo;
import com.musa_jeans.service_deseos.repository.DeseoRepository;

import jakarta.transaction.Transactional;

@Service
public class DeseoService {

    @Autowired
    private DeseoRepository deseoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Deseo> listarPorCliente(Long clienteId) {
        List<Deseo> lista = deseoRepository.findByClienteId(clienteId);
        lista.forEach(this::enriquecerConJean);
        return lista;
    }

    private Deseo enriquecerConJean(Deseo deseo){
        if(deseo.getJeanId() != null){
            try{
                Object jean = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/jean/" + deseo.getJeanId())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

                deseo.setDatosJean(jean);
            }catch(Exception e){
                deseo.setDescripcion("información no disponible");
            }
        }
        return deseo;
    }

    public Deseo agregarDeseo(Deseo deseo) {
        return deseoRepository.save(deseo);
    }

    public String eliminarDeseo(Long id) {
        deseoRepository.deleteById(id);
        return "Producto eliminado";
    }

    @Transactional 
    public String vaciarListaPorCliente(Long clienteId) {
        deseoRepository.deleteByClienteId(clienteId);
        return "Wishlist vaciada por completo";
    }
}
