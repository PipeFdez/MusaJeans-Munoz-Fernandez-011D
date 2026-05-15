package com.musa_jeans.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.PutMapping;
>>>>>>> service-inventario
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_inventario.model.Inventario;
import com.musa_jeans.service_inventario.service.InventarioService;

@RestController
<<<<<<< HEAD
@RequestMapping("api/v1/inventario")
=======
@RequestMapping("/api/v1/inventario")
>>>>>>> service-inventario
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

<<<<<<< HEAD
    @PostMapping
    public Inventario guardarNuevoInventario(@RequestBody Inventario inventario){
        return inventarioService.guardarInventario(inventario);
    }
=======
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

@DeleteMapping("/{idInventario}")
public ResponseEntity<String> eliminar(@PathVariable Long idInventario){

    inventarioService.eliminar(idInventario);

    return ResponseEntity.ok("inventario borrado");

}

>>>>>>> service-inventario
}
