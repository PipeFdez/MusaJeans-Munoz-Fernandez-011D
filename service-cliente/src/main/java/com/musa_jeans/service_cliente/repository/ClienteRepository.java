package com.musa_jeans.service_cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByRutIgnoreCase(String rut);

}
