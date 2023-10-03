package com.proyect.ventas.service;

import com.proyect.ventas.dto.CarritoDTO;
import com.proyect.ventas.dto.ProductoDTO;
import com.proyect.ventas.dto.VentasDTO;
import com.proyect.ventas.model.Ventas;
import com.proyect.ventas.repository.ICartAPI;
import com.proyect.ventas.repository.IProdAPI;
import com.proyect.ventas.repository.IVentasRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentasService implements IVentasService{
    @Autowired
    private IVentasRepository ventRepo;

    @Autowired
    private ICartAPI cartAPI;

    @Autowired
    private IProdAPI prodAPI;

    @Override
    public void saveVenta(Ventas venta) {
        ventRepo.save(venta);
    }

    @Override
    @CircuitBreaker(name = "carrito-service", fallbackMethod = "fallbackVent")
    public VentasDTO getVenta(Long id_venta) {
        Ventas venta = ventRepo.findById(id_venta).orElse(null);
        //busco el id_carrito y, a partir de eso, encuentro que tiene asociada la venta a traves de una api.
        CarritoDTO carrito = cartAPI.findCartById(venta.getId_carrito());
        List<Long> codigos = carrito.getList_prods();
        List<ProductoDTO> prods = new ArrayList<>();

        //a ese conjunto de codigos de prods le hago un for each. dentro del for each hago uso de la api de prods para obtener la data.
        for(Long cod: codigos){
            ProductoDTO prod = callProdAPIWithCircuitBreaker(cod);
            prods.add(prod);
        }
        VentasDTO ventadto = new VentasDTO();
        ventadto.setFecha(venta.getFecha());
        ventadto.setTotal(carrito.getTotal());
        ventadto.setList_prods(prods);
        return ventadto;
    }

    @CircuitBreaker(name = "producto-service", fallbackMethod = "fallbackProd")
    private ProductoDTO callProdAPIWithCircuitBreaker(Long producto_id){
        ProductoDTO produc = prodAPI.findProdById(producto_id);
        return produc;
    }

    private CarritoDTO fallbackVent(Throwable throwable){
        return new CarritoDTO(9999L, null, 9999.9);
    }

    private ProductoDTO fallbackProd(Throwable throwable){
        return new ProductoDTO("Error", "Error", 99999L);
    }
}
