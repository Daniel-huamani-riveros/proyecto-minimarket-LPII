package com.proyecto.minimarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.minimarket.entity.Usuario;
import com.proyecto.minimarket.repository.UsuarioRepository;
import com.proyecto.minimarket.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarTodosUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuarioById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario buscarUsuarioById(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario validarLogin(String username, String password) {
        return usuarioRepository.validarLogin(username, password);
    }

    @Override
    public Usuario buscarByUsername(String username) {
        return usuarioRepository.buscarByUsername(username);
    }
}