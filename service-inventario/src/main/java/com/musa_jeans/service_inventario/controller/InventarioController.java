package com.musa_jeans.service_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musa_jeans.service_inventario.model.Inventario;
import com.musa_jeans.service_inventario.service.InventarioService;

@RestController
@RequestMapping("api/v1/inventario")
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

    @PostMapping
    public Inventario guardarNuevoInventario(@RequestBody Inventario inventario){
        return inventarioService.guardarInventario(inventario);
    }
}
