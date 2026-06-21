package com.musa_jeans.service_admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa_jeans.service_admin.model.Administrador;
import com.musa_jeans.service_admin.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador registrarAdmin(Administrador admin) {

        Administrador existente = administradorRepository.findByEmail(admin.getEmail());
        if (existente != null) {
            throw new RuntimeException("El correo ya se encuentra registrado.");
        }

        admin.setRol("ROLE_ADMIN");
        
        return administradorRepository.save(admin);
    }

    public Administrador buscarPorId(Long id) {
        return administradorRepository.findById(id).orElse(null); 
    }

    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }
    public void eliminarAdmin(Long id) {
        administradorRepository.deleteById(id);
    }
}