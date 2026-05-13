package com.musa_jeans.service_carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_carrito.model.ProductoCarrito;
import com.musa_jeans.service_carrito.repository.ProductoCarritoRepository;

@Service
public class ProductoCarritoService {

    @Autowired
    private ProductoCarritoRepository productoCarritoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<ProductoCarrito> listarTodos() {
        List<ProductoCarrito> productos = productoCarritoRepository.findAll();

        return productos.stream()
                .map(this::enriquecerConJean)
                .toList();
    }

    public List<ProductoCarrito> buscarPorCarrito(Long idCarrito) {
        List<ProductoCarrito> productos = productoCarritoRepository.findByIdCarrito(idCarrito);

        return productos.stream()
                .map(this::enriquecerConJean)
                .toList();
    }

    public ProductoCarrito guardar(ProductoCarrito productoCarrito) {
        return productoCarritoRepository.save(productoCarrito);
    }

    public void eliminar(Long idProductoCarrito) {
        productoCarritoRepository.deleteById(idProductoCarrito);
    }

    private ProductoCarrito enriquecerConJean(ProductoCarrito productoCarrito) {
        if (productoCarrito.getIdJean() != null) {
            try {
                Object jean = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/v1/jean/" + productoCarrito.getIdJean())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();

                productoCarrito.setDatosJean(jean);
            } catch (Exception e) {
                productoCarrito
                        .setDatosJean("Información no disponible");
            }
        }
        return productoCarrito;
    }

}