package com.musa_jeans.service_reporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_reporte.model.Reporte;
import com.musa_jeans.service_reporte.service.ReporteService;

@RestController
@RequestMapping("/api/v1/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<Reporte> guardar() {
        return ResponseEntity.ok(reporteService.generarYGuardarReporte());
    }

    @GetMapping("/historial")
    public ResponseEntity<List<Reporte>> listarTodos() {
        return ResponseEntity.ok(reporteService.listarTodos());
    }
}