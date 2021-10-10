package com.revature.entity;

import java.sql.Date;

public class Transactions {

	private int transactionId;
	private int accountNumber;
	private String transType;
	private float amount;
	private Date transTimeStamp;
	
	
	public Transactions() {
		super();
	}


	public Transactions(int transactionId, int accountNumber, String transType, float amount, Date transTimeStamp) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.transType = transType;
		this.amount = amount;
		this.transTimeStamp = transTimeStamp;
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public String getTransType() {
		return transType;
	}


	public void setTransType(String transType) {
		this.transType = transType;
	}


	public Date getTransTimeStamp() {
		return transTimeStamp;
	}


	public void setTransTimeStamp(Date transTimeStamp) {
		this.transTimeStamp = transTimeStamp;
	}


	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", accountNumber=" + accountNumber + ", transType="
				+ transType + ", amount=" + amount + ", transTimeStamp=" + transTimeStamp + "]";
	}

	
	
}
