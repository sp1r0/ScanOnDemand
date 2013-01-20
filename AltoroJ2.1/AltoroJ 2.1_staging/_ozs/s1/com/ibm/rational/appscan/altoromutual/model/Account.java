/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.rational.appscan.altoromutual.model;

/**
 * This class models user's account
 * @author Alexei
 */
public class Account {
	private int accountId = -1;
	private String accountName = null;
	private double balance = -1;
	
	public Account(int accountId, String accountName, double balance) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.balance = balance;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getAccountName() {
		return accountName;
	}

}
