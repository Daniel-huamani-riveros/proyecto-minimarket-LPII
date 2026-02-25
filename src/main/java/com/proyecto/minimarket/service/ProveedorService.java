package com.proyecto.minimarket.service;

import java.util.List;

import com.proyecto.minimarket.entity.Proveedor;

public interface ProveedorService {

    public Proveedor guardarProveedor(Proveedor proveedor);

    public List<Proveedor> listarTodosProveedor();

    public Proveedor actualizarProveedor(Proveedor proveedor);

    public void eliminarProveedorById(Integer id);

    public Proveedor buscarProveedorById(Integer id);

    public List<Proveedor> buscarPorNombre(String nombre);
}