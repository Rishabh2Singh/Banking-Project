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

	public boolean isPayeePresent(long acno, int custId) {
		return (Long)entityManager.createQuery("select count(b) from Beneficiary b where b.account.accountNo= :acno and b.customer.customerId =:custId")
				.setParameter("acno", acno)
				.setParameter("custId", custId)
				.getSingleResult()>=1 ? true: false;  

	}

	public boolean isAccountActive(long acno) {
		return (Long)entityManager.createQuery("select count(a) from Account a where a.accountNo= :acno and a.status= :sta")
				.setParameter("acno", acno)
				.setParameter("sta", 1)
				.getSingleResult()==1 ? true: false;
	}
	public int removePayee(long acno, int custId) {
		Query q=entityManager.createQuery("update Beneficiary b set b.customer=1356786289 where b.account.accountNo= :acno and b.customer.customerId= :custId ");
		q.setParameter("acno", acno);
		q.setParameter("custId",custId);
		int i=q.executeUpdate();
		return i;
	}
}
