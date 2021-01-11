package com.lti.service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Account;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.repository.LoginRepository;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	public LoginRepository loginRepo;

	@Override
	public Account userLogin(int id, String password) throws UserLoginException, CustomerServiceException {
		try {
			if(!loginRepo.isCustomerPresent(id))
				throw new UserLoginException("Invalid Customer");
			int acno=loginRepo.findByLoginDetail(id, password);
			Account account=loginRepo.fetch(Account.class, acno);
			return account;
		}
		catch(NoResultException e) {
			throw new CustomerServiceException("Incorrect email/password");
		}
	
	}	

}
