package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.Registration;
import com.lti.entity.Account;
import com.lti.entity.AccountHolder;
import com.lti.entity.AccountHolderAdd;
import com.lti.entity.AccountHolderOccu;
import com.lti.repository.UserRepository;

@Service
public class UserRegServiceImpl implements UserRegService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Transactional
	public AccountHolder register(Registration reg) {
		
		AccountHolder accHolder = new AccountHolder();
		accHolder.setName(reg.getName());
		accHolder.setFatherName(reg.getFatherName());
		accHolder.setMobile(reg.getMobile());
		accHolder.setAdharCard(reg.getAdharCard());
		accHolder.setEmail(reg.getEmail());
		accHolder.setDob(reg.getDob());
		accHolder.setDebitCard(reg.getDebitCard());
		accHolder.setCreditCard(reg.getCreditCard());
		
		AccountHolderAdd accHolderAdd = new AccountHolderAdd();
		accHolderAdd.setAddress(reg.getAddress());
		accHolderAdd.setLandmark(reg.getLandmark());
		accHolderAdd.setCity(reg.getCity());
		accHolderAdd.setState(reg.getState());
		accHolderAdd.setPincode(reg.getPincode());
		
		AccountHolderAdd fetAdd = (AccountHolderAdd) userRepository.save(accHolderAdd);
		
		
		
		AccountHolderOccu accHolderOccu = new AccountHolderOccu();
		accHolderOccu.setType(reg.getType());
		accHolderOccu.setIncome_source(reg.getIncome_source());
		accHolderOccu.setAnnual_income(reg.getAnnual_income());
		
		AccountHolderOccu fetOccu = (AccountHolderOccu) userRepository.save(accHolderOccu);
		
		accHolder.setAccountHolderAdd1(fetAdd);
		accHolder.setAccountHolderAdd2(fetAdd);
		accHolder.setAccountHolderOccu(fetOccu);
		
		AccountHolder fetHol = (AccountHolder) userRepository.save(accHolder);
		
		Account accReg = new Account();
		accReg.setBalance(reg.getAmount());
		accReg.setType(reg.getAccType());
		accReg.setAccountHolder(fetHol);
		
		Account fetAcc = (Account) userRepository.save(accReg);
		
		System.out.println(fetAcc.getAccountNo());
		
		String subject = "Account Registration Details";
		String message ="Your A/C number is: "+fetAcc.getAccountNo();
		emailService.sendEmail(reg.getEmail(),subject , message);
		
		return fetHol;
		
		
	}

	
	

}
