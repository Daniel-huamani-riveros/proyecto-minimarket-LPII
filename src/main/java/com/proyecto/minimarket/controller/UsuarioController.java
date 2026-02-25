package com.proyecto.minimarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.minimarket.entity.Usuario;
import com.proyecto.minimarket.service.RolService;
import com.proyecto.minimarket.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/usuario")
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
        model.addAttribute("rolList", rolService.listarTodosRol());
        return "usuario/index";
    }

    @GetMapping("/usuario/new")
    public String createUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rolList", rolService.listarTodosRol());
        return "usuario/create";
    }

    @PostMapping("/usuario")
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/usuario/edit/{id}")
    public String editUsuarioForm(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarUsuarioById(id));
        model.addAttribute("rolList", rolService.listarTodosRol());
        return "usuario/edit";
    }

    @PostMapping("/usuario/{id}")
    public String updateUsuario(@PathVariable Integer id,
                                 @ModelAttribute("usuario") Usuario usuario) {
        Usuario existente = usuarioService.buscarUsuarioById(id);
        existente.setUsername(usuario.getUsername());
        existente.setPassword(usuario.getPassword());
        existente.setNombreCompleto(usuario.getNombreCompleto());
        existente.setRol(usuario.getRol());
        usuarioService.actualizarUsuario(existente);
        return "redirect:/usuario";
    }

    @GetMapping("/usuario/{id}")
    public String deleteUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuarioById(id);
        return "redirect:/usuario";
    }
}