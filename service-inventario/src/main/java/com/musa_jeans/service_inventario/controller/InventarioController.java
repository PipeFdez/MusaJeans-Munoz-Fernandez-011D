package com.musa_jeans.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_inventario.model.Inventario;
import com.musa_jeans.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listarTodo(){
        return inventarioService.listarTodoEnriquecido();
    }

   @GetMapping("/{id}")
    public Inventario verTodo(@PathVariable Long id){

        return inventarioService.obtenerInventarioCompleto(id);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarStock(@PathVariable Long id, @RequestBody Inventario datosActualizados) {
        Inventario inventario = inventarioService.obtenerInventarioCompleto(id);

        if (inventario != null) {

            inventario.setStock(datosActualizados.getStock());
            Inventario stockActualizado = inventarioService.registrarInventario(inventario);
            return ResponseEntity.ok(stockActualizado);
        }

        return ResponseEntity.notFound().build();
    }

@PostMapping
public ResponseEntity<Inventario> guardar(@RequestBody Inventario inventario) {
    Inventario nuevoInventario = inventarioService.guardar(inventario);
    Inventario inventarioCompleto = inventarioService.obtenerInventarioCompleto(nuevoInventario.getId());
    return ResponseEntity.ok(inventarioCompleto);
}

}
