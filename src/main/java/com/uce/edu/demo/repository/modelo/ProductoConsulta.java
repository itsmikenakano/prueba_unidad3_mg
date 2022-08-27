package com.uce.edu.demo.repository.modelo;

public class ProductoConsulta {

	private String codigoBarra;

	private String nombre;

	private Integer stock;

	public ProductoConsulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoConsulta(String codigoBarra, String nombre, Integer stock) {
		super();
		this.codigoBarra = codigoBarra;
		this.nombre = nombre;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoConsulta [codigoBarra=" + codigoBarra + ", nombre=" + nombre + ", stock=" + stock + "]";
	}

	// SET Y GET
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
