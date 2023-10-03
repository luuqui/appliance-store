package com.proyect.carrito.model;

import com.proyect.carrito.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cart;
    @ElementCollection
    private List<Long> list_prods = new ArrayList<Long>();
    private Double total;
}
