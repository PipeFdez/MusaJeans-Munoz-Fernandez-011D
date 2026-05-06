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

import com.musa_jeans.service_jean.model.Jean;
import com.musa_jeans.service_jean.service.JeanService;

@RestController
@RequestMapping("/jeans")
public class JeanController {

    @Autowired
    private JeanService jeanService;

    @GetMapping
    public List<Jean> listar() {
        return jeanService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jean> obtener(@PathVariable Long id) {
        return jeanService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jean> guardar(@RequestBody Jean jean) {
        return ResponseEntity.ok(jeanService.guardar(jean));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        jeanService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/marca/{nombre}")
    public ResponseEntity<List<Jean>> buscarPorMarcaNombre(@PathVariable String nombre) {
        List<Jean> listaJeans = jeanService.buscarPorMarcaNombre(nombre);

        if (listaJeans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaJeans);
    }

    @GetMapping("/talla/{talla}")
    public ResponseEntity<List<Jean>> buscarPorTalla(@PathVariable String talla) {
        List<Jean> listaTallas = jeanService.buscarPorTalla(talla);

        if (listaTallas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaTallas);
    }
}