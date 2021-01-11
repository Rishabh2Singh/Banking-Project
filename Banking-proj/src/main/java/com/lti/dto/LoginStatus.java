package com.lti.dto;

public class LoginStatus extends Status {

	//in case we want to return all the details of Customer on login
	//then declare Customer object here-> private Customer customer; 
	private int customerId;
	private String password;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
