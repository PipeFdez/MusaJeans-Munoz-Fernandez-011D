package com.musa_jeans.service_admin.controller;

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

import com.musa_jeans.service_admin.model.Administrador;
import com.musa_jeans.service_admin.service.AdministradorService;

@RestController
@RequestMapping("api/v1/administrador")
public class AdministradorController {

    @Autowired
    public AdministradorService administradorService;

    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable Long id) {
        Administrador admin = administradorService.buscarPorId(id);

        if (admin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }

    @PostMapping
    public ResponseEntity<Administrador> registrarAdmin(@RequestBody Administrador admin) {
        return ResponseEntity.ok(administradorService.registrarAdmin(admin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdmin(@PathVariable Long id, @RequestBody Administrador datosActualizados) {
        Administrador admin = administradorService.buscarPorId(id);

        if (admin != null) {
            admin.setNombre(datosActualizados.getNombre());
            admin.setEmail(datosActualizados.getEmail());
            admin.setPassword(datosActualizados.getPassword());
            // Nota: El rol no lo cambiamos porque se asigna automáticamente en el service

            Administrador adminActualizado = administradorService.registrarAdmin(admin);
            return ResponseEntity.ok(adminActualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAdmin(@PathVariable Long id) {
        Administrador admin = administradorService.buscarPorId(id);

        if (admin != null) {
            administradorService.eliminarAdmin(admin.getId());
            return ResponseEntity.ok("Administrador eliminado correctamente");
        }

        return ResponseEntity.notFound().build();
    }
}