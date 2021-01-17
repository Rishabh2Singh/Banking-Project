package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminInfo;
import com.lti.dto.AdminLogin;
import com.lti.dto.CustomerAccountDetails;
import com.lti.dto.CustomerDetails;
import com.lti.dto.InternetBankingApprovalDetails;
import com.lti.dto.InternetBankingDetails;
import com.lti.dto.Login;
import com.lti.entity.Account;
import com.lti.entity.Admin;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.UserLoginException;
import com.lti.service.AdminLoginService;
import com.lti.service.UserLoginService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminLoginService adminloginService;

	@PostMapping("/adminlogin")
	public @ResponseBody AdminInfo loginCheck(@RequestBody AdminLogin al)  {

		AdminInfo ad = null;
		try {
			System.out.println(" fetched values :" + al.getEmail() + "  " + al.getPassword());
			ad = adminloginService.adminLogin((String)al.getEmail(), (String)al.getPassword());
			ad.setStatus("success");
			return ad;
		} catch (UserLoginException | CustomerServiceException e) {
//			ad.setStatus(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/approveCustomer")
	public @ResponseBody void approveCustomer(@RequestBody String holderId)
	{
	
		adminloginService.approveCustomerByHolderId(Integer.valueOf(holderId));
		
	}
	
	@PostMapping("/rejectCustomer")
	public @ResponseBody void rejectCustomer(@RequestBody String holderId)
	{
		System.out.println();
		System.out.println();
		System.out.println("reject func called");
		adminloginService.rejectAcknowledgment(Integer.valueOf(holderId));
	}
	
	@PostMapping("/rejectInternetBanking")
	public @ResponseBody void rejectInternetBankingApplication(@RequestBody String holderId)
	{
		System.out.println("reject func called");
		adminloginService.internetBankingRejection(Integer.valueOf(holderId));
		//adminloginService.approveCustomerByHolderId();
	}

	
	@GetMapping("/customers")
	public @ResponseBody List<CustomerAccountDetails> getCustomers() {

		List<CustomerAccountDetails> ls = new ArrayList<>();
		
		try {
			ls = adminloginService.getCustomers();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Execption Occured while fetching Customers : " + e);
		}
		
		System.out.println("Fetched Customers :" + ls);
		
		return ls;
	}
	
	@GetMapping("/internetBanking")
	public @ResponseBody List<InternetBankingDetails> getApplications() {

		List<InternetBankingDetails> ls = new ArrayList<>();
		
		ls=adminloginService.getCustomerInternetBankingDetails();
		
		return ls;
	}
	
	
	@PostMapping("/internetBankingCustomerDetails")
	public @ResponseBody InternetBankingApprovalDetails getInternetBankingCustomerDetails1(@RequestBody long accno)
	{
		System.out.println("customerlist function Called with value : " + accno);

		InternetBankingApprovalDetails rs=adminloginService.getCustomerInternetBankingApprovalDetails(accno);
		return rs;
	}
	
	@PostMapping("/approveinternetbanking")
	public @ResponseBody int approveInternetBanking(@RequestBody long holderId)
	{
		int rs;
		System.out.println();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Value fetched approve########: "+holderId);
		rs=adminloginService.approveCustomerInternetBanking(holderId);
		//rs=adminloginService.approveCustomerByHolderId(Integer.valueOf(holderId));
		
		return 0;
	}

	@PostMapping("/accountOpeningRequest")
	public @ResponseBody List<CustomerDetails> getAccountOpeningRequestDetails(@RequestBody int holderId) {

		System.out.println("customerlist function Called with value : " + holderId);

		List<CustomerDetails> customerDetails = new ArrayList<>();

		customerDetails = adminloginService.getCustomerAndAccountDetails(holderId);
		
		return customerDetails;

		/*
		 * List<CustomerDetails> list = new ArrayList<>();
		 * 
		 * CustomerDetails cd1 = new CustomerDetails(); cd1.setCustomerName("Name1");
		 * cd1.setCustomerAddress("Address1"); cd1.setCustomerEmail("email1");
		 * cd1.setCustomerMobileNumber("mobile");
		 * cd1.setCustomerAadharNumber("aadhar1");
		 * 
		 * CustomerDetails cd2 = new CustomerDetails(); cd2.setCustomerName("Name2");
		 * cd2.setCustomerAddress("Address2"); cd2.setCustomerEmail("email2");
		 * cd2.setCustomerMobileNumber("mobile");
		 * cd2.setCustomerAadharNumber("aadhar2");
		 * 
		 * CustomerDetails cd3 = new CustomerDetails(); cd3.setCustomerName("Name3");
		 * cd3.setCustomerAddress("Address3"); cd3.setCustomerEmail("email3");
		 * cd3.setCustomerMobileNumber("mobile");
		 * cd3.setCustomerAadharNumber("aadhar3");
		 * 
		 * 
		 * CustomerDetails cd4 = new CustomerDetails(); cd4.setCustomerName("Name4");
		 * cd4.setCustomerAddress("Address4"); cd4.setCustomerEmail("email4");
		 * cd4.setCustomerMobileNumber("mobile");
		 * cd4.setCustomerAadharNumber("aadhar4");
		 * 
		 * list.add(cd1); list.add(cd2); list.add(cd3); list.add(cd4);
		 */

//		return list;
	}
}