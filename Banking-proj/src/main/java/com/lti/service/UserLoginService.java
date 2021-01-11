package com.lti.service;

import com.lti.entity.Account;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;

public interface UserLoginService {

	public Account userLogin(int id, String password) throws UserLoginException, CustomerServiceException ;
}
