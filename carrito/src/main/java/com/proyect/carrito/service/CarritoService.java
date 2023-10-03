package com.proyect.carrito.service;

import com.proyect.carrito.dto.ProductoDTO;
import com.proyect.carrito.model.Carrito;
import com.proyect.carrito.repository.ICarritoRepository;
import com.proyect.carrito.repository.IProdAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService implements ICarritoService {

    private static final String nameCircuitBreaker = "productos-service";
    private static final String nameFallback = "fallbackLogic";

    @Autowired
    private ICarritoRepository cartRepo;

    @Autowired
    private IProdAPI prodAPI;

    //Agrega un carrito en el caso que no exista. Si existe, solo agrega el prod en el carrito ya existente
    @CircuitBreaker(name = nameCircuitBreaker, fallbackMethod = nameFallback)
    @Retry(name = "productos-service")
    public void addProd(Long id_cart, Long cod) {
        if (id_cart == null || id_cart <= 0 || cod == null || cod <= 0) {
            throw new IllegalArgumentException("ID de carrito o código de producto deben ser valores válidos.");
        }
        Carrito carritoAct = cartRepo.findById(id_cart).orElse(null);
        ProductoDTO prod = prodAPI.findProdById(cod);
        if (carritoAct == null) {
            carritoAct = new Carrito();
            carritoAct.setId_cart(id_cart);
            carritoAct.setList_prods(new ArrayList<>());
            carritoAct.setTotal(0.0);
        }

        List<Long> listProds = carritoAct.getList_prods();
        listProds.add(prod.getCod());
        carritoAct.setList_prods(listProds);
        carritoAct.setTotal(calculateTotal(carritoAct));
        cartRepo.save(carritoAct);
    }

    //Elimina un producto de un carrito. Además, actualiza el precio en la base de datos.
    @Override
    @CircuitBreaker(name = nameCircuitBreaker, fallbackMethod = nameFallback)
    @Retry(name = "productos-service")
    public void deleteProd(Long id_cart, Long cod) {
        if (id_cart == null || id_cart <= 0 || cod == null || cod <= 0) {
            throw new IllegalArgumentException("ID de carrito o código de producto deben ser valores válidos.");
        }
        Carrito carrito = cartRepo.findById(id_cart).orElse(null);
        ProductoDTO prod = prodAPI.findProdById(cod);
        List<Long> listCods = carrito.getList_prods();
        listCods.remove(cod);
        carrito.setList_prods(listCods);
        carrito.setTotal(calculateTotal(carrito));
        cartRepo.save(carrito);
    }

    //Retorna la lista de productos que tiene el carrito(La información del producto completa).
    @Override
    @CircuitBreaker(name = nameCircuitBreaker, fallbackMethod = nameFallback)
    @Retry(name = "productos-service")
    public List<ProductoDTO> getProdsCarrito(Long id_cart) {
        if (id_cart == null || id_cart <= 0) {
            throw new IllegalArgumentException("ID de carrito o código de producto deben ser valores válidos.");
        }
        Carrito carrito = cartRepo.findById(id_cart).orElse(null);
        List<ProductoDTO> prodsCompletos = new ArrayList<ProductoDTO>();

        if (carrito != null) {
            List<Long> codProds = carrito.getList_prods();

            for (Long codigo : codProds) {
                ProductoDTO prod = prodAPI.findProdById(codigo);
                prodsCompletos.add(prod);
            }
        }
        return prodsCompletos;
    }

    @Override
    public Carrito getCartById(Long id_cart) {
        return cartRepo.getCartById(id_cart);
    }

    //En el caso que haya un error de conexion entre microservios productos-carrito:
    public ProductoDTO fallbackLogic(Throwable throwable) {
        return new ProductoDTO(0L, "Fallido", "Fallido", 999999.0);
    }


    //funcion que calcula el total del carrito
    public Double calculateTotal(Carrito carrito){
        List<Long> listCods = carrito.getList_prods();
        Double total = 0.0;
        for(Long cod: listCods){
            ProductoDTO prod = prodAPI.findProdById(cod);
            total += prod.getPrecio();
        }

        return total;
    }



}

