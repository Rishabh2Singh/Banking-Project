package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.NetBanking;
import com.lti.entity.Account;
import com.lti.entity.InternetBanking;
import com.lti.repository.NetBankingRepository;

@Service
public class NetBankingServiceImpl implements NetBankingService {

	@Autowired
	private NetBankingRepository netBankingRepository;
	
	@Transactional
	public void enableNetBanking(NetBanking net) throws Exception {
		
		long accNo = net.getAccountNo();
		Account accountObj = netBankingRepository.fetch(Account.class, net.getAccountNo());
		//System.out.println(accountObj.getAccountNo());
		InternetBanking intBank = new InternetBanking();
		
		
		if(accountObj == null)
		{
			//throw new Exception("Invalid AccountNumber!");
			System.out.println("Invalid AccountNumber!");
		}
		else if(accountObj.getStatus() != 1)
		{
			//throw new Exception("Account not activated!");
			System.out.println("Account not activated!");
		}
		else if(accountObj.getStatus() == 1 )
		{
			intBank.setLoginPass(net.getLoginPass());
			intBank.setTransPass(net.getTransPass());
			//intBank.setCustomerId(123);
			
			InternetBanking fetIb = (InternetBanking) netBankingRepository.save(intBank);
			accountObj.setInternetBanking(fetIb);
		}
		
		
		
	}

}
