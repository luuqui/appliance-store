package com.proyect.carrito.controller;

import com.proyect.carrito.dto.ProductoDTO;
import com.proyect.carrito.model.Carrito;
import com.proyect.carrito.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carrito")
public class CarritoController {
    @Autowired
    private ICarritoService carServ;
    @PostMapping("/save/{id_cart}/{cod}")
    public ResponseEntity<String> addProd(@PathVariable("id_cart")Long id_cart, @PathVariable("cod")Long cod){
        carServ.addProd(id_cart, cod);
        return ResponseEntity.ok("Producto agregado con exito!");
    }

    @DeleteMapping("/delete/{id_cart}/{cod}")
    public ResponseEntity<String> deleteProd(@PathVariable("id_cart")Long id_cart, @PathVariable("cod")Long cod){
        carServ.deleteProd(id_cart, cod);
        return ResponseEntity.ok("Producto eliminado con exito!");
    }

    @GetMapping("/get/{id_cart}")
    public List<ProductoDTO> getProds(@PathVariable("id_cart")Long id_cart){
        return carServ.getProdsCarrito(id_cart);
    }

    @GetMapping("/getCartById/{id_cart}")
    public Carrito getCart(@PathVariable("id_cart")Long id_cart){
        return carServ.getCartById(id_cart);
    }
}
