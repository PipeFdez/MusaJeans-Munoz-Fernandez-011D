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

import com.musa_jeans.service_jean.model.Marca;
import com.musa_jeans.service_jean.service.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    
    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> listar() {
        return marcaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtener(@PathVariable Long id) {
        return marcaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marca> guardar(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.guardar(marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        marcaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}