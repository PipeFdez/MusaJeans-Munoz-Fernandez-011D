package com.musa_jeans.service_venta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_venta.model.DetalleVenta;
import com.musa_jeans.service_venta.service.DetalleVentaService;

@RestController
@RequestMapping("/api/v1/detalle-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<DetalleVenta>> buscarPorVenta(@PathVariable Long idVenta) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorVenta(idVenta);

        if (detalles == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(detalles);
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalleVenta) {
        return ResponseEntity.ok(detalleVentaService.guardar(detalleVenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);

        return ResponseEntity.ok("Detalle eliminado correctamente");
    }

}