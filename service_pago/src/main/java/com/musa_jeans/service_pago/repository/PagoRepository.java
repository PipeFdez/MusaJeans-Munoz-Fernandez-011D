package com.musa_jeans.service_pago.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.musa_jeans.service_pago.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByEstadoIgnoreCase(String estado);
    
}