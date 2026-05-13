package com.musa_jeans.service_carrito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musa_jeans.service_carrito.model.ProductoCarrito;
import com.musa_jeans.service_carrito.service.ProductoCarritoService;

@RestController
@RequestMapping("/api/v1/producto-carrito")
public class ProductoCarritoController {

    @Autowired
    private ProductoCarritoService productoCarritoService;

    @GetMapping
    public List<ProductoCarrito> listarTodos() {
        return productoCarritoService.listarTodos();
    }

    @GetMapping("/carrito/{idCarrito}")
    public ResponseEntity<List<ProductoCarrito>> buscarPorCarrito(@PathVariable Long idCarrito) {
        List<ProductoCarrito> productos = productoCarritoService.buscarPorCarrito(idCarrito);

        if (productos == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<ProductoCarrito> guardar(@RequestBody ProductoCarrito productoCarrito) {
        return ResponseEntity.ok(productoCarritoService.guardar(productoCarrito));
    }

    @DeleteMapping("/{idProductoCarrito}")
    public ResponseEntity<String> eliminar(@PathVariable Long idProductoCarrito) {
        productoCarritoService.eliminar(idProductoCarrito);
        return ResponseEntity.ok("Producto eliminado del carrito");
    }

}