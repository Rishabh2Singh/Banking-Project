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

@Service 
@Transactional
public class AccountStatementServiceImpl implements AccountStatementService {

	@Autowired
	private AccountStatementRepository accountStatementRepository;
	
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
			accStatRes.setType(activity.getType());
		    accStatResList.add(accStatRes);
		}
		
		return accStatResList;
	}
}
