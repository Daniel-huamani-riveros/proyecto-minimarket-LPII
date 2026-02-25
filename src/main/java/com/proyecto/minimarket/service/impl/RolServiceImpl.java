package com.proyecto.minimarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.minimarket.entity.Rol;
import com.proyecto.minimarket.repository.RolRepository;
import com.proyecto.minimarket.service.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> listarTodosRol() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarById(Integer id) {
        return rolRepository.findById(id).get();
    }
}