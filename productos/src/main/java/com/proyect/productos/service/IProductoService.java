package com.proyect.productos.service;

import com.proyect.productos.model.Producto;

import java.util.List;

public interface IProductoService {
    //create
    public void saveProducto(Producto producto);
    //read
    public List<Producto> getProductos();
    public Producto findByCod(Long cod);
    //update
    public void updateProducto(Producto producto);
    //delete
    public void deleteProduct(Long cod);
    public Producto getProdByCod(Long cod);
}
