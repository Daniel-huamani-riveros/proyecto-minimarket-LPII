package com.proyecto.minimarket.service;

import java.util.List;

import com.proyecto.minimarket.entity.Usuario;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario);

    public List<Usuario> listarTodosUsuario();

    public Usuario actualizarUsuario(Usuario usuario);

    public void eliminarUsuarioById(Integer id);

    public Usuario buscarUsuarioById(Integer id);

    public Usuario validarLogin(String username, String password);

    public Usuario buscarByUsername(String username);
}
