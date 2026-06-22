package com.musa_jeans.service_cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musa_jeans.service_cliente.model.Cliente;
import com.musa_jeans.service_cliente.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("Debería listar todos los clientes")
    void listarTodosTest() {

        List<Cliente> lista = new ArrayList<>();

        Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.setRut("11111111-1");
        c1.setNombre("Juan");
        c1.setCorreo("juan@gmail.com");
        c1.setDireccion("Santiago");

        Cliente c2 = new Cliente();
        c2.setId(2L);
        c2.setRut("22222222-2");
        c2.setNombre("Pedro");
        c2.setCorreo("pedro@gmail.com");
        c2.setDireccion("Valparaíso");

        lista.add(c1);
        lista.add(c2);

        when(clienteRepository.findAll()).thenReturn(lista);

        List<Cliente> resultado = clienteService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería buscar cliente por RUT")
    void buscarPorRutTest() {

        String rut = "11111111-1";

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setRut(rut);
        cliente.setNombre("Juan");
        cliente.setCorreo("juan@gmail.com");
        cliente.setDireccion("Santiago");

        when(clienteRepository.findByRutIgnoreCase(rut)).thenReturn(cliente);

        Cliente resultado = clienteService.buscarPorRut(rut);

        assertNotNull(resultado);
        assertEquals(rut, resultado.getRut());
        assertEquals("Juan", resultado.getNombre());

        verify(clienteRepository, times(1)).findByRutIgnoreCase(rut);
    }

    @Test
    @DisplayName("Debería registrar un cliente correctamente")
    void registrarClienteTest() {

        Cliente cliente = new Cliente();
        cliente.setRut("20051806-3");
        cliente.setNombre("Felipe");
        cliente.setCorreo("felipe@gmail.com");
        cliente.setDireccion("Maipú");

        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setId(1L);
        clienteGuardado.setRut("20051806-3");
        clienteGuardado.setNombre("Felipe");
        clienteGuardado.setCorreo("felipe@gmail.com");
        clienteGuardado.setDireccion("Maipú");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardado);

        Cliente resultado = clienteService.registrarCliente(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Felipe", resultado.getNombre());
        assertEquals("20051806-3", resultado.getRut());

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    @DisplayName("Debería eliminar un cliente por ID")
    void eliminarClienteTest() {

        Long id = 1L;
        doNothing().when(clienteRepository).deleteById(id);
        String resultado = clienteService.eliminarCliente(id);
        assertEquals("Cliente eliminado", resultado);
        verify(clienteRepository, times(1)).deleteById(id);
    }
}