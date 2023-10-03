package com.proyect.ventas.service;

import com.proyect.ventas.dto.VentasDTO;
import com.proyect.ventas.model.Ventas;

import java.util.List;

public interface IVentasService {
    public void saveVenta(Ventas venta);
    public VentasDTO getVenta(Long id_venta);
}
