package com.revature.entity;

import java.sql.Date;

public class Transactions {

	private int transactionId;
	private String transType;
	private float interestRate;
	private Date transTimeStamp;
	
	
	public Transactions() {
		super();
	}


	public Transactions(int transactionId, String transType, float interestRate, Date transTimeStamp) {
		super();
		this.transactionId = transactionId;
		this.transType = transType;
		this.interestRate = interestRate;
		this.transTimeStamp = transTimeStamp;
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


	public float getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}


	public Date getTransTimeStamp() {
		return transTimeStamp;
	}


	public void setTransTimeStamp(Date transTimeStamp) {
		this.transTimeStamp = transTimeStamp;
	}


	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", transType=" + transType + ", interestRate="
				+ interestRate + ", transTimeStamp=" + transTimeStamp + "]";
	}

	
	
}
