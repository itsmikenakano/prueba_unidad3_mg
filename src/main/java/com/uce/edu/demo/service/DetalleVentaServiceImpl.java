package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IDetalleVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.VentaReporte;

@Service
public class DetalleVentaServiceImpl implements IDetalleVentaService {

	@Autowired
	private IDetalleVentaRepository iDetalleVentaRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public List<VentaReporte> buscaPorFecha(LocalDateTime fechaVenta, String categoria, Integer cantidad) {
		List<DetalleVenta> detalles = this.iDetalleVentaRepository.buscaPorFechaCantidad(fechaVenta, cantidad);
		List<VentaReporte> reporteVentas = new ArrayList<>();
		for (DetalleVenta d : detalles) {
			if(d.getProducto().getCategoria().equals(categoria)) {
				VentaReporte venta = new VentaReporte();
				venta.setCodigoBarras(d.getProducto().getCodigoBarra());
				venta.setNombre(d.getProducto().getNombre());
				venta.setCantidad(d.getCantidad());
				venta.setPrecioUnitario(d.getPrecioUnitario());
				venta.setSubtotal(d.getSubtotal());
				reporteVentas.add(venta);
			}
		}

		return reporteVentas;
	}

}
