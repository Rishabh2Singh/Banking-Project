package com.lti.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Account;
import com.lti.entity.Activity;

@Repository
public class AccountSummaryRepository extends GenericRepository {

	public Account fetchAccountByCustomerId(int custId) {
		Account account = (Account) entityManager
				.createQuery("select a from Account a where a.internetBanking.customerId= :cusId")
				.setParameter("cusId", custId).getSingleResult();
		return account;
	}

	public List<Activity> fetchAccountSummaryByCustomerId(Account account) {

		Query q = entityManager
				.createQuery("select a from Activity a where a.account.accountNo = :accNo order by a.date desc");
		q.setParameter("accNo", account.getAccountNo());
		q.setMaxResults(5);
		List<Activity> miniStatement = q.getResultList();
		System.out.println(miniStatement.size());
		return miniStatement;
	}
}
