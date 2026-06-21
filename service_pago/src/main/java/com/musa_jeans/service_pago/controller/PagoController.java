package com.musa_jeans.service_pago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_pago.model.Pago;
import com.musa_jeans.service_pago.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/pago")
@Tag(name = "Pago", description = "Operaciones relacionadas con los pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Obtener todos los pagos", description = "Retorna una lista completa de todos los pagos registrados")
    @GetMapping
    public List<Pago> listar() {
        return pagoService.listarTodos();
    }

    @Operation(summary = "Obtener un pago por su ID", description = "Retorna una lista con un pago en especifico")
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtener(@PathVariable Long id) {
        Pago pago = pagoService.buscarPorId(id).orElse(null);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    @Operation(summary = "Registrar un pago", description = "Ingresa un pago a la base de datos")
    @PostMapping
    public ResponseEntity<Pago> guardar(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.guardar(pago));
    }

    @Operation(summary = "Eliminar un pago", description = "Elimina un pago por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Pago pago = pagoService.buscarPorId(id).orElse(null);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}