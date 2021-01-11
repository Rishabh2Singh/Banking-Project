package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	public Object save(Object obj) {
		Object updatedObject = entityManager.merge(obj);
		return updatedObject;
	}
	
	public <E> E fetch(Class<E> clazz, Object pk) {
		E e=entityManager.find(clazz, pk);
		return e;
	}
}
