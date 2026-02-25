package com.proyecto.minimarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.minimarket.entity.Compra;
import com.proyecto.minimarket.service.CompraService;
import com.proyecto.minimarket.service.ProductoService;
import com.proyecto.minimarket.service.ProveedorService;

@Controller
public class CompraController {

    @Autowired
    private CompraService compraService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/compra")
    public String cargarFormulario(Model model) {
        model.addAttribute("compra", new Compra());
        model.addAttribute("productos", productoService.listarTodosProductos());
        model.addAttribute("proveedores", proveedorService.listarTodosProveedor());
        return "compra/create";
    }

    @PostMapping("/compra")
    public String saveCompra(@ModelAttribute("compra") Compra compra) {
        compraService.guardarCompra(compra);
        return "redirect:/compra/historial";
    }

    @GetMapping("/compra/historial")
    public String historial(Model model) {
        model.addAttribute("compras", compraService.listarTodasCompras());
        return "compra/historial";
    }

    @GetMapping("/compra/buscar")
    public String buscarPorFechas(@RequestParam("fechaInicio") String fechaInicio,
                                   @RequestParam("fechaFin") String fechaFin,
                                   Model model) {
        if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            model.addAttribute("MENSAJE", "⚠️ Debe ingresar ambas fechas.");
            model.addAttribute("TIPO", "warning");
            model.addAttribute("compras", compraService.listarTodasCompras());
        } else {
            List<Compra> compras = compraService.buscarPorRangoFechas(fechaInicio, fechaFin);
            model.addAttribute("compras", compras);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
        }
        return "compra/historial";
    }
}