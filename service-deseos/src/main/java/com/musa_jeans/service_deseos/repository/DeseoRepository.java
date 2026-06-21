package com.musa_jeans.service_deseos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musa_jeans.service_deseos.model.Deseo;

public interface DeseoRepository extends JpaRepository<Deseo, Long> {

    List<Deseo> findByClienteId(Long clienteId);//con la lista, me puede traer multiples elementos dado que en una wishlist puede haber varios jeans

    void deleteByClienteId(Long clienteId);// lo usare para eliminar toda la wishlist de un cliente

}
