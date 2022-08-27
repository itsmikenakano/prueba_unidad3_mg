package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSolicitado;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepository iVentaRepository;

	@Autowired
	private IProductoRepository iProductoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarVenta(List<ProductoSolicitado> productos, String cedulaCliente, String numeroVenta) {

		Venta v = new Venta();
		v.setCedulaCliente(cedulaCliente);
		v.setNumero(numeroVenta);
		v.setFecha(LocalDateTime.now());

		BigDecimal montoTotal = BigDecimal.ZERO;
		List<DetalleVenta> detalles = new ArrayList<DetalleVenta>();

		for (ProductoSolicitado productoSolicitado : productos) {
			Producto p = this.iProductoRepository.buscarPorCodigoBarras(productoSolicitado.getCodigoBarras());
			if (p.getStock() == 0) {
				throw new RuntimeException();
			}

			if (p.getStock() <= productoSolicitado.getCantidad()) {
				productoSolicitado.setCantidad(p.getStock());
			}
			DetalleVenta detalle = new DetalleVenta();
			detalle.setProducto(p);
			detalle.setPrecioUnitario(p.getPrecio());
			detalle.setSubtotal(p.getPrecio().multiply(new BigDecimal(productoSolicitado.getCantidad())));
			detalle.setVenta(v);
			detalle.setCantidad(productoSolicitado.getCantidad());
			detalles.add(detalle);

			montoTotal = montoTotal.add(detalle.getSubtotal());
			p.setStock(p.getStock() - productoSolicitado.getCantidad());
			this.iProductoRepository.actualizar(p);
		}

		v.setDetalles(detalles);
		v.setTotalVenta(montoTotal);

		this.iVentaRepository.insertar(v);
	}

}
