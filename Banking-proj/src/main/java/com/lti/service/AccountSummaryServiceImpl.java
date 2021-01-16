package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.AccountSummaryDto;
import com.lti.entity.Account;
import com.lti.entity.Activity;
import com.lti.repository.AccountSummaryRepository;

@Service
@Transactional
public class AccountSummaryServiceImpl implements AccountSummaryService {

	@Autowired
	AccountSummaryRepository accountSummaryRepository;
	
	@Override
	public AccountSummaryDto accountSummary(int custId) {
	 	Account account=accountSummaryRepository.fetchAccountByCustomerId(custId);
	 	List<Activity> activities=accountSummaryRepository.fetchAccountSummaryByCustomerId(account);
	 	AccountSummaryDto accountSummaryDto=new AccountSummaryDto();
	 	accountSummaryDto.setAccountNo(account.getAccountNo());
	 	accountSummaryDto.setName(account.getAccountHolder().getName());
	 	accountSummaryDto.setEmail(account.getAccountHolder().getEmail());
		accountSummaryDto.setAdharCard(account.getAccountHolder().getAdharCard());
	 	accountSummaryDto.setType(account.getType());
	 	accountSummaryDto.setBalance(account.getBalance());
	 	accountSummaryDto.setActivity(activities);
	 	return accountSummaryDto;
	}

}
