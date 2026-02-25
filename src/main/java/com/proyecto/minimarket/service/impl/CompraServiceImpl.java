package com.proyecto.minimarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.minimarket.entity.Compra;
import com.proyecto.minimarket.entity.DetalleCompra;
import com.proyecto.minimarket.repository.CompraRepository;
import com.proyecto.minimarket.repository.DetalleCompraRepository;
import com.proyecto.minimarket.repository.ProductoRepository;
import com.proyecto.minimarket.service.CompraService;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    DetalleCompraRepository detalleCompraRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Compra guardarCompra(Compra compra) {
        // Guardar primero la compra sin detalles
        List<DetalleCompra> detalles = compra.getDetalles();
        compra.setDetalles(null);
        Compra compraGuardada = compraRepository.save(compra);

        // Ahora guardar cada detalle con la compra ya guardada
        if (detalles != null) {
            for (DetalleCompra detalle : detalles) {
                detalle.setCompra(compraGuardada);
                detalleCompraRepository.save(detalle);
                // Actualizar stock
                productoRepository.findById(detalle.getProducto().getIdProducto())
                    .ifPresent(p -> {
                        p.setStock(p.getStock() + detalle.getCantidad());
                        productoRepository.save(p);
                    });
            }
        }
        return compraGuardada;
    }

    @Override
    public List<Compra> listarTodasCompras() {
        return compraRepository.findAll();
    }

    @Override
    public List<Compra> buscarPorRangoFechas(String fechaInicio, String fechaFin) {
        return compraRepository.buscarPorRangoFechas(fechaInicio, fechaFin);
    }
}