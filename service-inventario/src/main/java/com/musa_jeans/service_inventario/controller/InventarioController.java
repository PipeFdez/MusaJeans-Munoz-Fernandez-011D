package com.musa_jeans.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_inventario.model.Inventario;
import com.musa_jeans.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listarTodo(){
        return inventarioService.listarPorNombre();
    }

   @GetMapping("/{id}")
    public Inventario verTodo(@PathVariable Long id){

        return inventarioService.obtenerInventarioCompleto(id);
        
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @RequestBody Inventario datosNuevos) {

        Inventario actualizado = inventarioService.actualizarInventario(id, datosNuevos);
        
        return ResponseEntity.ok(actualizado);
    }
}
