package com.proyect.productos.repository;

import com.proyect.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.cod = :cod")
    Producto getProdByCod(Long cod);
}
