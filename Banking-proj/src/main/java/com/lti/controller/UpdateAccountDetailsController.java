package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.UpdateAccountDetailsDto;
import com.lti.service.UpdateAccountDetailsService;

@RestController
@CrossOrigin
public class UpdateAccountDetailsController {

	@Autowired
	private UpdateAccountDetailsService updateAccountDetailsService;
	
	@PostMapping("/updateDetails")
	public @ResponseBody Status updateAccountDetails(@RequestBody UpdateAccountDetailsDto updateDetails) {
		System.out.println("CusId"+updateDetails.getCusId());
		return  updateAccountDetailsService.updateAccountDetails(updateDetails);
	
	}
}
