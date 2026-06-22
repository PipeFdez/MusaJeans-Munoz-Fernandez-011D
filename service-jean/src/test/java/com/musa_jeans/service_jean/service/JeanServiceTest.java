package com.musa_jeans.service_jean.service;

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

import com.musa_jeans.service_jean.model.Jean;
import com.musa_jeans.service_jean.model.Marca;
import com.musa_jeans.service_jean.model.Modelo;
import com.musa_jeans.service_jean.repository.JeanRepository;

@ExtendWith(MockitoExtension.class)
class JeanServiceTest {

    @Mock
    private JeanRepository jeanRepository;

    @InjectMocks
    private JeanService jeanService;

    @Test
    @DisplayName("Debería guardar un jean correctamente")
    void guardarJeanTest() {

        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("Levis");

        Modelo modelo = new Modelo();
        modelo.setId(1L);
        modelo.setNombre("Skinny");

        Jean jean = new Jean();
        jean.setPrecio(29990);
        jean.setColor("Azul");
        jean.setTalla("XL");
        jean.setTiro("Medio");
        jean.setDescripcion("Jean clásico");
        jean.setMarca(marca);
        jean.setModelo(modelo);

        when(jeanRepository.save(any(Jean.class))).thenAnswer(invocation -> {
            Jean j = invocation.getArgument(0);
            j.setId(1L);
            return j;
        });

        Jean resultado = jeanService.guardar(jean);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        assertEquals(29990, resultado.getPrecio());
        assertEquals("Azul", resultado.getColor());
        assertEquals("XL", resultado.getTalla());
        assertEquals("Medio", resultado.getTiro());
        assertEquals("Jean clásico", resultado.getDescripcion());

        assertEquals("Levis", resultado.getMarca().getNombre());
        assertEquals("Skinny", resultado.getModelo().getNombre());

        verify(jeanRepository, times(1)).save(jean);
    }

    @Test
    @DisplayName("Debería buscar un jean por ID")
    void buscarPorIdTest() {
        Long jeanId = 1L;

        Jean jean = new Jean();
        jean.setId(jeanId);

        when(jeanRepository.findById(jeanId)).thenReturn(Optional.of(jean));

        Optional<Jean> resultado = jeanService.buscarPorId(jeanId);

        assertNotNull(resultado);
        assertEquals(jeanId, resultado.get().getId());

        verify(jeanRepository, times(1)).findById(jeanId);
    }

    @Test
    @DisplayName("Debería eliminar un jean por ID")
    void eliminarTest() {

        Long jeanId = 1L;

        jeanService.eliminar(jeanId);
        verify(jeanRepository, times(1)).deleteById(jeanId);
    }

    @Test
    @DisplayName("Debería buscar jeans por nombre de marca")
    void buscarPorMarcaNombreTest() {

        String nombreMarca = "Levis";
        List<Jean> listaJeans = new ArrayList<>();
        Jean jean = new Jean();
        Marca marca = new Marca();

        marca.setNombre(nombreMarca);
        jean.setMarca(marca);
        listaJeans.add(jean);

        when(jeanRepository.findByMarcaNombreIgnoreCase(nombreMarca)).thenReturn(listaJeans);
        List<Jean> resultado = jeanService.buscarPorMarcaNombre(nombreMarca);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombreMarca, resultado.get(0).getMarca().getNombre());

        verify(jeanRepository, times(1))
                .findByMarcaNombreIgnoreCase(nombreMarca);
    }

}