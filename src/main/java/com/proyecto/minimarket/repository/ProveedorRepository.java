package com.proyecto.minimarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.minimarket.entity.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query(value = "SELECT * FROM proveedor WHERE nombre LIKE CONCAT('%', :nombre, '%')", nativeQuery = true)
    public List<Proveedor> buscarPorNombre(@Param("nombre") String nombre);
}