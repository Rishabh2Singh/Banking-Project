package com.lti.dto;

public class NetBanking {

	
	private String loginPass;
	private String transPass;
	private int status;

	private long accountNo;

	

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

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	
	public NetBanking() {
		// TODO Auto-generated constructor stub
	}
}
