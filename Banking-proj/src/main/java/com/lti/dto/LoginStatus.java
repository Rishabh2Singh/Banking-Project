package com.lti.dto;

import com.lti.entity.Account;

public class LoginStatus {

	private Account account;
	private String message;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
