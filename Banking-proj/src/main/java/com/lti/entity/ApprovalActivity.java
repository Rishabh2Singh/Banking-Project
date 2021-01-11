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

@Entity
@Table(name="approval_activity")
public class ApprovalActivity {

	@Id
	@SequenceGenerator(name="seq_appact", initialValue=401, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_appact")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="account_no")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private InternetBanking customer;
	
	private String type;
	private String approval;
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admin admin;
	
	@Column(name="check_date")
	private LocalDate date;
	
	private LocalTime time;
	
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
	public InternetBanking getCustomer() {
		return customer;
	}
	public void setCustomer(InternetBanking customer) {
		this.customer = customer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
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

}
