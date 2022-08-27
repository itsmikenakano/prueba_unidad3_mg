package com.uce.edu.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoConsulta;
import com.uce.edu.demo.repository.modelo.ProductoSolicitado;
import com.uce.edu.demo.repository.modelo.VentaReporte;
import com.uce.edu.demo.service.IDetalleVentaService;
import com.uce.edu.demo.service.IProductoService;
import com.uce.edu.demo.service.IVentaService;

@SpringBootApplication
public class PruebaUnidad3MgApplication implements CommandLineRunner {

	private static Logger LOG = Logger.getLogger(PruebaUnidad3MgApplication.class);

	@Autowired
	private IProductoService iProductoService;

	@Autowired
	private IVentaService iVentaService;

	@Autowired
	private IDetalleVentaService iDetalleVentaService;

	public static void main(String[] args) {
		SpringApplication.run(PruebaUnidad3MgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Producto p = new Producto();
		p.setNombre("Zapatos");
		p.setCodigoBarra("001");
		p.setCategoria("Vestimenta");
		p.setPrecio(new BigDecimal(20.00));
		p.setStock(5);

		this.iProductoService.ingresarProducto(p);

		p.setStock(15);

		this.iProductoService.ingresarProducto(p);

		ProductoSolicitado ps1 = new ProductoSolicitado();
		ps1.setCantidad(2);
		ps1.setCodigoBarras("001");

		List<ProductoSolicitado> productos = new ArrayList<ProductoSolicitado>();
		productos.add(ps1);

		this.iVentaService.realizarVenta(productos, "230029", "FACT-0001");

		ProductoConsulta pc = this.iProductoService.buscarPorCodigo("001");

		LOG.info("Producto Consulta Stock: " + pc);

		LocalDateTime fecha = LocalDateTime.parse("2022-08-26T18:43:30.963131");

		List<VentaReporte> reporteVentas = this.iDetalleVentaService.buscaPorFecha(fecha, "Vestimenta", 2);
		reporteVentas.forEach(v -> LOG.info("Venta: " + v));

	}

}
