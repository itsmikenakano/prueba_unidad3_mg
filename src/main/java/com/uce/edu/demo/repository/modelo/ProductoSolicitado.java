package com.uce.edu.demo.repository.modelo;

public class ProductoSolicitado {

	private String codigoBarras;

	private Integer cantidad;

	@Override
	public String toString() {
		return "ProductoSolicitado [codigoBarras=" + codigoBarras + ", cantidad=" + cantidad + "]";
	}

	// SET Y GET
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
