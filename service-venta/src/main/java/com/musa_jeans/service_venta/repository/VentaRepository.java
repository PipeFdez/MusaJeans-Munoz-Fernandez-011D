package com.musa_jeans.service_venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_venta.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}