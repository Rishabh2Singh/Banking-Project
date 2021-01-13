package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_accountHolder")
public class AccountHolder {

	@Id
	@SequenceGenerator(name="seq_hold", initialValue=1007, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_hold")
	//@GeneratedValue
	private int id;
	
	private String name;
	
	@Column(name="father_name")
	private String fatherName;
	
	private long mobile;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="adhar_card")
	private long adharCard;
	
	private LocalDate dob;

	@Column(name="debit_card")
	private int debitCard;

	@Column(name="credit_card")
	private int creditCard;
	

	@OneToOne()
	@JoinColumn(name="res_id")
	private AccountHolderAdd accountHolderAdd1;
	
	@OneToOne()
	@JoinColumn(name="mail_Add")
	private AccountHolderAdd accountHolderAdd2;
	
	@OneToOne()
	@JoinColumn(name="occu_id")
	private AccountHolderOccu accountHolderOccu;
	
	@JsonIgnore
	@OneToOne(mappedBy="accountHolder", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private Account account;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public AccountHolderAdd getAccountHolderAdd1() {
		return accountHolderAdd1;
	}

	public void setAccountHolderAdd1(AccountHolderAdd accountHolderAdd1) {
		this.accountHolderAdd1 = accountHolderAdd1;
	}

	public AccountHolderAdd getAccountHolderAdd2() {
		return accountHolderAdd2;
	}

	public void setAccountHolderAdd2(AccountHolderAdd accountHolderAdd2) {
		this.accountHolderAdd2 = accountHolderAdd2;
	}

	public AccountHolderOccu getAccountHolderOccu() {
		return accountHolderOccu;
	}

	public void setAccountHolderOccu(AccountHolderOccu accountHolderOccu) {
		this.accountHolderOccu = accountHolderOccu;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "AccountHolder [id=" + id + ", name=" + name + ", fatherName=" + fatherName + ", mobile=" + mobile
				+ ", email=" + email + ", adharCard=" + adharCard + ", dob=" + dob + ", debitCard=" + debitCard
				+ ", creditCard=" + creditCard + ", accountHolderAdd1=" + accountHolderAdd1 + ", accountHolderAdd2="
				+ accountHolderAdd2 + ", accountHolderOccu=" + accountHolderOccu + ", account=" + account + "]";
	}
	
	
	
}
