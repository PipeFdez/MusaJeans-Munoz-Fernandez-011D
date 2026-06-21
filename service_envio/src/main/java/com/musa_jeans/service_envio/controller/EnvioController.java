package com.musa_jeans.service_envio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_envio.model.Envio;
import com.musa_jeans.service_envio.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/envio")
@Tag(name = "Envío", description = "Operaciones relacionadas con los envíos")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Operation(summary = "Obtener todos los envíos", description = "Retorna una lista completa de todos los envíos registrados")
    @GetMapping
    public List<Envio> listar() {
        return envioService.listarTodos();
    }

    @Operation(summary = "Obtener un envío por su ID", description = "Retorna un envío específico")
    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtener(@PathVariable Long id) {

        Envio envio = envioService.buscarPorId(id).orElse(null);

        if (envio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(envio);
    }

    @Operation(summary = "Registrar un envío", description = "Ingresa un envío a la base de datos")
    @PostMapping
    public ResponseEntity<Envio> guardar(@RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.guardar(envio));
    }

    @Operation(summary = "Actualizar un envío", description = "Actualiza un envío por su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(
            @PathVariable Long id,
            @RequestBody Envio envioActualizado) {

        Envio envio = envioService.actualizar(id, envioActualizado);

        if (envio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(envio);
    }

    @Operation(summary = "Eliminar un envío", description = "Elimina un envío por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        Envio envio = envioService.buscarPorId(id).orElse(null);

        if (envio == null) {
            return ResponseEntity.notFound().build();
        }

        envioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}