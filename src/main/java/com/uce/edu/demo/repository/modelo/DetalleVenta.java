package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_venta")
@NamedQuery(name = "DetalleVenta.buscarPorFechaCantidad", query = "SELECT d FROM DetalleVenta d JOIN d.venta v WHERE v.fecha >= :fecha AND d.cantidad >= :datoCantidad")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deve_id_seq")
	@SequenceGenerator(name = "deve_id_seq", sequenceName = "deve_id_seq", allocationSize = 1)
	@Column(name = "deve_id")
	private Integer id;

	@Column(name = "deve_cantidad")
	private Integer cantidad;

	@Column(name = "deve_precio_unitario")
	private BigDecimal precioUnitario;

	@Column(name = "deve_subtotal")
	private BigDecimal subtotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deve_id_producto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "deve_id_venta")
	private Venta venta;

	@Override
	public String toString() {
		return "DetalleVenta [id=" + id + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario
				+ ", Subtotal=" + subtotal + ", producto=" + producto + ", venta=" + venta + "]";
	}

	// SET Y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}
