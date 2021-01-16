package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.UpdateAccountDetailsDto;
import com.lti.entity.Account;
import com.lti.entity.AccountHolder;
import com.lti.entity.AccountHolderAdd;
import com.lti.entity.AccountHolderOccu;
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
		
		AccountHolderAdd ad1=accountDetailsRepository.fetch(AccountHolderAdd.class,updateDetails.getAdd1().getId());
		ad1.setAddress(updateDetails.getAdd1().getAddress());
		ad1.setLandmark(updateDetails.getAdd1().getLandmark());
		ad1.setCity(updateDetails.getAdd1().getCity());
		ad1.setState(updateDetails.getAdd1().getState());
		ad1.setPincode(updateDetails.getAdd1().getPincode());
		accountDetailsRepository.save(ad1);
		
		AccountHolderAdd ad2=accountDetailsRepository.fetch(AccountHolderAdd.class,updateDetails.getAdd2().getId());
		ad2.setAddress(updateDetails.getAdd2().getAddress());
		ad2.setLandmark(updateDetails.getAdd2().getLandmark());
		ad2.setCity(updateDetails.getAdd2().getCity());
		ad2.setState(updateDetails.getAdd2().getState());
		ad2.setPincode(updateDetails.getAdd2().getPincode());
		accountDetailsRepository.save(ad2);
		
		AccountHolderOccu occu=accountDetailsRepository.fetch(AccountHolderOccu.class,updateDetails.getOccupation().getId());
		occu.setType(updateDetails.getOccupation().getType());
		occu.setAnnual_income(updateDetails.getOccupation().getAnnual_income());
		occu.setIncome_source(updateDetails.getOccupation().getIncome_source());
		accountDetailsRepository.save(occu);
		
		accountHolder.setAccountHolderAdd1(ad1);
		accountHolder.setAccountHolderAdd2(ad2);
		accountHolder.setAccountHolderOccu(occu);
		
		accountDetailsRepository.save(accountHolder);
		
		 Status status=new Status();
		 status.setMessage("Your Details Have Succesfully Updated");
		 status.setStatus(StatusType.SUCCESS);
		 return status;
	}

}