package com.musa_jeans.service_venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_venta.model.DetalleVenta;
import com.musa_jeans.service_venta.model.Venta;
import com.musa_jeans.service_venta.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaService detalleVentaService;

    public List<Venta> listarTodos() {
        List<Venta> ventas = ventaRepository.findAll();

        return ventas.stream()
                .map(this::enriquecerVenta)
                .toList();
    }

    public Venta buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id).orElse(null);

        if (venta != null) {
            return enriquecerVenta(venta);
        }

        return null;
    }

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }

    private Venta enriquecerVenta(Venta venta) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorVenta(venta.getIdVenta());
        venta.setDetalles(detalles);

        if (venta.getRutCliente() != null) {
            try {
                Object cliente = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8083/api/v1/cliente/" + venta.getRutCliente())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();

                venta.setDatosCliente(cliente);
            } catch (Exception e) {
                venta.setDatosCliente("Información no disponible");
            }
        }
        return venta;
    }

}