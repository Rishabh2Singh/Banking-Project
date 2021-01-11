package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository extends GenericRepository{

	public boolean isCustomerPresent(int id) {
		return (Long)entityManager.createQuery("select count(i.id) from InternetBanking i where i.id= :id")
				.setParameter("id", id)
				.getSingleResult()==1 ? true: false;
		
	}
	public int findByLoginDetail(int id, String password) {
		return (int)
				entityManager
				.createQuery("select i.customerId from InternetBanking i where i.customerId= :id and i.loginPass= : password")
				.setParameter("id", id)
				.setParameter("password", password)
				.getSingleResult();
	}
}
