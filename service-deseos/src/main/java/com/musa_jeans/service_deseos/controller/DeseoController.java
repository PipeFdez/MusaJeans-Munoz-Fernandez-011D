package com.musa_jeans.service_deseos.controller;

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

import com.musa_jeans.service_deseos.model.Deseo;
import com.musa_jeans.service_deseos.service.DeseoService;

@RestController
@RequestMapping("/api/v1/deseo")
public class DeseoController {

    @Autowired
    private DeseoService deseoService;

    @GetMapping("/cliente/{clienteId}")
        public List<Deseo> listarPorCliente(@PathVariable Long clienteId) {
        return deseoService.listarPorCliente(clienteId);
    }

    @PostMapping
    public ResponseEntity<Deseo> guardar(@RequestBody Deseo deseo) {
        Deseo nuevoDeseo = deseoService.agregarDeseo(deseo);
        return ResponseEntity.ok(nuevoDeseo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        deseoService.eliminarDeseo(id);
        return ResponseEntity.ok("Producto eliminado");
    }

    @DeleteMapping("/cliente/{clienteId}")
    public ResponseEntity<String> vaciarLista(@PathVariable Long clienteId) {
        deseoService.vaciarListaPorCliente(clienteId);
        return ResponseEntity.ok("Lista de deseos vaciada");
    }

}
