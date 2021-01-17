package com.lti.dto;

public class InternetBankingApprovalDetails {
	
	private long accountNo;
	private String type;
	private double balance;
	private String name;
	private long adharCard;
	private int debitCard;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(long adharCard) {
		this.adharCard = adharCard;
	}

	public int getDebitCard() {
		return debitCard;
	}

	public void setDebitCard(int debitCard) {
		this.debitCard = debitCard;
	}
}