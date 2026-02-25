package com.proyecto.minimarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.minimarket.entity.Usuario;
import com.proyecto.minimarket.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@ModelAttribute("usuario") Usuario usuario,
                                 HttpSession session, Model model) {

        Usuario usuarioEncontrado = usuarioService.validarLogin(
                usuario.getUsername(), usuario.getPassword());

        if (usuarioEncontrado != null) {
            session.setAttribute("idUsuario", usuarioEncontrado.getIdUsuario());
            session.setAttribute("username", usuarioEncontrado.getUsername());
            session.setAttribute("nombreCompleto", usuarioEncontrado.getNombreCompleto());
            session.setAttribute("rol", usuarioEncontrado.getRol().getDescripcion());
            return "redirect:/home";
        } else {
            model.addAttribute("MENSAJE", "❌ Usuario y/o contraseña incorrectos.");
            model.addAttribute("TIPO", "danger");
            model.addAttribute("usuario", new Usuario());
            return "login";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}