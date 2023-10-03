package com.proyect.ventas.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CarritoDTO {
    private Long id_cart;
    private List<Long> list_prods;
    private Double total;
}
