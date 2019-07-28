package de.bitc.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.bitc.model.Customer;

/**
 * DAO for Customer
 */
@Stateless
@LocalBean
public class CustomerDao {
	@PersistenceContext(unitName = "sample")
	private EntityManager em;

	public void create(Customer entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Customer entity = em.find(Customer.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}

	public Customer update(Customer entity) {
		return em.merge(entity);
	}

	public List<Customer> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Customer> findAllQuery = em.createQuery(
				"SELECT DISTINCT c FROM Customer c ORDER BY c.id",
				Customer.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
