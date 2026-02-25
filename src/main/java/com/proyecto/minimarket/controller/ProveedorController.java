package com.proyecto.minimarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.minimarket.entity.Proveedor;
import com.proyecto.minimarket.service.ProveedorService;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/proveedor")
    public String listProveedores(Model model) {
        model.addAttribute("proveedores", proveedorService.listarTodosProveedor());
        return "proveedor/index";
    }

    @GetMapping("/proveedor/new")
    public String createProveedorForm(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedor/create";
    }

    @PostMapping("/proveedor")
    public String saveProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/proveedor";
    }

    @GetMapping("/proveedor/edit/{id}")
    public String editProveedorForm(@PathVariable Integer id, Model model) {
        model.addAttribute("proveedor", proveedorService.buscarProveedorById(id));
        return "proveedor/edit";
    }

    @PostMapping("/proveedor/{id}")
    public String updateProveedor(@PathVariable Integer id,
                                   @ModelAttribute("proveedor") Proveedor proveedor) {
        Proveedor existente = proveedorService.buscarProveedorById(id);
        existente.setNombre(proveedor.getNombre());
        existente.setTelefono(proveedor.getTelefono());
        existente.setDireccion(proveedor.getDireccion());
        proveedorService.actualizarProveedor(existente);
        return "redirect:/proveedor";
    }

    @GetMapping("/proveedor/{id}")
    public String deleteProveedor(@PathVariable Integer id) {
        proveedorService.eliminarProveedorById(id);
        return "redirect:/proveedor";
    }

    @GetMapping("/proveedor/buscarAjax")
    @ResponseBody
    public List<Proveedor> buscarProveedorAjax(
            @RequestParam(value = "q", defaultValue = "") String q) {
        return proveedorService.buscarPorNombre(q);
    }
}