package com.proyect.ventas.repository;

import com.proyect.ventas.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PRODUCTOS-SERVICE")
public interface IProdAPI {
    @GetMapping("/producto/findProdById/{cod}")
    public ProductoDTO findProdById(@PathVariable("cod")Long cod);
}
