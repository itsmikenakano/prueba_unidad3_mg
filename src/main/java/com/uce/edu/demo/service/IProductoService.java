package com.uce.edu.demo.service;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoConsulta;

public interface IProductoService {

	public void ingresarProducto(Producto producto);
	
	public ProductoConsulta buscarPorCodigo(String codigo);

}
