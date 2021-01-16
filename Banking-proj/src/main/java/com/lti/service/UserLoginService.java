package com.lti.service;

import java.util.List;

import com.lti.dto.Payee;
import com.lti.dto.Payees;
import com.lti.dto.Transaction;
import com.lti.entity.Account;
import com.lti.entity.Beneficiary;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;

public interface UserLoginService {

	public Account userLogin(int id, String password) throws UserLoginException, CustomerServiceException ;

	public List<Payee> fetchBeneficiary(int custId);

	public int addPayee(Payee payee) throws CustomerServiceException;
//	public List<Account> fetchAccount(int i);

	public long fetchAccountNo(int custId);

	public int addActivity(Transaction transaction) throws CustomerServiceException;
	
	public int CustIdOnEmail(long acno) throws CustomerServiceException;
	
	public String sendCustId(long acno) throws CustomerServiceException;
}
