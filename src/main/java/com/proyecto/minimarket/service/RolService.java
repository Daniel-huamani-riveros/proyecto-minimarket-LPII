package com.proyecto.minimarket.service;

import java.util.List;

import com.proyecto.minimarket.entity.Rol;

public interface RolService {

    public List<Rol> listarTodosRol();

    public Rol buscarById(Integer id);
}