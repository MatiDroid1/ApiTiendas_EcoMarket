package com.tiendas.duoc.cl.Tiendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendas.duoc.cl.Tiendas.model.Tienda;
import com.tiendas.duoc.cl.Tiendas.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiendaService {
    @Autowired
    private TiendaRepository tr;

    public List<Tienda> listarTiendas(){
        return tr.findAll();
    }

    public Tienda guardar(Tienda tienda){
        return tr.save(tienda);
    }

    public Tienda buscarTienda(Long id){
        return tr.findById(id).orElse(null);
    }
}
