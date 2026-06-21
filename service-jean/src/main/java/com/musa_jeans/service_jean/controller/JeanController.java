package com.musa_jeans.service_jean.controller;

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

import com.musa_jeans.service_jean.model.Jean;
import com.musa_jeans.service_jean.service.JeanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/jean")
@Tag(name = "Jean", description = "Operaciones relacionadas con la gestión de Jeans")
public class JeanController {

    @Autowired
    private JeanService jeanService;
    
    @Operation(summary = "Obtener todos los jeans", description = "Retorna una lista completa con todos los jeans registrados")
    @GetMapping
    public List<Jean> listar() {
        return jeanService.listarTodos();
    }

    @Operation(summary = "Obtener un jean por su ID", description = "Retorna una lista con todos los datos de un jean")
    @GetMapping("/{id}")
    public ResponseEntity<Jean> obtener(@PathVariable Long id) {
        return jeanService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registra un jean en la base de datos", description = "Guarda un jean en la base de datos")
    @PostMapping
    public ResponseEntity<Jean> guardar(@RequestBody Jean jean) {
        return ResponseEntity.ok(jeanService.guardar(jean));
    }

    @Operation(summary = "Editar los datos del jean", description = "Edita todos los datos del jean, buscando por ID")
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

    @Operation(summary = "Borrar un jean", description = "Borra un jean por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        Jean jean = jeanService.buscarPorId(id).orElse(null);

        if (jean != null) {
            jeanService.eliminar(id);
            return ResponseEntity.ok("Jean eliminado");
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener todos los jeans por marca", description = "Retonar una lista con todos los jeans de una marca")
    @GetMapping("/marca/{nombre}")
    public ResponseEntity<List<Jean>> buscarPorMarcaNombre(@PathVariable String nombre) {
        List<Jean> listaJeans = jeanService.buscarPorMarcaNombre(nombre);

        if (listaJeans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaJeans);
    }

    @Operation(summary = "Obtener todos los jeans por una talla", description = "Retorna una lista con todos los jeans por talla (XS, M, XL...)")
    @GetMapping("/talla/{talla}")
    public ResponseEntity<List<Jean>> buscarPorTalla(@PathVariable String talla) {
        List<Jean> listaTallas = jeanService.buscarPorTalla(talla);

        if (listaTallas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaTallas);
    }
}