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
	
	public AdminInfo adminLogin(String email, String password) {
		AdminInfo ad=null;
		ad = adminLoginRepo.fetchAdmin(email,password);
		return ad;
	}
	
	public List<CustomerDetails> getCustomerAndAccountDetails(int holderId) {
		return adminLoginRepo.fetchCustomerDetailsByHolderId(holderId);
	}
	
	public void approveCustomerByHolderId(int holderId) {
		
		adminLoginRepo.approveCustomer(holderId);
		
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
		
		return rs;
	}
	public void rejectAcknowledgment(int id) {
		adminLoginRepo.rejectCustomer(id);
	}
	public void internetBankingRejection(int id) {
		adminLoginRepo.internetBankingRejectionAcknowledgment(id);
	}
}