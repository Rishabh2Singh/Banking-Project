package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.AdminInfo;
import com.lti.dto.CustomerAccountDetails;
import com.lti.dto.CustomerDetails;
import com.lti.dto.InternetBankingApprovalDetails;
import com.lti.dto.InternetBankingDetails;
import com.lti.entity.Account;
import com.lti.entity.AccountHolder;
import com.lti.entity.Admin;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.repository.AdminLoginRepository;
import com.lti.repository.LoginRepository;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	public AdminLoginRepository adminLoginRepo;
	
	@Autowired
	private EmailService emailService;
	
	public AdminInfo adminLogin(String email, String password) throws CustomerServiceException {
		if(!adminLoginRepo.isAdminPresent(email)) {
			throw new CustomerServiceException("Admin not present");
		}
		else {
			AdminInfo adminInfo = adminLoginRepo.fetchAdmin(email,password);
			return adminInfo;
		}
	}
	
	public List<CustomerDetails> getCustomerAndAccountDetails(int holderId) {
		return adminLoginRepo.fetchCustomerDetailsByHolderId(holderId);
	}
	
	public void approveCustomerByHolderId(int holderId) {
		
		adminLoginRepo.approveCustomer(holderId);
		
		AccountHolder accHol=adminLoginRepo.fetch(AccountHolder.class, holderId);
		String email=accHol.getEmail();
		String subject="Account approval update";
		String mssg="Greetings. Dear "+accHol.getName()+", your request for account creation ("+accHol.getAccount().getAccountNo()+") has been approved by RICA bank. We are delighted to have you in our RICA family. In case of any bank related queries and services please contact to our nearest branch. Thank you.";
		emailService.sendEmail(email, subject, mssg);
		
		
	}

	@Override
	public List<CustomerAccountDetails> getCustomers() {
		return adminLoginRepo.fetchCustomers();
	}
	
	@Override
	public List<InternetBankingDetails> getCustomerInternetBankingDetails() {
		return adminLoginRepo.fetchInternetBankingCustomers();
	}
	
	@Override
	public InternetBankingApprovalDetails getCustomerInternetBankingApprovalDetails(long accountNumber) {
		return adminLoginRepo.fetchInternetBankingApprovalDetails(accountNumber);
	}
	public int approveCustomerInternetBanking(long holderId) {
		System.out.println("called at service ");
		int rs=adminLoginRepo.InternetBankingChangeStatus(holderId);

		Account acc=adminLoginRepo.fetch(Account.class, holderId);
		String email=acc.getAccountHolder().getEmail();
		String subject="Customer ID approval update";
		String mssg="Greetings. Dear "+acc.getAccountHolder().getName()+", your request for internet banking (ID:"+acc.getInternetBanking().getCustomerId()+") has been approved by RICA bank. We are delighted to have you in our RICA internet banking family. Hope this services will make your daily activities easier. In case of any bank related queries and services please contact to our nearest branch. Thank you.";
		emailService.sendEmail(email, subject, mssg);
		
		return rs;
	}
	public void rejectAcknowledgment(int id) {
		adminLoginRepo.rejectCustomer(id);
	}
	public void internetBankingRejection(int id) {
		adminLoginRepo.internetBankingRejectionAcknowledgment(id);
	}
}