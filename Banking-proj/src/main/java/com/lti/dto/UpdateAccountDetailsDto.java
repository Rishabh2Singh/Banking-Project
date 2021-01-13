package com.lti.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lti.entity.AccountHolderAdd;
import com.lti.entity.AccountHolderOccu;

public class UpdateAccountDetailsDto {
	private int cusId;
	private String name;
	private long mobile;
	private long aadharNumber;	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private AccountHolderAdd add1;
	private AccountHolderAdd add2;
	private AccountHolderOccu occupation;
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public AccountHolderAdd getAdd1() {
		return add1;
	}
	public void setAdd1(AccountHolderAdd add1) {
		this.add1 = add1;
	}
	public AccountHolderAdd getAdd2() {
		return add2;
	}
	public void setAdd2(AccountHolderAdd add2) {
		this.add2 = add2;
	}
	public AccountHolderOccu getOccupation() {
		return occupation;
	}
	public void setOccupation(AccountHolderOccu occupation) {
		this.occupation = occupation;
	}
	
	
}
