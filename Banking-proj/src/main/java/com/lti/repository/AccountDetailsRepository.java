package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.Account;
import com.lti.entity.AccountHolder;

@Repository
public class AccountDetailsRepository extends GenericRepository {

	public Account fetchByCustomerId(int customerId) {

		Account account= (Account) entityManager.createQuery("select a from Account a where a.internetBanking.customerId= :cusId")
				.setParameter("cusId", customerId).getSingleResult();
//		//System.out.println(account.getAccountNo()+account.getAccountHolder().getName()+account.getAccountHolder().getAdharCard());
		return account;
	}
}
