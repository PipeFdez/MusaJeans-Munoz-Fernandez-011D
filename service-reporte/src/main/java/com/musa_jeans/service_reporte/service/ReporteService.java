package com.musa_jeans.service_reporte.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.musa_jeans.service_reporte.model.Reporte;
import com.musa_jeans.service_reporte.repository.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ReporteRepository reporteRepository;

    public Reporte generarYGuardarReporte() {
        List<Map> ventas = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/v1/venta")
                .retrieve()
                .bodyToFlux(Map.class)
                .collectList()
                .block();

        long montoTotal = 0;
        int cantidadVentas = 0;

        if (ventas != null) {
            cantidadVentas = ventas.size();
            
            for (Map venta : ventas) {
                if (venta.get("total") != null) {
                    montoTotal += Long.parseLong(venta.get("total").toString());
                }
            }
        }

        Reporte nuevoReporte = new Reporte();
        nuevoReporte.setFechaGeneracion(new Date());
        nuevoReporte.setCantidadTotalVentas(cantidadVentas);
        nuevoReporte.setMontoTotalRecaudado(montoTotal);

        Reporte reporteGuardado = reporteRepository.save(nuevoReporte);

        reporteGuardado.setVentasDetalladas((List) ventas);

        return reporteGuardado;
    }

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }
}