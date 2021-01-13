package com.lti.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Account;
import com.lti.entity.Beneficiary;

@Repository
public class LoginRepository extends GenericRepository{

	public boolean isCustomerPresent(long id) {
//		return (Long)entityManager.createQuery("select count(i.customerId) from InternetBanking i where i.customerId= :id")
//				.setParameter("id", id)
//				.getSingleResult()==1 ? true: false;
		Query q=entityManager.createQuery("select count(i.customerId) from InternetBanking i where i.customerId = :id");
		q.setParameter("id", id);
		long c=(long) q.getSingleResult();
//		System.out.println(c);
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
		Query q=entityManager.createQuery("select a from Account a join a.internetBanking b where b.customerId= :id");
		q.setParameter("id", id);
		Account acc=(Account) q.getSingleResult();
		return acc;
	}

	public List<Beneficiary> fetchBeneficiary(int custId){
		Query q=entityManager.createQuery("select b from Beneficiary b join b.customer c where c.customerId= :id");
		q.setParameter("id", custId);
		List<Beneficiary> beneficiaries=q.getResultList();
		return beneficiaries;
		
	}
}
