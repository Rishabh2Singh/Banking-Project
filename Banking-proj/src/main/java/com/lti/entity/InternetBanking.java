package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="internet_banking")
public class InternetBanking {

	@Id
	@Column(name="customer_id")
	@SequenceGenerator(name="seq_ib", initialValue=1356786292, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_ib")
	private int customerId;
	
	private String loginPass;
	private String transPass;
	private int status;
	
	@JsonIgnore
	@OneToOne(mappedBy="internetBanking", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private Account account;

	@JsonIgnore
	@OneToMany(mappedBy="customer", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private List<Beneficiary> beneficiaries;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getTransPass() {
		return transPass;
	}
	public void setTransPass(String transPass) {
		this.transPass = transPass;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
//	public Account getAccount() {
//		return account;
//	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
