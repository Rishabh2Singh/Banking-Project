package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.NetBanking;
import com.lti.entity.Account;
import com.lti.entity.InternetBanking;
import com.lti.service.AccountService;
import com.lti.service.NetBankingService;

@RestController
@CrossOrigin
public class NetBankingController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private NetBankingService netBankingService;
	
	@PostMapping("/netbanking")
	public void netBanking(@RequestBody NetBanking net) throws Exception
	{
		netBankingService.enableNetBanking(net);
		
	}

}
