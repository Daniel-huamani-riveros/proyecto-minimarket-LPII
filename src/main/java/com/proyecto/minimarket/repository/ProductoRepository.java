package com.proyecto.minimarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.minimarket.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	@Query(value = "SELECT * FROM producto WHERE nombre LIKE CONCAT('%', :nombre, '%')", nativeQuery = true)
	public List<Producto> buscarPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM producto WHERE stock <= :stockMinimo ORDER BY stock ASC", nativeQuery = true)
    public List<Producto> listStockBajo(@Param("stockMinimo") int stockMinimo);

    @Query(value = "SELECT * FROM producto WHERE fechaVencimiento <= DATE_ADD(CURDATE(), INTERVAL :dias DAY) ORDER BY fechaVencimiento ASC", nativeQuery = true)
    public List<Producto> listProductosAVencer(@Param("dias") int dias);
}