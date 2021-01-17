package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AccountStatementDto;
import com.lti.dto.AccountStatementListDto;
import com.lti.dto.AccountStatementResDto;
import com.lti.service.AccountStatementService;

@RestController
@CrossOrigin
public class AccountStatementController {

	@Autowired
	private AccountStatementService accountStatementService;
	
	@PostMapping("/accountStatement")
	public @ResponseBody AccountStatementListDto accountStatement(
			@RequestBody AccountStatementDto accountStatementDto) {
		List<AccountStatementResDto> activities = accountStatementService.accountStatement(accountStatementDto);
		AccountStatementListDto accountStatementListDto = new AccountStatementListDto();
		if(activities.size()==0) {
			accountStatementListDto.setMessage("No Transactions between these dates");
			return accountStatementListDto;
		}
		else {
			accountStatementListDto.setMessage("Transaction List");
			accountStatementListDto.setResDto(activities);
			return accountStatementListDto;
		}
	}
	 
	@GetMapping("/defaultAccountStatement")
	public @ResponseBody AccountStatementListDto defaultAccountStatement(@RequestParam("custId") int cusId) {
		System.out.println(cusId);
		List<AccountStatementResDto> activities=accountStatementService.defaultAccountStatement(cusId);
		AccountStatementListDto accountStatementListDto = new AccountStatementListDto();
		if(activities.size()==0) {
			accountStatementListDto.setMessage("No Transactions Yet");
			return accountStatementListDto;
		}
		else {
		accountStatementListDto.setMessage("Recent Transactions");
		accountStatementListDto.setResDto(activities);
		return accountStatementListDto;
		}
	}
}
