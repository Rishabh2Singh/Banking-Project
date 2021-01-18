package com.lti.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
import com.lti.repository.ForgetUserRepository;
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
	
	@Autowired
	public ForgetUserRepository forgetUserRepo;
	
	@Autowired
	private EmailService emailService;

	public Account userLogin(int id, String password) throws UserLoginException, CustomerServiceException {

			if(!loginRepo.isCustomerPresent(id))
				throw new CustomerServiceException("Customer Not Registered!");
			else {
				int custid=(int) loginRepo.findByLoginDetail(id, password);
				Account account=loginRepo.fetchAccountByCustomerId(custid);
				System.out.println(account.getInternetBanking().getCustomerId());
				return account;
			}
	}
	
	public List<Payee> fetchBeneficiary(int custId){
		List<Payee> names=payeeRepo.fetchBeneficiary(custId);
		return names;
	}
	
	@Override
	public int addPayee(Payee payee) throws CustomerServiceException {
		if(!(payeeRepo.isAccountActive(payee.getAcno()))){
			throw new CustomerServiceException("Invalid Payee Details");
		}
		else if(payeeRepo.isPayeePresent(payee.getAcno(),payee.getCustId())) {
			throw new CustomerServiceException("Payee already present in your Payee List");
		}
		else {
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
	}
	@Override
	public long fetchAccountNo(int custId) {
		long acno=payeeRepo.fetchAccByCustId(custId);
		return acno;
	}
	@Override
	public int addActivity(Transaction transaction) throws CustomerServiceException {
		
		Account acc=payeeRepo.fetch(Account.class, transaction.getFromAc());
		Account acc2=payeeRepo.fetch(Account.class, transaction.getToAc());
		String transactionType="";
		Activity fetAct=null;
		if(transaction.getAmt()<acc.getBalance()) {
			Activity act=new Activity();
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
		
			String email=forgetUserRepo.fetchEmailByAccountNo(transaction.getFromAc());
			String subject="Account balance update for your bank account";
			String mssg="Dear customer, "+transaction.getAmt()+" has been debited from your account. Your transaction id is "+fetAct.getId()+". If this is not you then kindly report to your near branch of RICA bank. Thank you.";
			emailService.sendEmail(email, subject, mssg);
			
			email=forgetUserRepo.fetchEmailByAccountNo(transaction.getToAc());
			mssg="Dear customer, "+transaction.getAmt()+" has been credited to your account. Thank you.";
			emailService.sendEmail(email, subject, mssg);
			
			Activity recieverAct=new Activity();
			recieverAct.setAmount(transaction.getAmt());
			recieverAct.setAccountInv(transaction.getFromAc());
			recieverAct.setDate(LocalDate.now());
			recieverAct.setTime(LocalTime.now());
			transactionType=transaction.getType();
			transactionType=transactionType+" Recieve";
			recieverAct.setType(transactionType);
			recieverAct.setRemark(transaction.getRemark());
			recieverAct.setAccount(acc2);
			Activity fetAct2=(Activity) payeeRepo.save(recieverAct);
		}
		else {
			throw new CustomerServiceException("Insufficient Balance");
		}
		
		
		return fetAct.getId();
	}
	@Override
	public int CustIdOnEmail(long acno) throws CustomerServiceException {
		if(!forgetUserRepo.isAccountPresent(acno))
			throw new CustomerServiceException("Invalid Account Number!");
		else {
			String email=forgetUserRepo.fetchEmailByAccountNo(acno);
			String subject="Otp for regenerating CustomerId";
			int custId=forgetUserRepo.fetchCustIdByAccountNo(acno);
			int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999);
			String message="Your One Time Password(OTP) for getting your CustomerId is "+randomNum;
			emailService.sendEmail(email, subject, message);
			System.out.println("Otp sent");
			return randomNum;
		}
	}
	@Override
	public String sendCustId(long acno) throws CustomerServiceException {
		if(!forgetUserRepo.isAccountPresent(acno)) {
			System.out.println("Invalid");
			throw new CustomerServiceException("Invalid Account Number!");
		}
		else {
			System.out.println(acno);
			String email=forgetUserRepo.fetchEmailByAccountNo(acno);
			System.out.println(email);
			int custId=forgetUserRepo.fetchCustIdByAccountNo(acno);
			System.out.println(custId);
			String subject="Regenerated CustomerId";
			String message="Dear customer, Thank you for choosing our bank services. Your request for sending your customer Id is approved. Here is your customer Id: "+custId;
			emailService.sendEmail(email, subject, message);
			String status="Customer Id is successfully sent";
			System.out.println(status);
			return status;
		}
	}
	public String removePayeeDetails(long acno, int custId) {
		
		int i=payeeRepo.removePayee(acno, custId);
		if(i>=1) {
			return "Removed successfully";
		}
		else {
			return "Removal failed";
		}
	}
}
