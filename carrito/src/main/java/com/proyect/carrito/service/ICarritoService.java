package com.proyect.carrito.service;

import com.proyect.carrito.dto.ProductoDTO;
import com.proyect.carrito.model.Carrito;

import java.util.List;

public interface ICarritoService {
    public void addProd(Long id_cart, Long cod);
    public void deleteProd(Long id_cart, Long cod);
    public List<ProductoDTO> getProdsCarrito(Long id_cart);

    public Carrito getCartById(Long id_cart);
}
