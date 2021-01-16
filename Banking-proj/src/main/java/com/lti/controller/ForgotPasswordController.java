package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ForgotPasswordDto;
import com.lti.service.ForgotPasswordService;

@RestController
@CrossOrigin
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@GetMapping("/forgotPassword")
	public ForgotPasswordDto forgotPassword(@RequestParam("custId") int custId) {
			return forgotPasswordService.checkCusId(custId);
	}
	
	@GetMapping("/setNewPassword")
	public ForgotPasswordDto setNewPassword(@RequestParam("custId") int custId,@RequestParam("pwd") String pwd) {
		return forgotPasswordService.updateNewPassword(custId,pwd);
	}
}
