package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Payee;
import com.lti.dto.Payees;
import com.lti.dto.Status.StatusType;
import com.lti.dto.Transaction;
import com.lti.entity.Account;
import com.lti.entity.Beneficiary;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.service.UserLoginService;

@RestController
@CrossOrigin
public class BankController {

	@Autowired
	private UserLoginService loginService;
	
	@PostMapping("/testing")
	public @ResponseBody String printMessage(@RequestParam String message) {
		
		message=message+" controller";
		return message;
		
	}
	@PostMapping("/login")
	public @ResponseBody LoginStatus loginCheck(@RequestBody Login login) {
		
		Account acc = null;
		
			try {
				acc = loginService.userLogin(login.getCustomerId(), login.getPassword());
				LoginStatus status=new LoginStatus();
				status.setAccount(acc);
				status.setMessage("Login Successfully");
				return status;
			} catch (UserLoginException | CustomerServiceException e) {
				LoginStatus status=new LoginStatus();
				status.setAccount(null);
				status.setMessage(e.getMessage());
				System.out.println(e.getMessage());
				return status;
			}
			
		
//		return acc;
	}
	
	@GetMapping("/transfer")
	public @ResponseBody List<Payee> fetchingBeneficary(@RequestParam int custId){
		System.out.println(custId);
		List<Payee> payees=loginService.fetchBeneficiary(custId);
		return payees;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody int add(@RequestBody Payee payee) {
//		int customerId=1356786291;
		System.out.println("Into the adding payee");
		System.out.println(payee.getCustId());
		System.out.println(payee.getAcno());
		int payeeId=loginService.addPayee(payee);
		return payeeId;
	}
	@RequestMapping(value="/getAcc", method=RequestMethod.GET)
	public @ResponseBody long fetchAcc(@RequestParam(name="custId") int custId) {
//		System.out.println(custId);
		long acno=loginService.fetchAccountNo(custId);
		System.out.println(acno);
		return acno;
	}
	@RequestMapping(value="/transaction", method=RequestMethod.POST)
	public @ResponseBody int addTransaction(@RequestBody Transaction transaction) {
		System.out.println(transaction.getFromAc());
		System.out.println(transaction.getToAc());
		System.out.println(transaction.getAmt());
		System.out.println(transaction.getType());
		System.out.println(transaction.getRemark());
		int id=loginService.addActivity(transaction);
		return id;
	}
}
