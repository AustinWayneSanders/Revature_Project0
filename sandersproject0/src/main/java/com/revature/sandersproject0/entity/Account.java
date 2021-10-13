package com.revature.sandersproject0.entity;

//Entity class representing the account table in MySQL

public class Account {
	private int accountNumber;
	private int accountTypeID;
	private float balance;
	private int clientID;
	
	public Account() {
		super();
	}

	public Account(int accountNumber, int accountTypeID, float balance, int clientID) {
		super();
		this.accountNumber = accountNumber;
		this.accountTypeID = accountTypeID;
		this.balance = balance;
		this.clientID = clientID;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountType() {
		return accountTypeID;
	}

	public void setAccountType(int accountTypeID) {
		this.accountTypeID = accountTypeID;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountTypeID + ", balance=" + balance
				+ ", clientID=" + clientID + "]";
	}

	
}
