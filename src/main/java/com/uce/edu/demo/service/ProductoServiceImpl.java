package com.uce.edu.demo.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoConsulta;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository iProductoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void ingresarProducto(Producto producto) {
		Producto p = new Producto();
		boolean vacio = false;

		try {
			p = this.iProductoRepository.buscarPorCodigoBarras(producto.getCodigoBarra());
		} catch (Exception e) {
			vacio = true;
		}

		if (vacio) {
			this.iProductoRepository.insertar(producto);
		} else {
			p.setStock(p.getStock() + producto.getStock());
			this.iProductoRepository.actualizar(p);
		}

	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public ProductoConsulta buscarPorCodigo(String codigo) {
		Producto p = this.iProductoRepository.buscarPorCodigo(codigo);

		ProductoConsulta ps = new ProductoConsulta();
		ps.setCodigoBarra(p.getCodigoBarra());
		ps.setNombre(p.getNombre());
		ps.setStock(p.getStock());

		return ps;
	}

}
