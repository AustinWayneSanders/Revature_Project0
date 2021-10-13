package com.revature.sandersBankingExceptions.entity;

//Entity class representing the accounttype table in MySQL, used to pass data as objects between MySQL and Java. 

public class AccountType {
	private int accountTypeID;
	private String accountName;
	private float interstRate;
	
	public AccountType() {
		super();
	}

	public AccountType(int accountTypeID, String accountName, float interstRate) {
		super();
		this.accountTypeID = accountTypeID;
		this.accountName = accountName;
		this.interstRate = interstRate;
	}

	public int getAccountTypeID() {
		return accountTypeID;
	}

	public void setAccountTypeID(int accountTypeID) {
		this.accountTypeID = accountTypeID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public float getInterstRate() {
		return interstRate;
	}

	public void setInterstRate(float interstRate) {
		this.interstRate = interstRate;
	}

	@Override
	public String toString() {
		return "AccountType [accountTypeID=" + accountTypeID + ", accountName=" + accountName + ", interstRate="
				+ interstRate + "]";
	}
	
}
