package com.musa_jeans.service_venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_venta.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByIdVenta(Long idVenta);

}