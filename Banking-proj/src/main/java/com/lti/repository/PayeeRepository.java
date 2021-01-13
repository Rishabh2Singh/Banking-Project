package com.lti.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.dto.Payee;
import com.lti.entity.Account;
import com.lti.entity.Beneficiary;

@Repository
public class PayeeRepository extends GenericRepository{

	public List<Payee> fetchBeneficiary(int custId){
		Query q=entityManager.createQuery("select b.account.accountNo,b.name,b.type,b.nickname from Beneficiary b where b.customer.customerId= :id");
//		Query q=entityManager.createQuery("select b from Beneficiary b join b.customer c where c.customerId= :id");
		q.setParameter("id", custId);
		List<Payee> beneficiaries=q.getResultList();
		return beneficiaries;
	}

	public long fetchAccByCustId(int custId) {
		Query q=entityManager.createQuery("select a.accountNo from Account a where a.internetBanking.customerId= :id");
		q.setParameter("id", custId);
		long acno=(long) q.getSingleResult();
		return acno;
	}
	public void creditBalance(double amount, long acno) {
		Query q=entityManager.createQuery("update Account set balance=balance+ :amount where accountNo= :no ");
		q.setParameter("amount", amount);
		q.setParameter("no", acno);
		int i=q.executeUpdate();
		System.out.println(i);
	}
	public void debitBalance(double amount, long acno) {
		Query q=entityManager.createQuery("update Account set balance=balance- :amount where accountNo= :no ");
		q.setParameter("amount", amount);
		q.setParameter("no", acno);
		int i=q.executeUpdate();
		System.out.println(i);
	}
}
