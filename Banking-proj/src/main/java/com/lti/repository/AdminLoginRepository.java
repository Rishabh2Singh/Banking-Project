package com.lti.repository;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.dto.AdminInfo;
import com.lti.dto.CustomerAccountDetails;
import com.lti.dto.CustomerDetails;
import com.lti.dto.InternetBankingApprovalDetails;
import com.lti.dto.InternetBankingDetails;
//import com.lti.dto.InternetBanking;
import com.lti.entity.Account;
import com.lti.entity.AccountHolder;
import com.lti.entity.Admin;
import com.lti.entity.ApprovalActivity;
import com.lti.entity.InternetBanking;

@Repository
public class AdminLoginRepository extends GenericRepository {

	public AdminInfo fetchAdmin(String email,String password) {

		
		System.out.println("email received :"+email+"password received :"+password);
		Query q = entityManager.createQuery("select al from Admin al where al.email= :email");
		q.setParameter("email", email);
		//q.setParameter("password", password);
		Admin ad =(Admin) q.getSingleResult();
		AdminInfo af=new AdminInfo();
		
		af.setContact(ad.getContact());
		af.setDesignation(ad.getDesignation());
		af.setEmail(ad.getEmail());
		af.setId(ad.getId());
		af.setName(ad.getName());
		

		//System.out.println("email received At repo : " + ad);
		

		return af;
	}

	public List<CustomerAccountDetails> fetchCustomers() {

		Query q = entityManager.createQuery("select ac from Account ac where ac.status = 0");

		List<CustomerAccountDetails> customerAccountDetails = new ArrayList<>();

		List<Account> customerAccounts = q.getResultList();

		customerAccounts.forEach(custAcnt -> {
			CustomerAccountDetails cad = new CustomerAccountDetails();
			cad.setAccountNo(custAcnt.getAccountNo());
			cad.setAccountHolderId(custAcnt.getAccountHolder().getId());
			cad.setAccountHolderName(custAcnt.getAccountHolder().getName());

			customerAccountDetails.add(cad);
		});

		System.out.println("CAD : " + customerAccountDetails);
		return customerAccountDetails;
	}

	public List<InternetBankingDetails> fetchInternetBankingCustomers() {

		List<InternetBankingDetails> ls = new ArrayList<>();

		Query q = entityManager.createQuery("select ib from InternetBanking ib where ib.status = 0");

		List<InternetBanking> internetBankingList = q.getResultList();

		internetBankingList.forEach(ib -> {
			InternetBankingDetails ibd = new InternetBankingDetails();
			ibd.setCustomerId(ib.getCustomerId());
			ibd.setAccountNo(ib.getAccount().getAccountNo());
			ibd.setAccountHolderName(ib.getAccount().getAccountHolder().getName());

			ls.add(ibd);
		});

		return ls;
	}

	public InternetBankingApprovalDetails fetchInternetBankingApprovalDetails(long accountNumber) {

		// List<InternetBankingApprovalDetails> ibad=new ArrayList<
		System.out.println("value fetched at repo1 : "+accountNumber);
		InternetBankingApprovalDetails ibad = new InternetBankingApprovalDetails();
		Query q = entityManager.createQuery("select a from Account a where a.accountNo= : accountNumber");
		q.setParameter("accountNumber", accountNumber);

		Account rs = (Account) q.getSingleResult();

		ibad.setAccountNo(rs.getAccountNo());
		ibad.setBalance(rs.getBalance());
		ibad.setType(rs.getType());
		ibad.setAdharCard(rs.getAccountHolder().getAdharCard());
		ibad.setName(rs.getAccountHolder().getName());
		ibad.setDebitCard(rs.getAccountHolder().getDebitCard());

		return ibad;

	}

	@SuppressWarnings("unchecked")
	public List<CustomerDetails> fetchCustomerDetailsByHolderId(int holderId) {

		List<CustomerDetails> customerDetails = new ArrayList<>();

		Query q = entityManager.createQuery("select ah from AccountHolder ah where ah.id= : holderId");
		q.setParameter("holderId", holderId);

		List<AccountHolder> accountHolders = q.getResultList();

		for (AccountHolder accountHolder : accountHolders) {

			CustomerDetails cd = new CustomerDetails();

			cd.setName(accountHolder.getName());
			cd.setFatherName(accountHolder.getFatherName());
			cd.setAdharCard(accountHolder.getAdharCard());
			cd.setAddress(accountHolder.getAccountHolderAdd1().getAddress());
			cd.setDob(accountHolder.getDob());
			cd.setIncome_source(accountHolder.getAccountHolderOccu().getIncome_source());
			cd.setAnnual_income(accountHolder.getAccountHolderOccu().getAnnual_income());
			cd.setCity(accountHolder.getAccountHolderAdd1().getCity());
			cd.setState(accountHolder.getAccountHolderAdd1().getState());

			customerDetails.add(cd);
		}

		return customerDetails;

	}

	public void approveCustomer(int holderId) {
		System.out.println("###########" + holderId);
		Query q = entityManager
				.createQuery("update Account ah set ah.status = '1' where ah.accountHolder.id= :hid");
		q.setParameter("hid", holderId);
		System.out.println("Query : "+q);
		q.executeUpdate();
		
		/*Query q1= entityManager.createQuery("select a from Account a where a.accountNo= : accountNumber");
		q1.setParameter("accountNumber", holderId);

		Account rs = (Account) q.getSingleResult();*/
		//String mail=rs.getAccountHolder().get;
		
	}
	public void rejectCustomer(int holderId) {
		System.out.println("########### Sending rejection mail to : " + holderId);
		/*Query q1= entityManager.createQuery("select a from Account a where a.accountNo= : accountNumber");
		q1.setParameter("accountNumber", holderId);

		Account rs = (Account) q.getSingleResult();*/
		//String mail=rs.getAccountHolder().get;
		
	}
	public void internetBankingRejectionAcknowledgment(int holderId) {
		System.out.println("########### Sending rejection mail to : " + holderId);
		/*Query q1= entityManager.createQuery("select a from Account a where a.accountNo= : accountNumber");
		q1.setParameter("accountNumber", holderId);

		Account rs = (Account) q.getSingleResult();*/
		//String mail=rs.getAccountHolder().get;
		
	}

	public int InternetBankingChangeStatus(long holderId) {
		System.out.println("fetched at repo Internet ###########" + holderId);
		Query q = entityManager.createQuery("select a from Account a where a.accountNo= : accountNumber");
		q.setParameter("accountNumber", holderId);

		Account rs = (Account) q.getSingleResult();
		System.out.println("customer no fetched from db "+rs.getInternetBanking().getCustomerId());
		int customerid=rs.getInternetBanking().getCustomerId();
		Query q1 = entityManager
				.createQuery("update InternetBanking ab set ab.status = '1' where ab.customerId= :holderId1");
		q1.setParameter("holderId1", customerid);

		q1.executeUpdate();
		
		
		return 1;
	}
	
	public List<CustomerDetails> fetchInternetBankingRequest() {
		List<CustomerDetails> accountOpeningList = new ArrayList<>();

		// TODO : Need to write query and set data in CustomerDetails and add it in a
		// list before sending.
		return accountOpeningList;
	}

}
