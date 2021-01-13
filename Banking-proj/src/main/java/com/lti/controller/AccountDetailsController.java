package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AccountDetailsDto;
import com.lti.service.AccountDetailsService;

@RestController
@CrossOrigin
public class AccountDetailsController {
	
	@Autowired
	private AccountDetailsService accountDetailsService;

	@GetMapping("/accountDetails")
	public @ResponseBody AccountDetailsDto accountDetails(@RequestParam("cusId") int customerId) {
	return accountDetailsService.accountDetails(customerId);	
	}
}
