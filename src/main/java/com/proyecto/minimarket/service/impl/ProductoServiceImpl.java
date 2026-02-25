package com.proyecto.minimarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.minimarket.entity.Producto;
import com.proyecto.minimarket.repository.ProductoRepository;
import com.proyecto.minimarket.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProductoById(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto buscarProductoById(Integer id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<Producto> listStockBajo(int stockMinimo) {
        return productoRepository.listStockBajo(stockMinimo);
    }

    @Override
    public List<Producto> listProductosAVencer(int diasLimite) {
        return productoRepository.listProductosAVencer(diasLimite);
    }
}