package com.proyect.productos.service;

import com.proyect.productos.model.Producto;
import com.proyect.productos.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public void saveProducto(Producto producto) {
        prodRepo.save(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return prodRepo.findAll();
    }

    @Override
    public Producto findByCod(Long cod) {
        return prodRepo.findById(cod).orElse(null);
    }

    @Override
    public void updateProducto(Producto prod) {
        prodRepo.save(prod);
    }

    @Override
    public void deleteProduct(Long cod) {
        prodRepo.deleteById(cod);
    }

    @Override
    public Producto getProdByCod(Long cod) {
        return prodRepo.getProdByCod(cod);
    }


}
