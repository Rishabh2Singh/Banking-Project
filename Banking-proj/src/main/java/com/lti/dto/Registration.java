package com.lti.dto;

import java.time.LocalDate;

public class Registration {


	private String name;
	private String fatherName;
	private long mobile;
	private String email;
	private long adharCard;
	private LocalDate dob;
	private int debitCard;
	private int creditCard;
	
	private String address;
	private String landmark;
	private String city;
	private String state;
	private int pincode;
	
	private String type;
	private String income_source;
	private long annual_income;
	
	
	private int amount;
	private String accType;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public int getDebitCard() {
		return debitCard;
	}
	public void setDebitCard(int debitCard) {
		this.debitCard = debitCard;
	}
	public int getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(int creditCard) {
		this.creditCard = creditCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIncome_source() {
		return income_source;
	}
	public void setIncome_source(String income_source) {
		this.income_source = income_source;
	}
	public long getAnnual_income() {
		return annual_income;
	}
	public void setAnnual_income(long annual_income) {
		this.annual_income = annual_income;
	}
	
	public Registration() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
