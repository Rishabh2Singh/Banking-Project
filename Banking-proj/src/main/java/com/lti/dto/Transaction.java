package com.lti.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

	private long fromAc;
	private String type;
	private long toAc;
	private LocalDate date;
	private LocalTime time;
	private double amt;
	private String remark;
	
	public long getFromAc() {
		return fromAc;
	}
	public void setFromAc(long fromAc) {
		this.fromAc = fromAc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getToAc() {
		return toAc;
	}
	public void setToAc(long toAc) {
		this.toAc = toAc;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amot) {
		this.amt = amot;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
