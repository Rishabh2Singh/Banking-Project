package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.UpdateAccountDetailsDto;
import com.lti.entity.Account;
import com.lti.entity.AccountHolder;
import com.lti.repository.AccountDetailsRepository;

@Service
@Transactional
public class UpdateAccountDetailsServiceImpl implements UpdateAccountDetailsService {

	@Autowired
	private AccountDetailsRepository accountDetailsRepository;

	public Status updateAccountDetails(UpdateAccountDetailsDto updateDetails) {
		int cusId = updateDetails.getCusId();
		Account account = accountDetailsRepository.fetchByCustomerId(cusId);
		int accountHolderId = account.getAccountHolder().getId();
		AccountHolder accountHolder = accountDetailsRepository.fetch(AccountHolder.class, accountHolderId);
		accountHolder.setMobile(updateDetails.getMobile());
		accountHolder.setAccountHolderAdd1(updateDetails.getAdd1());
		accountHolder.setAccountHolderAdd2(updateDetails.getAdd2());
		accountHolder.setAccountHolderOccu(updateDetails.getOccupation());
		AccountHolder accountHolder1 = (AccountHolder) accountDetailsRepository.save(accountHolder);
		 Status status=new Status();
		 status.setMessage("Your Details Have Succesfully Updated");
		 status.setStatus(StatusType.SUCCESS);
		 return status;
		//return accountHolder1;

	}

}
