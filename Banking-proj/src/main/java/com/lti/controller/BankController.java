package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Account;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.service.UserLoginService;

@RestController
@CrossOrigin
public class BankController {

	@Autowired
	private UserLoginService loginService;
	
	@PostMapping("/testing")
	public @ResponseBody String printMessage(@RequestParam String message) {
		
		message=message+" controller";
		return message;
		
	}
	@PostMapping("/login")
	public @ResponseBody Account loginCheck(@RequestBody Login login) {
		
		Account acc = null;
		try {
			acc = loginService.userLogin(login.getCustomerId(), login.getPassword());
		} catch (UserLoginException e) {
			e.printStackTrace();
		} catch (CustomerServiceException e) {
			e.printStackTrace();
		}
		
		return acc;
	}
}
