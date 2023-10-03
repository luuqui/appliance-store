package com.proyect.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VentasDTO {
    private LocalDate fecha;
    private Double total;
    private List<ProductoDTO> list_prods;
}
