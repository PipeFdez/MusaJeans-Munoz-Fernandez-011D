package com.musa_jeans.service_carrito.controller;

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

import com.musa_jeans.service_carrito.model.Carrito;
import com.musa_jeans.service_carrito.service.CarritoService;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> listarTodos() {
        return carritoService.listarTodos();
    }

    @GetMapping("/cliente/{rutCliente}")
    public ResponseEntity<Carrito> buscarPorCliente(@PathVariable String rutCliente) {
        Carrito carrito = carritoService.buscarPorCliente(rutCliente);

        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<Carrito> guardar(@RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.guardar(carrito));
    }

    @DeleteMapping("/{idCarrito}")
    public ResponseEntity<String> eliminar(@PathVariable Long idCarrito) {

        carritoService.eliminar(idCarrito);

        return ResponseEntity.ok("Carrito eliminado");
    }

}