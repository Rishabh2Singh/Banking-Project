package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="admin_login")
public class Admin {

	@Id
	@SequenceGenerator(name="seq_adm", initialValue=101, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_adm")
	private int id;
	
	private String name;
	private String password;
	private String email;
	private long contact;
	private String designation;
	
	@OneToMany(mappedBy="admin", cascade= { CascadeType.PERSIST, CascadeType.MERGE})
	private List<ApprovalActivity> activities;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<ApprovalActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<ApprovalActivity> activities) {
		this.activities = activities;
	}
	
}
