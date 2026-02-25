package com.proyecto.minimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.minimarket.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
	Usuario validarLogin(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM usuario WHERE username = :username", nativeQuery = true)
    public Usuario buscarByUsername(@Param("username") String username);
}
