package com.proyecto.minimarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.minimarket.entity.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

    @Query(value = "SELECT * FROM Compra WHERE fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY fecha ASC", nativeQuery = true)
    public List<Compra> buscarPorRangoFechas(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}