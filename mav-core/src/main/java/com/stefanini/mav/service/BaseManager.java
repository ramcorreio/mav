package com.stefanini.mav.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;

public class BaseManager {

	@PersistenceContext(unitName = "biller-pu")
	private EntityManager entityManager;
	
	protected BaseManager() {
	}
	
	protected <T> T get(Class<T> clazz, Number id) {
		return entityManager.find(clazz, id);
	}
	
	/**
	 * Copiar apenas dados primitivos
	 * 
	 * @param dest
	 * @param src
	 */
	protected void copyProperties(Object dest, Object src) {
		copyProperties(dest, src, new String[0]);
	}
	
	protected void copyPropertiesSemId(Object dest, Object src) {
		copyProperties(dest, src, new String[]{"id"});
	}
	
	protected void copyProperties(Object dest, Object src, String[] excludes) {
		
		BeanUtils.copyProperties(src, dest, excludes);
	}

	private void throwsExcepiton(PersistenceException e) throws BrokerException {
		if (ConstraintViolationException.class.isInstance(e.getCause())) {
			throw new BrokerException(e.getCause());
		} else if (DataIntegrityViolationException.class.isInstance(e .getCause())) {
			throw new BrokerException(e.getCause());
		}
	}

	protected <T> void create(T entity) throws BrokerException {
		try {
			entityManager.persist(entity);
		} catch (PersistenceException e) {
			throwsExcepiton(e);
		}
	}

	protected <T> TypedQuery<T> createNamedQuery(String q, Class<T> clazz) {
		return entityManager.createNamedQuery(q, clazz);
	}

	protected <T> T update(T entity) throws BrokerException {
		try {
			entity = entityManager.merge(entity);
		} catch (PersistenceException e) {
			throwsExcepiton(e);
		}

		return entity;
	}
	
	protected <T> void remove(T entity) {
		entityManager.remove(entity);
	}

	protected <T> boolean contains(T entity) {
		return entityManager.contains(entity);
	}
}
