package com.musa_jeans.service_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_admin.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByEmail(String email);   
     
}
