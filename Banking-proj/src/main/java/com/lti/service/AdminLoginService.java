package com.lti.service;

import java.util.List;

import com.lti.dto.AdminInfo;
import com.lti.dto.CustomerAccountDetails;
import com.lti.dto.CustomerDetails;
import com.lti.dto.InternetBankingApprovalDetails;
import com.lti.dto.InternetBankingDetails;
import com.lti.entity.AccountHolder;
import com.lti.entity.Admin;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;


public interface AdminLoginService {

	public AdminInfo adminLogin(String email, String password) throws UserLoginException, CustomerServiceException;
	
	public List<CustomerDetails> getCustomerAndAccountDetails(int holderId);
	
	public List<CustomerAccountDetails> getCustomers();
	
	//public List<InternetBankingDetails> getApplications();
	public void approveCustomerByHolderId(int holderId);
	
	public int approveCustomerInternetBanking(long holderId);
	
	public List<InternetBankingDetails> getCustomerInternetBankingDetails();
	
	public InternetBankingApprovalDetails getCustomerInternetBankingApprovalDetails(long accountNumber);
	
	public void rejectAcknowledgment(int id);
	
	public void internetBankingRejection(int id);
}
