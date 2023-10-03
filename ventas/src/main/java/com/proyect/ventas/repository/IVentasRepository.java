package com.proyect.ventas.repository;

import com.proyect.ventas.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentasRepository extends JpaRepository<Ventas, Long> {
}
