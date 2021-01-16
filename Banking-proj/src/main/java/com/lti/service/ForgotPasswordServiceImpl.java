package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.ForgotPasswordDto;
import com.lti.entity.Account;
import com.lti.repository.ForgotPasswordRepository;

@Service
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private ForgotPasswordRepository forgotPasswordRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public ForgotPasswordDto checkCusId(int custId) {

		ForgotPasswordDto forgotPwd = new ForgotPasswordDto();

		if (forgotPasswordRepo.checkUserByCustId(custId)) {
			Account account = forgotPasswordRepo.fetchAccount(custId);
			String email = account.getAccountHolder().getEmail();
			String subject = "Link to Reset Password";
			String message = "Click th link below to reset password \n" + "http://localhost:4200/forgotpassword?cid="+
			account.getInternetBanking().getCustomerId();
			emailService.sendEmail("rishsingh538@gmail.com", subject, message);
			forgotPwd.setStatusMsg(account.getAccountHolder().getName()+" Kindly check ur registered mail"
					+ " to reset password!!");
			return forgotPwd;

		} else {
			forgotPwd.setStatusMsg("No User Found!!! Please check ur CustomerId");
			return forgotPwd;
		}
	}

	@Override
	public ForgotPasswordDto updateNewPassword(int custId, String pwd) {

		ForgotPasswordDto forgotPwd = new ForgotPasswordDto();
		
		if (forgotPasswordRepo.checkUserByCustId(custId)) {
			forgotPasswordRepo.updatePassword(custId, pwd);
			forgotPwd.setStatusMsg("Your Password is updated!!");
			return forgotPwd;
		}
		else
		{
			forgotPwd.setStatusMsg("Pls Enter Valid Customer Id");
			return forgotPwd;
		}
	}
	
}
