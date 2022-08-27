package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.ProductoSolicitado;

public interface IVentaService {
	
	public void realizarVenta(List<ProductoSolicitado> productos, String cedulaCliente, String numeroVenta);
	

}
