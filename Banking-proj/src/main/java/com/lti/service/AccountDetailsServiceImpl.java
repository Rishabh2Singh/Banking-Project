package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.AccountDetailsDto;
import com.lti.entity.Account;
import com.lti.entity.InternetBanking;
import com.lti.repository.AccountDetailsRepository;

@Service 
@Transactional
public class AccountDetailsServiceImpl implements AccountDetailsService{

	@Autowired 
	private AccountDetailsRepository accountDetailsRepository;
	
	public AccountDetailsDto accountDetails(int customerId) {
		Account account= accountDetailsRepository.fetchByCustomerId(customerId);
		AccountDetailsDto accDetDto= new AccountDetailsDto();
		accDetDto.setName(account.getAccountHolder().getName());
		accDetDto.setDob(account.getAccountHolder().getDob());
		accDetDto.setMobile(account.getAccountHolder().getMobile());
		accDetDto.setAadharNumber(account.getAccountHolder().getAdharCard());
		accDetDto.setAdd1(account.getAccountHolder().getAccountHolderAdd1());
		accDetDto.setAdd2(account.getAccountHolder().getAccountHolderAdd2());
		accDetDto.setOccupation(account.getAccountHolder().getAccountHolderOccu());
//		System.out.println(account.getAccountNo()+account.getAccountHolder().getName()+account.getAccountHolder().getAdharCard());
		return accDetDto;
	}
}
