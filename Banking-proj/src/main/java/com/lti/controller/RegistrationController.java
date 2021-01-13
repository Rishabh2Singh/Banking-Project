package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Registration;
import com.lti.entity.AccountHolder;
import com.lti.entity.AccountHolderAdd;
import com.lti.entity.AccountHolderOccu;
import com.lti.service.UserRegService;

@RestController
@CrossOrigin
public class RegistrationController {

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private UserRegService userServ;
	
	@PostMapping("/newuser")
	public int addUser(@RequestBody Registration reg) {
		
		AccountHolder accHol =  userServ.register(reg);
		
		return accHol.getId();
	}
	
//	@PostMapping("/insertadd")
//	public void insertAddress(@RequestBody AccountHolderAdd add ) {
//		userRepository.save(add);
//	}
}
