package com.musa_jeans.service_jean.controller;

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

import com.musa_jeans.service_jean.model.Modelo;
import com.musa_jeans.service_jean.service.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public List<Modelo> listar() {
        return modeloService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> obtener(@PathVariable Long id) {
        return modeloService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modelo> guardar(@RequestBody Modelo modelo) {
        return ResponseEntity.ok(modeloService.guardar(modelo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        modeloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}