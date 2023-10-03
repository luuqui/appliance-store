package com.proyect.ventas.repository;

import com.proyect.ventas.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name ="CARRITO-SERVICE")
public interface ICartAPI {
    @GetMapping("/carrito/getCartById/{id_cart}")
    public CarritoDTO findCartById(@PathVariable("id_cart")Long id_cart);
}