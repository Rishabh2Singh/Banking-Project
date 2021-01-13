package com.lti.service;

import com.lti.dto.Registration;
import com.lti.entity.AccountHolder;

public interface UserRegService {

	public AccountHolder register(Registration reg);
}
