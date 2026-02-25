package com.proyecto.minimarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.minimarket.entity.Proveedor;
import com.proyecto.minimarket.repository.ProveedorRepository;
import com.proyecto.minimarket.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public List<Proveedor> listarTodosProveedor() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminarProveedorById(Integer id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public Proveedor buscarProveedorById(Integer id) {
        return proveedorRepository.findById(id).get();
    }

    @Override
    public List<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.buscarPorNombre(nombre);
    }
}