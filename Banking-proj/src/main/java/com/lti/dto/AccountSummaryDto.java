package com.lti.dto;

import java.util.List;

import com.lti.entity.Activity;

public class AccountSummaryDto {

	private long accountNo;
	private String name;
	private String email;
	private long adharCard;
	private String type;
	private double balance;
	private List<Activity> activity;
	
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAdharCard() {
		return adharCard;
	}
	public void setAdharCard(long adharCard) {
		this.adharCard = adharCard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<Activity> getActivity() {
		return activity;
	}
	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}
}
