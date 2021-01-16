package com.lti.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.exception.CustomerServiceException;

@Repository
public class ForgetUserRepository extends GenericRepository{
	
	public String fetchEmailByAccountNo(long acno) {
		Query q=entityManager.createQuery("select a.accountHolder.email from Account a where a.accountNo= :acno");
		q.setParameter("acno", acno);
		String email=(String) q.getSingleResult();
		return email;
	}

	public int fetchCustIdByAccountNo(long acno) throws CustomerServiceException {
		try {
			Query q=entityManager.createQuery("select a.internetBanking.customerId from Account a where a.accountNo= :acno");
			q.setParameter("acno", acno);
			int custId= (int)q.getSingleResult();
			return custId;
		}catch(NullPointerException e) {
			
			throw new CustomerServiceException("User not registered for InternetBanking services..!");
		}
	}

	public boolean isAccountPresent(long acno) {
		return (Long)entityManager.createQuery("select count(a.accountNo) from Account a where a.accountNo= :acno")
				.setParameter("acno", acno)
				.getSingleResult()==1 ? true: false;

	}
}
