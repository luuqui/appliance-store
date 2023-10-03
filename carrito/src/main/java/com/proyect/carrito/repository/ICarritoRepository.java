package com.proyect.carrito.repository;

import com.proyect.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    @Query("SELECT c FROM Carrito c WHERE c.id_cart = :id_cart")
    Carrito getCartById(Long id_cart);
}
