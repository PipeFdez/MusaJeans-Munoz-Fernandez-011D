package com.musa_jeans.service_pago.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musa_jeans.service_pago.model.Pago;
import com.musa_jeans.service_pago.repository.PagoRepository;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    @DisplayName("Debería listar todos los pagos")
    void listarTodosTest() {

        List<Pago> lista = new ArrayList<>();

        Pago p1 = new Pago();
        p1.setId(1L);
        p1.setMonto(10000);
        p1.setMetodoPago("Tarjeta");
        p1.setEstado("COMPLETADO");
        p1.setVentaId(10L);

        Pago p2 = new Pago();
        p2.setId(2L);
        p2.setMonto(20000);
        p2.setMetodoPago("Efectivo");
        p2.setEstado("PENDIENTE");
        p2.setVentaId(11L);

        lista.add(p1);
        lista.add(p2);

        when(pagoRepository.findAll()).thenReturn(lista);

        List<Pago> resultado = pagoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Tarjeta", resultado.get(0).getMetodoPago());

        verify(pagoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería buscar pago por ID")
    void buscarPorIdTest() {

        Long id = 1L;

        Pago pago = new Pago();
        pago.setId(id);
        pago.setMonto(15000);
        pago.setMetodoPago("Tarjeta");
        pago.setEstado("COMPLETADO");
        pago.setVentaId(10L);

        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));

        Optional<Pago> resultado = pagoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.get().getId());
        assertEquals(15000, resultado.get().getMonto());

        verify(pagoRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Debería guardar un pago correctamente")
    void guardarTest() {

        Pago pago = new Pago();
        pago.setMonto(12000);
        pago.setMetodoPago("Efectivo");
        pago.setEstado("PENDIENTE");
        pago.setVentaId(20L);

        Pago pagoGuardado = new Pago();
        pagoGuardado.setId(1L);
        pagoGuardado.setMonto(12000);
        pagoGuardado.setMetodoPago("Efectivo");
        pagoGuardado.setEstado("PENDIENTE");
        pagoGuardado.setVentaId(20L);

        when(pagoRepository.save(any(Pago.class))).thenReturn(pagoGuardado);

        Pago resultado = pagoService.guardar(pago);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(12000, resultado.getMonto());

        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    @DisplayName("Debería eliminar un pago por ID")
    void eliminarTest() {
        Long id = 1L;
        pagoService.eliminar(id);
        verify(pagoRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Debería buscar pagos por estado")
    void buscarPorEstadoTest() {

        String estado = "COMPLETADO";

        List<Pago> lista = new ArrayList<>();

        Pago pago = new Pago();
        pago.setId(1L);
        pago.setEstado(estado);

        lista.add(pago);

        when(pagoRepository.findByEstadoIgnoreCase(estado)).thenReturn(lista);

        List<Pago> resultado = pagoService.buscarPorEstado(estado);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(estado, resultado.get(0).getEstado());

        verify(pagoRepository, times(1)).findByEstadoIgnoreCase(estado);
    }
}