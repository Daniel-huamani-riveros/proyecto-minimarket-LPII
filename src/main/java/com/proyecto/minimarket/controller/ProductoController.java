package com.proyecto.minimarket.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.minimarket.entity.Producto;
import com.proyecto.minimarket.service.ProductoService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/producto")
    public String listProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodosProductos());
        return "producto/index";
    }

    @GetMapping("/producto/new")
    public String createProductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/create";
    }

    @PostMapping("/producto")
    public String saveProducto(@ModelAttribute("producto") Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/producto";
    }

    @GetMapping("/producto/edit/{id}")
    public String editProductoForm(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.buscarProductoById(id));
        return "producto/edit";
    }

    @PostMapping("/producto/{id}")
    public String updateProducto(@PathVariable Integer id,
                                  @ModelAttribute("producto") Producto producto) {
        Producto existente = productoService.buscarProductoById(id);
        existente.setNombre(producto.getNombre());
        existente.setPrecioCompra(producto.getPrecioCompra());
        existente.setPrecioVenta(producto.getPrecioVenta());
        existente.setStock(producto.getStock());
        existente.setFechaVencimiento(producto.getFechaVencimiento());
        productoService.actualizarProducto(existente);
        return "redirect:/producto";
    }

    @GetMapping("/producto/{id}")
    public String deleteProducto(@PathVariable Integer id) {
        productoService.eliminarProductoById(id);
        return "redirect:/producto";
    }

    @GetMapping("/producto/buscar")
    public String buscarProductoVista(
            @RequestParam(value = "nombre", defaultValue = "") String nombre,
            Model model) {
        model.addAttribute("productos", productoService.buscarPorNombre(nombre));
        model.addAttribute("busqueda", nombre);
        return "producto/index";
    }

    @GetMapping("/producto/buscarAjax")
    @ResponseBody
    public List<Producto> buscarProductoAjax(
            @RequestParam(value = "q", defaultValue = "") String q) {
        return productoService.buscarPorNombre(q);
    }

    @GetMapping("/producto/reporte/stock")
    public String reporteStockBajo(Model model) {
        model.addAttribute("productos", productoService.listStockBajo(10));
        model.addAttribute("stockMinimo", 10);
        return "producto/reporte_stock";
    }

    @GetMapping("/producto/reporte/stock/pdf")
    public ResponseEntity<byte[]> reporteStockPdf() {
        try {
            List<Producto> productos = productoService.listStockBajo(10);

            InputStream stream = getClass().getResourceAsStream("/reports/reporte_stock.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(stream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);
            Map<String, Object> params = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("reporte_stock.pdf", "reporte_stock.pdf");

            return ResponseEntity.ok().headers(headers).body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/producto/reporte/vencimiento")
    public String reporteVencimiento(Model model) {
        model.addAttribute("productos", productoService.listProductosAVencer(30));
        model.addAttribute("diasLimite", 30);
        return "producto/reporte_vencimiento";
    }
}