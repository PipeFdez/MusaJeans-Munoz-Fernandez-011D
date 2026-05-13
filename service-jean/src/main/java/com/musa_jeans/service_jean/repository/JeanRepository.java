package com.musa_jeans.service_jean.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_jean.model.Jean;

public interface JeanRepository extends JpaRepository<Jean, Long> {

    List<Jean> findByTallaIgnoreCase(String talla);

    List<Jean> findByMarcaNombreIgnoreCase(String nombre);

}
