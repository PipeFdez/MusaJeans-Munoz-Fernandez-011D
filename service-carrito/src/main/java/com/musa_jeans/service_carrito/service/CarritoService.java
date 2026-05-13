package com.musa_jeans.service_carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_carrito.model.Carrito;
import com.musa_jeans.service_carrito.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarTodos() {
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream()
                .map(this::enriquecerConCliente)
                .toList();
    }

    public Carrito buscarPorCliente(String rutCliente) {
        Carrito carrito = carritoRepository.findByRutCliente(rutCliente);

        if (carrito != null) {
            return enriquecerConCliente(carrito);
        }

        return null;
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminar(Long idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    private Carrito enriquecerConCliente(Carrito carrito) {
        if (carrito.getRutCliente() != null) {
            try {
                Object cliente = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8083/api/v1/cliente/" + carrito.getRutCliente())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();

                carrito.setDatosCliente(cliente);
            } catch (Exception e) {
                carrito.setDatosCliente("Información cliente no disponible");
            }
        }
        return carrito;
    }

}