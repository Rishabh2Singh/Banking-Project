package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.NetBanking;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
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
	public Status netBanking(@RequestBody NetBanking net) throws Exception
	{
		String res = netBankingService.enableNetBanking(net);
		Status status = new Status();
		if(res.equals("Successfully registered")) {
		status.setStatus(StatusType.SUCCESS);
		status.setMessage(res);}
		else {
			status.setStatus(StatusType.FAILED);
			status.setMessage(res);
		}
		return status;
	}

}
