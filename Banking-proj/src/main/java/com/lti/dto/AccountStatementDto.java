package com.lti.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountStatementDto {

	private int customerId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDate;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
}
