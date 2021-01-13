package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_account")
public class Account {

	@Id
	@Column(name="account_no")
	@SequenceGenerator(name="seq_acc", initialValue=1001345104, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_acc")
	private long accountNo;
	
	private String type;
	private double balance;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="holder_id")
	private AccountHolder accountHolder;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private InternetBanking internetBanking;
	
	private int status;

	@JsonIgnore
	@OneToMany(mappedBy="account", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private List<Activity> activities;

	@JsonIgnore	
	@OneToMany(mappedBy="account",cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private List<ApprovalActivity> approvalActivites;

	@JsonIgnore
	@OneToMany(mappedBy="account", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private List<Beneficiary> beneficiaries;
	
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
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	public InternetBanking getInternetBanking() {
		return internetBanking;
	}
	public void setInternetBanking(InternetBanking internetBanking) {
		this.internetBanking = internetBanking;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public List<ApprovalActivity> getApprovalActivites() {
		return approvalActivites;
	}
	public void setApprovalActivites(List<ApprovalActivity> approvalActivites) {
		this.approvalActivites = approvalActivites;
	}
	
} 
