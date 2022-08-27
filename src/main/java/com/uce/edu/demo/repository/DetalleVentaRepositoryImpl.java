package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.DetalleVenta;

@Repository
@Transactional
public class DetalleVentaRepositoryImpl implements IDetalleVentaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<DetalleVenta> buscaPorFechaCantidad(LocalDateTime fechaVenta, Integer cantidad) {
		TypedQuery<DetalleVenta> myquery = this.entityManager.createNamedQuery("DetalleVenta.buscarPorFechaCantidad",
				DetalleVenta.class);
		myquery.setParameter("fecha", fechaVenta);
		myquery.setParameter("datoCantidad", cantidad);
		List<DetalleVenta> detalles = myquery.getResultList();
		for (DetalleVenta d : detalles) {
			d.getProducto().getNombre();
		}
		return detalles;
	}

}
