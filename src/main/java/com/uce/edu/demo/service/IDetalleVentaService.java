package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.VentaReporte;

public interface IDetalleVentaService {
	
	public List<VentaReporte> buscaPorFecha(LocalDateTime fechaVenta, String categoria, Integer cantidad);
}
