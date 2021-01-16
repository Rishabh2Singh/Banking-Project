package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AccountSummaryDto;
import com.lti.service.AccountSummaryService;

@RestController
@CrossOrigin
public class AccountSummaryController{

	@Autowired
	AccountSummaryService accSummaryService;
	
	@GetMapping("/accountSummary")
	public @ResponseBody AccountSummaryDto accountSummary(@RequestParam("custId") int cusId){
		return accSummaryService.accountSummary(cusId);
	}
}
