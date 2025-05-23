package com.tiendas.duoc.cl.Tiendas.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendas.duoc.cl.Tiendas.model.Tienda;
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    
}
