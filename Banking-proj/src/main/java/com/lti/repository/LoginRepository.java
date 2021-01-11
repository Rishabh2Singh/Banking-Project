package com.lti.repository;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Account;

@Repository
public class LoginRepository extends GenericRepository{

	public boolean isCustomerPresent(long id) {
//		return (Long)entityManager.createQuery("select count(i.customerId) from InternetBanking i where i.customerId= :id")
//				.setParameter("id", id)
//				.getSingleResult()==1 ? true: false;
		Query q=entityManager.createQuery("select count(i.customerId) from InternetBanking i where i.customerId = :id");
		q.setParameter("id", id);
		long c=(long) q.getSingleResult();
		System.out.println(c);
		if(c==1)
			return true;
		else
			return false;
	}
	public long findByLoginDetail(int id, String password) {
		Query q=entityManager.createQuery("select i.customerId from InternetBanking i where customerId= :id and loginPass= : password");
		q.setParameter("id", id);
		q.setParameter("password", password);
		int custid=(int)q.getSingleResult();
//		System.out.println(custid);
		return custid;
	}
	public Account fetchAccountByCustomerId(int id) {
//		Query q=entityManager.createQuery("select a from Account a where a.internetBanking.customerId= :id");
		Query q=entityManager.createQuery("select a from Account a join a.internetBanking b where b.customerId= :id");
		q.setParameter("id", id);
		Account acc=(Account) q.getSingleResult();
//		System.out.println(acc.getAccountNo()+" "+acc.getBalance());
		return acc;
	}
}
