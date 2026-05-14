package com.musa_jeans.service_venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_venta.model.DetalleVenta;
import com.musa_jeans.service_venta.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<DetalleVenta> buscarPorVenta(Long idVenta) {
        List<DetalleVenta> detalles = detalleVentaRepository.findByIdVenta(idVenta);

        return detalles.stream()
                .map(this::enriquecerConJean)
                .toList();
    }

    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public void eliminar(Long idDetalleVenta) {
        detalleVentaRepository.deleteById(idDetalleVenta);
    }

    private DetalleVenta enriquecerConJean(DetalleVenta detalleVenta) {
        if (detalleVenta.getIdJean() != null) {
            try {
                Object jean = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/v1/jean/" + detalleVenta.getIdJean())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();

                detalleVenta.setDatosJean(jean);
            } catch (Exception e) {
                detalleVenta.setDatosJean("Información no disponible");
            }
        }
        return detalleVenta;
    }

}