package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_accountHolderOccupation")
public class AccountHolderOccu {

	@Id
	@SequenceGenerator(name="seq_occu", initialValue=205, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_occu")
	//@GeneratedValue
	private int id;
	
	private String type;
	private String income_source;
	private long annual_income;
	
	@OneToOne(mappedBy="accountHolderOccu", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private AccountHolder accountHolder;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	@Override
	public String toString() {
		return "AccountHolderOccu [id=" + id + ", type=" + type + ", income_source=" + income_source
				+ ", annual_income=" + annual_income + ", accountHolder=" + accountHolder + "]";
	}
	
	
	
}
