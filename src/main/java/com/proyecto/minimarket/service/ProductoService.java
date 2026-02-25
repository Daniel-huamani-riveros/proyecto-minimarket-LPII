package com.proyecto.minimarket.service;

import java.util.List;

import com.proyecto.minimarket.entity.Producto;

public interface ProductoService {

    public Producto guardarProducto(Producto producto);

    public List<Producto> listarTodosProductos();

    public Producto actualizarProducto(Producto producto);

    public void eliminarProductoById(Integer id);

    public Producto buscarProductoById(Integer id);

    public List<Producto> buscarPorNombre(String nombre);

    public List<Producto> listStockBajo(int stockMinimo);

    public List<Producto> listProductosAVencer(int diasLimite);
}
