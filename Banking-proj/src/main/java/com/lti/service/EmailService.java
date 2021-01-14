package com.lti.service;

import org.springframework.stereotype.Service;


public interface EmailService {

	public void sendEmail(String to, String subject, String message);
			
			
			
}
