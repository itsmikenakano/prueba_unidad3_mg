package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Producto p) {
		this.entityManager.persist(p);

	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto p) {
		this.entityManager.merge(p);

	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarPorCodigoBarras(String codigo) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p Where p.codigoBarra= :datoCodigo", Producto.class);
		myQuery.setParameter("datoCodigo", codigo);

		return myQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarPorCodigo(String codigo) {
		CriteriaBuilder myCriteria = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> myQuery = myCriteria.createQuery(Producto.class);

		Root<Producto> myTabla = myQuery.from(Producto.class);

		Predicate predicadoCodigo = myCriteria.equal(myTabla.get("codigoBarra"), codigo);

		myQuery.select(myTabla).where(predicadoCodigo);

		TypedQuery<Producto> myQueryFinal = this.entityManager.createQuery(myQuery);

		return myQueryFinal.getSingleResult();

	}

}
