package com.lti.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="activity")
public class Activity {

	@Id
	@SequenceGenerator(name="seq_act", initialValue=501, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_act")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="account_no")
	private Account account;
	
	private String type;
	
	@Column(name="account_inv")
	private long accountInv;
	
	@Column(name="act_date")
	private LocalDate date;
	
	private LocalTime time;
	private double amount;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getAccountInv() {
		return accountInv;
	}
	public void setAccountInv(long accountInv) {
		this.accountInv = accountInv;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
