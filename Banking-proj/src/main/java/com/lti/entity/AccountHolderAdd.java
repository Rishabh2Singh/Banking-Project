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
@Table(name="tbl_accountHolderAdd")
public class AccountHolderAdd {

	@Id
	@SequenceGenerator(name="seq_add", initialValue=306, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_add")
	//@GeneratedValue
	private int id;
	
	private String address;
	private String landmark;
	private String city;
	private String state;
	private int pincode;
	
	@OneToOne(mappedBy="accountHolderAdd1", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private AccountHolder accountholdobj1;
	
	@OneToOne(mappedBy="accountHolderAdd2", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private AccountHolder accountholdobj2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public AccountHolder getAccountholdobj1() {
		return accountholdobj1;
	}
	public void setAccountholdobj1(AccountHolder accountholdobj1) {
		this.accountholdobj1 = accountholdobj1;
	}
	public AccountHolder getAccountholdobj2() {
		return accountholdobj2;
	}
	public void setAccountholdobj2(AccountHolder accountholdobj2) {
		this.accountholdobj2 = accountholdobj2;
	}
	@Override
	public String toString() {
		return "AccountHolderAdd [id=" + id + ", address=" + address + ", landmark=" + landmark + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + ", accountholdobj1=" + accountholdobj1
				+ ", accountholdobj2=" + accountholdobj2 + "]";
	}
	
	
	
	
}
