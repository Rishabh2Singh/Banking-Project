package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Account;
import com.lti.entity.InternetBanking;

@Repository
public class ForgotPasswordRepository extends GenericRepository{

	public boolean checkUserByCustId(int custId) {
		return (Long)entityManager.createQuery("select count(a) from Account a where a.internetBanking.customerId = : custId")
		.setParameter("custId", custId)
		.getSingleResult()==1?true:false;
	}
	
	public Account fetchAccount(int custId) {
		List<Account> accounts=entityManager.createQuery("select a from Account a where a.internetBanking.customerId = : custId")
				.setParameter("custId", custId).getResultList();
		return accounts.get(0);
	}
	
	public void updatePassword(int custId,String pwd) {
		List<Account> accounts=entityManager.createQuery("select a from Account a where a.internetBanking.customerId = : custId")
				.setParameter("custId", custId).getResultList();
		InternetBanking ib= accounts.get(0).getInternetBanking();
		ib.setLoginPass(pwd);
		save(ib);
	}
}
