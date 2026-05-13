package com.musa_jeans.service_jean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_jean.model.Jean;
import com.musa_jeans.service_jean.service.JeanService;

@RestController
@RequestMapping("api/v1/jean")
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

    @PutMapping("/{id}")
    public ResponseEntity<Jean> actualizarJean(@PathVariable Long id, @RequestBody Jean jeanActualizado) {
        Jean jean = jeanService.buscarPorId(id).orElse(null);

        if (jean != null) {

            jean.setPrecio(jeanActualizado.getPrecio());
            jean.setDescripcion(jeanActualizado.getDescripcion());

            jeanService.guardar(jean);
            return ResponseEntity.ok(jean);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        Jean jean = jeanService.buscarPorId(id).orElse(null);

        if (jean != null) {
            jeanService.eliminar(id);
            return ResponseEntity.ok("Jean eliminado");
        }

        return ResponseEntity.notFound().build();
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