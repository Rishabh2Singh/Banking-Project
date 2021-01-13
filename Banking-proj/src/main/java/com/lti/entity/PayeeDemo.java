package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_payee_demo")
public class PayeeDemo {

	@Id
	@SequenceGenerator(name="seq_pay", initialValue=101, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_pay")
	private int id;
	
	private String name;
	private long acno;
	private String type;
	private String nickname;
	private int customer;
	
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
	public long getAcno() {
		return acno;
	}
	public void setAcno(long acno) {
		this.acno = acno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCustomer() {
		return customer;
	}
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	
	
}
