package com.proyecto.minimarket.service;

import java.util.List;

import com.proyecto.minimarket.entity.Compra;

public interface CompraService {

    public Compra guardarCompra(Compra compra);

    public List<Compra> listarTodasCompras();

    public List<Compra> buscarPorRangoFechas(String fechaInicio, String fechaFin);
}