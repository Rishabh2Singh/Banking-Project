package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.AccountStatementDto;
import com.lti.dto.AccountStatementResDto;
import com.lti.entity.Account;
import com.lti.entity.Activity;
import com.lti.repository.AccountStatementRepository;
import com.lti.repository.AccountSummaryRepository;

@Service 
@Transactional
public class AccountStatementServiceImpl implements AccountStatementService {

	@Autowired
	private AccountStatementRepository accountStatementRepository;
	
	@Autowired
	private AccountSummaryRepository accountSummaryRepo;
	
	public List<AccountStatementResDto> accountStatement(AccountStatementDto accountStatement) {
		List<Activity> activities=accountStatementRepository.fetchAccountStatementOfAccountHolder(accountStatement);
		List<AccountStatementResDto> accStatResList=new ArrayList<AccountStatementResDto>();
		for(Activity activity:activities) {
			Account account=accountStatementRepository.fetch(Account.class,activity.getAccountInv());
			String name=account.getAccountHolder().getName();
			AccountStatementResDto accStatRes=new AccountStatementResDto();
			accStatRes.setAccountNo(activity.getAccountInv());
			accStatRes.setAmount(activity.getAmount());
			accStatRes.setName(name);
			accStatRes.setDate(activity.getDate());
			accStatRes.setType(activity.getType());
			accStatRes.setTime(activity.getTime());
		    accStatResList.add(accStatRes);
		}
		return accStatResList;
	}

	@Override
	public List<AccountStatementResDto> defaultAccountStatement(int custId) {
		Account account1=accountSummaryRepo.fetchAccountByCustomerId(custId);
		System.out.println("custId"+custId+"acc No"+account1.getAccountNo());
	 	List<Activity> activities=accountSummaryRepo.fetchAccountSummaryByCustomerId(account1);
	 	List<AccountStatementResDto> accStatResList=new ArrayList<AccountStatementResDto>();
		for(Activity activity:activities) {
			Account account=accountStatementRepository.fetch(Account.class,activity.getAccountInv());
			String name=account.getAccountHolder().getName();
			AccountStatementResDto accStatRes=new AccountStatementResDto();
			accStatRes.setAccountNo(activity.getAccountInv());
			accStatRes.setAmount(activity.getAmount());
			accStatRes.setName(name);
			accStatRes.setType(activity.getType());
			accStatRes.setDate(activity.getDate());
			accStatRes.setTime(activity.getTime());
		    accStatResList.add(accStatRes);
		}
		return accStatResList;
	}
}
