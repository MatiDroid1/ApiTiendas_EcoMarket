package com.tiendas.duoc.cl.Tiendas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TIENDAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="tienda_id")
    private Long id_tienda;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "activa", nullable = false)
    private Long activa;
}
