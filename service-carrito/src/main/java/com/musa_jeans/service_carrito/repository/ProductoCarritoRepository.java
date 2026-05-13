package com.musa_jeans.service_carrito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_carrito.model.ProductoCarrito;

public interface ProductoCarritoRepository extends JpaRepository<ProductoCarrito, Long> {

    List<ProductoCarrito> findByIdCarrito(Long idCarrito);

}