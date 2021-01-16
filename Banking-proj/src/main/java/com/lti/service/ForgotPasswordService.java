package com.lti.service;

import com.lti.dto.ForgotPasswordDto;

public interface ForgotPasswordService {

	public ForgotPasswordDto checkCusId(int custId);
	public ForgotPasswordDto updateNewPassword(int custId,String pwd);
}
