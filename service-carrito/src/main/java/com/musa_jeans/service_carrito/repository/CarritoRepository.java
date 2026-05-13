package com.musa_jeans.service_carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_carrito.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Carrito findByRutCliente(String rutCliente);

}