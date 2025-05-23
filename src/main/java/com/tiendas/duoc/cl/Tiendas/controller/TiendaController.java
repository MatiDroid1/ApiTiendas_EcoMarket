package com.tiendas.duoc.cl.Tiendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendas.duoc.cl.Tiendas.model.Tienda;
import com.tiendas.duoc.cl.Tiendas.service.TiendaService;

@RestController
@RequestMapping("api/v1/tiendas")
public class TiendaController {
    @Autowired
    private TiendaService ts;

    @GetMapping
    public ResponseEntity<List<Tienda>> listarTiendas() {
        List<Tienda> tiendas = ts.listarTiendas();
        if (tiendas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tiendas);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarXid(@PathVariable Long id) {
        Tienda tienda = ts.buscarTienda(id);
        if (tienda == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tiendas asociadas a ese id");
        } else {
            return ResponseEntity.ok(tienda);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearTienda(@RequestBody Tienda tienda) {
        try {
            ts.guardar(tienda);
            return ResponseEntity.status(201).body("Tienda ingresada correctamente");
        } catch (Exception ex) {
            return ResponseEntity.status(505).body("Problema para ingresar tienda " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        try {
            Tienda tiendaExistente = ts.buscarTienda(id);
            if (tiendaExistente == null) {
                return ResponseEntity.status(404).body("Tienda no encontrada");
            }
            tiendaExistente.setNombre(tienda.getNombre());
            tiendaExistente.setCiudad(tienda.getCiudad());
            tiendaExistente.setDireccion(tienda.getDireccion());
            tiendaExistente.setTelefono(tienda.getTelefono());
            tiendaExistente.setActiva(tienda.getActiva());

            ts.guardar(tiendaExistente);
            return ResponseEntity.ok("Tienda actualizada con exito");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error al modificar la tienda: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivarTienda(@PathVariable Long id) {
        try {
            Tienda tienda = ts.buscarTienda(id);
            if (tienda == null) {
                return ResponseEntity.status(404).body("Tienda no encontrada");
            }

            tienda.setActiva(0L); // 0 = desactivada
            ts.guardar(tienda);

            return ResponseEntity.ok("Tienda desactivada con éxito");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error al desactivar la tienda: " + ex.getMessage());
        }
    }

}
