package com.proyecto.minimarket.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Integer idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precioCompra")
    private Double precioCompra;

    @Column(name = "precioVenta")
    private Double precioVenta;

    @Column(name = "stock")
    private Integer stock;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaVencimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVencimiento;

    public Producto() {}
}