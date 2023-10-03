package com.proyect.ventas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ventas;
    private LocalDate fecha;
    private Long id_carrito;
}
