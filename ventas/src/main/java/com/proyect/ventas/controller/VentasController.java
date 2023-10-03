package com.proyect.ventas.controller;

import com.proyect.ventas.dto.VentasDTO;
import com.proyect.ventas.model.Ventas;
import com.proyect.ventas.service.IVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ventas")
public class VentasController {
    @Autowired
    private IVentasService ventSer;

    @PostMapping("/save")
    public String saveVenta(@RequestBody Ventas venta){
        ventSer.saveVenta(venta);
        return "Alta";
    }

    @GetMapping("/get/{id_venta}")
    public VentasDTO getVenta(@PathVariable("id_venta")Long id_venta){
        return ventSer.getVenta(id_venta);
    }
}
