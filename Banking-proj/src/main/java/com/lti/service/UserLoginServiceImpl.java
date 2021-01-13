package com.lti.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.Payee;
import com.lti.dto.Payees;
import com.lti.dto.Transaction;
import com.lti.entity.Account;
import com.lti.entity.Activity;
import com.lti.entity.Beneficiary;
import com.lti.entity.InternetBanking;
import com.lti.entity.PayeeDemo;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.repository.LoginRepository;
import com.lti.repository.PayeeRepository;
//import com.lti.repository.PayeeRepository;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	public LoginRepository loginRepo;
	
	@Autowired
	public PayeeRepository payeeRepo;

	public Account userLogin(int id, String password) throws UserLoginException, CustomerServiceException {
		try {
//			if(!loginRepo.isCustomerPresent(id))
//				throw new CustomerServiceException("Customer Not Registered!");
//			else {
				int custid=(int) loginRepo.findByLoginDetail(id, password);
				Account account=loginRepo.fetchAccountByCustomerId(custid);
				return account;
//			}
		}
		catch(NoResultException e) {
			throw new CustomerServiceException("Incorrect email/password");
		}
	
	}
	public List<Payee> fetchBeneficiary(int custId){
		List<Payee> names=payeeRepo.fetchBeneficiary(custId);
		return names;
	}
	@Override
	public int addPayee(Payee payee) {
//		System.out.println(payee.getAcno());
		Account acc=payeeRepo.fetch(Account.class, payee.getAcno());
		System.out.println(acc.getAccountNo());
//		System.out.println(payee.getCustomerId());
		InternetBanking ib=payeeRepo.fetch(InternetBanking.class,payee.getCustId());
		System.out.println(ib.getCustomerId());
		
		Beneficiary ben=new Beneficiary();
	
		ben.setName(payee.getName());
		ben.setNickname(payee.getNickname());
		ben.setType(payee.getType());
		ben.setAccount(acc);
		ben.setCustomer(ib);
		Beneficiary fetBen=(Beneficiary) payeeRepo.save(ben);
		System.out.println(fetBen.getId());
		return fetBen.getId();
	}
	@Override
	public long fetchAccountNo(int custId) {
		long acno=payeeRepo.fetchAccByCustId(custId);
		return acno;
	}
	@Override
	public int addActivity(Transaction transaction) {
		Activity act=new Activity();
		Account acc=payeeRepo.fetch(Account.class, transaction.getFromAc());
		Account acc2=payeeRepo.fetch(Account.class, transaction.getToAc());
		String transactionType="";
		Activity fetAct=null;
		if(transaction.getAmt()<acc.getAccountNo()) {
			act.setAmount(transaction.getAmt());
			act.setAccountInv(transaction.getToAc());
			act.setDate(LocalDate.now());
			act.setTime(LocalTime.now());
			transactionType=transaction.getType();
			transactionType=transactionType+ " Transfer";
			act.setType(transactionType);
			act.setRemark(transaction.getRemark());
			act.setAccount(acc);
			
			fetAct=(Activity) payeeRepo.save(act);
		
		
			payeeRepo.debitBalance(transaction.getAmt(), transaction.getFromAc());
			payeeRepo.creditBalance(transaction.getAmt(), transaction.getToAc());
		
			Activity recieverAct=new Activity();
			recieverAct.setAmount(transaction.getAmt());
			recieverAct.setAccountInv(transaction.getFromAc());
			recieverAct.setDate(LocalDate.now());
			recieverAct.setTime(LocalTime.now());
			transactionType=transaction.getType();
			transactionType=transactionType+" Recieve";
			act.setType(transactionType);
			recieverAct.setRemark(transaction.getRemark());
			recieverAct.setAccount(acc2);
			
			Activity fetAct2=(Activity) payeeRepo.save(act);
		}
		
		
		return fetAct.getId();
	}

}
