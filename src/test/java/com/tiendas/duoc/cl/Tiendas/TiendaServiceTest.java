package com.tiendas.duoc.cl.Tiendas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tiendas.duoc.cl.Tiendas.model.Tienda;
import com.tiendas.duoc.cl.Tiendas.repository.TiendaRepository;
import com.tiendas.duoc.cl.Tiendas.service.TiendaService;

@ExtendWith(MockitoExtension.class)
class TiendaServiceTest {

    @Mock
    private TiendaRepository tr;

    @InjectMocks
    private TiendaService tiendaService;

    @Test
    void testListarTiendas() {
        Tienda t1 = new Tienda(1L, "Tienda Centro", "Alameda 123", "Santiago", "912345678", 1L);
        Tienda t2 = new Tienda(2L, "Tienda Norte", "Independencia 456", "Santiago", "987654321", 1L);

        when(tr.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Tienda> resultado = tiendaService.listarTiendas();

        assertEquals(2, resultado.size());
        assertEquals("Tienda Centro", resultado.get(0).getNombre());
    }

    @Test
    void testGuardar() {
        Tienda tienda = new Tienda(null, "Nueva Tienda", "Los Leones 789", "Providencia", "999999999", 1L);

        when(tr.save(tienda)).thenReturn(tienda);

        Tienda resultado = tiendaService.guardar(tienda);

        assertNotNull(resultado);
        assertEquals("Nueva Tienda", resultado.getNombre());
        assertEquals("Providencia", resultado.getCiudad());
    }

    @Test
    void testBuscarTienda() {
        Tienda tienda = new Tienda(1L, "Tienda Sur", "Gran Avenida 100", "La Cisterna", "888888888", 1L);

        when(tr.findById(1L)).thenReturn(Optional.of(tienda));

        Tienda resultado = tiendaService.buscarTienda(1L);

        assertNotNull(resultado);
        assertEquals("Tienda Sur", resultado.getNombre());
        assertEquals("La Cisterna", resultado.getCiudad());
    }

    @Test
    void testVerificarTiendaNoRegistrada() {
        when(tr.findById(99L)).thenReturn(Optional.empty());

        Tienda resultado = tiendaService.buscarTienda(99L);

        assertNull(resultado);
    }
}