/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.rational.appscan.altoromutual.model;

import java.sql.SQLException;
import java.util.Date;

import com.ibm.rational.appscan.altoromutual.util.DBUtil;

/**
 * This class models a user
 * @author Alexei
 *
 */
public class User implements java.io.Serializable{

	private static final long serialVersionUID = -4566649173574593144L;
	
	public static enum Role{User, Admin};
	
	private String username, firstName, lastName;
	private Role role = Role.User;
	
	private Date lastAccessDate = null;
	
	public User(String username, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		lastAccessDate = new Date();
	}
	
	public void setRole(Role role){
		this.role = role;
	}
	
	public Role getRole(){
		return role;
	}
	
	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Account[] getAccounts(){
		try {
			return DBUtil.getAccounts(username);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Transaction[] getUserTransactions(Date startDate, Date endDate, Account[] accounts){
		return DBUtil.getTransactions(startDate, endDate, accounts);
	}
}
