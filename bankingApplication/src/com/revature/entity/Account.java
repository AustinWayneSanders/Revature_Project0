package com.revature.entity;

public class Account {
	private int accountNumber;
	private String userName;
	private String password;
	
	public Account() {
		super();
	}

	public Account(int accountNumber, String userName, String password) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.password = password;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
