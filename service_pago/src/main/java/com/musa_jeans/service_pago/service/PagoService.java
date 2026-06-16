package com.musa_jeans.service_pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_pago.model.Pago;
import com.musa_jeans.service_pago.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }

    public List<Pago> buscarPorEstado(String estado) {
        return pagoRepository.findByEstadoIgnoreCase(estado);
    }

    public Pago obtenerCompleto(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);

        if (pago != null) {

            Venta venta = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8084/ventas/" + pago.getVentaId())
                    .retrieve()
                    .bodyToMono(Venta.class)
                    .block();

            pago.setVenta(venta);
        }
        return pago;
    }

}