package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Account;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.service.UserLoginService;

@RestController
@CrossOrigin
public class BankController {

	@Autowired
	private UserLoginService loginService;
	
	@PostMapping("/login")
	public Account login(@RequestParam int id, @RequestParam String password) {
		Account acc = null;
		try {
			acc = loginService.userLogin(id, password);
		} catch (UserLoginException e) {
			e.printStackTrace();
		} catch (CustomerServiceException e) {
			e.printStackTrace();
		}
		return acc;
	}
}
