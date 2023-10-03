package com.proyect.productos.controller;

import com.proyect.productos.model.Producto;
import com.proyect.productos.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producto")
public class ProductoController {
    @Autowired
    private IProductoService prodServ;

    @PostMapping("/save")
    public String saveProd(@RequestBody Producto producto){
        prodServ.saveProducto(producto);
        return "Alta!";
    }
    @GetMapping("/get")
    public List<Producto> getProductos(){
        return prodServ.getProductos();
    }

    @PutMapping("/put")
    public String updateProd(@RequestBody Producto producto){
        prodServ.updateProducto(producto);
        return "Update!";
    }
    @DeleteMapping("/delete/{cod}")
    public String deleteProd(@PathVariable("cod")Long cod){
        prodServ.deleteProduct(cod);
        return "Delete!";
    }

    @GetMapping("/findProdById/{cod}")
    public Producto findProdById(@PathVariable("cod")Long cod){
        return prodServ.getProdByCod(cod);
    }
}
