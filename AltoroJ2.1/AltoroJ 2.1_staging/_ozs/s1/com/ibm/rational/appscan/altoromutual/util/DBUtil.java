/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.rational.appscan.altoromutual.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ibm.rational.appscan.altoromutual.model.Account;
import com.ibm.rational.appscan.altoromutual.model.Transaction;
import com.ibm.rational.appscan.altoromutual.model.User;
import com.ibm.rational.appscan.altoromutual.model.User.Role;

/**
 * Utility class for database operations
 * @author Alexei
 *
 */
public class DBUtil {

	private static final String PROTOCOL = "jdbc:derby:";
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
	private static DBUtil instance = null;
	private Connection connection = null;
	
	//private constructor
	private DBUtil(){
		/*
**
**			Default location for the database is current directory:
**			System.out.println(System.getProperty("user.dir"));
**			to change DB location, set derby.system.home property:
**			System.setProperty("derby.system.home", "[new_DB_location]");
**
		*/		
		try {
			//load JDBC driver
			Class.forName(DRIVER).newInstance(); 			
		} catch (Exception e) {
			// do nothing
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() throws SQLException{
		if (instance == null)
			instance = new DBUtil();
		
		if (instance.connection == null || instance.connection.isClosed()){
			try {
				//attempt to connect to the database
				instance.connection = DriverManager.getConnection(PROTOCOL+"altoro");
			} catch (SQLException e){
				//if database does not exist, create it an initialize it
				if (e.getErrorCode() == 40000){
					instance.initDB();
				//otherwise pass along the exception
				} else {
					throw e;
				}
			}
		}
		return instance.connection;	
	}
	
	/*
	 * Create and initialize the database
	 */
	private void initDB() throws SQLException{
		
		connection = DriverManager.getConnection(PROTOCOL+"altoro;create=true");
		Statement statement = connection.createStatement();
		
//		statement.execute("DROP TABLE PEOPLE");
//		statement.execute("DROP TABLE ACCOUNTS");
//		statement.execute("DROP TABLE TRANSACTIONS");

		statement.execute("CREATE TABLE PEOPLE (USER_ID VARCHAR(50) NOT NULL, PASSWORD VARCHAR(20) NOT NULL, FIRST_NAME VARCHAR(100) NOT NULL, LAST_NAME VARCHAR(100) NOT NULL, ROLE VARCHAR(50) NOT NULL, PRIMARY KEY (USER_ID))");
		statement.execute("CREATE TABLE ACCOUNTS (ACCOUNT_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10015539, INCREMENT BY 1), USERID VARCHAR(50) NOT NULL, ACCOUNT_NAME VARCHAR(100) NOT NULL, BALANCE DOUBLE NOT NULL, PRIMARY KEY (ACCOUNT_ID))");
		statement.execute("CREATE TABLE TRANSACTIONS (TRANSACTION_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1), ACCOUNTID INTEGER NOT NULL, DATE TIMESTAMP NOT NULL, TYPE VARCHAR(100) NOT NULL, AMOUNT DOUBLE NOT NULL, PRIMARY KEY (TRANSACTION_ID))");

		statement.execute("INSERT INTO PEOPLE (USER_ID,PASSWORD,FIRST_NAME,LAST_NAME,ROLE) VALUES ('admin', 'admin', 'Admin', 'User','admin'), ('jsmith','demo1234', 'John', 'Smith','user'),('sspeed','demo1234', 'Sam', 'Speed','user'),('tuser','tuser','Test', 'User','user')");
		statement.execute("INSERT INTO ACCOUNTS (USERID,ACCOUNT_NAME,BALANCE) VALUES ('admin','Corporate', 52394783.61), ('admin','Checking', 93820.44), ('jsmith','Savings', 10000.42), ('jsmith','Checking', 15000.39)");
	}

	/**
	 * Authenticate user
	 * @param user user name
	 * @param password password
	 * @return true if valid user, false otherwise
	 * @throws SQLException
	 */
	public static boolean isValidUser(String user, String password) throws SQLException{
		if (user == null || password == null || user.trim().length() == 0 || password.trim().length() == 0)
			return false; 
		
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		
		ResultSet resultSet =statement.executeQuery("SELECT COUNT(*)FROM PEOPLE WHERE USER_ID = '"+ user +"' AND PASSWORD='" + password + "'"); /* BAD - user input should always be sanitized */
		
		if (resultSet.next()){
			
				if (resultSet.getInt(1) > 0)
					return true;
		}
		return false;
	}
	

	/**
	 * Get user information
	 * @param username
	 * @return user information
	 * @throws SQLException
	 */
	public static User getUserInfo(String username) throws SQLException{
		if (username == null || username.trim().length() == 0)
			return null; 
		
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet =statement.executeQuery("SELECT FIRST_NAME,LAST_NAME,ROLE FROM PEOPLE WHERE USER_ID = '"+ username +"'"); /* BAD - user input should always be sanitized */

		String firstName = null;
		String lastName = null;
		String roleString = null;
		if (resultSet.next()){
			firstName = resultSet.getString("FIRST_NAME");
			lastName = resultSet.getString("LAST_NAME");
			roleString = resultSet.getString("ROLE");
		}
		
		if (firstName == null || lastName == null)
			return null;
		
		User user = new User(username, firstName, lastName);
		
		if (roleString.equalsIgnoreCase("admin"))
			user.setRole(Role.Admin);
		
		return user;
	}

	/**
	 * Get all accounts for the specified user
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public static Account[] getAccounts(String username) throws SQLException{
		if (username == null || username.trim().length() == 0)
			return null; 
		
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet =statement.executeQuery("SELECT ACCOUNT_ID, ACCOUNT_NAME, BALANCE FROM ACCOUNTS WHERE USERID = '"+ username +"'"); /* BAD - user input should always be sanitized */

		ArrayList<Account> accounts = new ArrayList<Account>(3);
		while (resultSet.next()){
			int accountId = resultSet.getInt("ACCOUNT_ID");
			String name = resultSet.getString("ACCOUNT_NAME");
			double balance = resultSet.getDouble("BALANCE"); 
			Account newAccount = new Account(accountId, name, balance);
			accounts.add(newAccount);
		}
		
		return accounts.toArray(new Account[accounts.size()]);
	}

	/**
	 * Transfer funds between specified accounts
	 * @param username
	 * @param creditActId
	 * @param debitActId
	 * @param amount
	 * @return
	 */
	public static String transferFunds(String username, int creditActId, int debitActId, double amount) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			//at the moment this query limits transfers to
			//transfers between two user accounts
			ResultSet resultSet =statement.executeQuery("SELECT ACCOUNT_ID, BALANCE FROM ACCOUNTS WHERE USERID = '"+ username +"'"); /* BAD - user input should always be sanitized */
	
			Account debitAccount = null;
			Account creditAccount = null;
			
			while (resultSet.next()){
				int accountId = resultSet.getInt("ACCOUNT_ID");
				double balance = resultSet.getDouble("BALANCE");
				
				if(accountId == creditActId)
					creditAccount = new Account(accountId, "", balance);
				else if(accountId == debitActId)
					debitAccount = new Account(accountId, "", balance);
			}
			
			if (debitAccount == null){
				return "Originating account is invalid";
			} 
			
			if (creditAccount == null)
				return "Destination account is invalid";
			
			java.sql.Timestamp date = new Timestamp(new java.util.Date().getTime());
			
			//in real life we would want to do these updates and transaction entry creation
			//as one atomic operation		
		
			//create transaction record
			statement.execute("INSERT INTO TRANSACTIONS (ACCOUNTID, DATE, TYPE, AMOUNT) VALUES ("+debitAccount.getAccountId()+",'"+date+"','Withdrawal',"+(-amount)+")," +
					  "("+creditAccount.getAccountId()+",'"+date+"','Deposit',"+amount+")"); 	

			//update account balances
			statement.execute("UPDATE ACCOUNTS SET BALANCE = " + (debitAccount.getBalance()-amount) + " WHERE ACCOUNT_ID = " + debitAccount.getAccountId());
			statement.execute("UPDATE ACCOUNTS SET BALANCE = " + (creditAccount.getBalance()+amount) + " WHERE ACCOUNT_ID = " + creditAccount.getAccountId());

			return null;
			
		} catch (SQLException e) {
			return "Transaction failed. Please try again later.";
		}

	}


	/**
	 * Get transaction information for the specified accounts in the date range (non-inclusive of the dates)
	 * @param startDate
	 * @param endDate
	 * @param accounts
	 * @return
	 */
	public static Transaction[] getTransactions(Date startDate, Date endDate, Account[] accounts) {
		
		if (accounts == null || accounts.length == 0)
			return null;
		try {
			Connection connection = getConnection();
			
			Timestamp startStamp = null;
			Timestamp endStamp = null; 
			
			if (startDate != null)
				startStamp = new Timestamp(startDate.getTime());
			
			if (endDate != null)
				endStamp = new Timestamp(endDate.getTime());

			Statement statement = connection.createStatement();
			StringBuffer acctIds = new StringBuffer();
			acctIds.append("ACCOUNTID = " + accounts[0].getAccountId());
			for (int i=1; i<accounts.length; i++){
				acctIds.append(" OR ACCOUNTID = "+accounts[i].getAccountId());	
			}
			
			String dateString = null;
			
			if (startDate != null && endDate != null){
				dateString = "DATE BETWEEN '" + startStamp + "' AND '" + endStamp + "'";
			} else if (startDate != null){
				dateString = "DATE > '" + startStamp +"'";
			} else if (endDate != null){
				dateString = "DATE < '" + endStamp + "'";
			}
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM TRANSACTIONS WHERE (" + acctIds.toString() + ") " + ((dateString==null)?"": "AND (" + dateString + ") ") + "ORDER BY DATE DESC" );
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while (resultSet.next()){
				int transId = resultSet.getInt("TRANSACTION_ID");
				int actId = resultSet.getInt("ACCOUNTID");
				Timestamp date = resultSet.getTimestamp("DATE");
				String desc = resultSet.getString("TYPE");
				double amount = resultSet.getDouble("AMOUNT");
				transactions.add(new Transaction(transId, actId, date, desc, amount));
			}
			
			return transactions.toArray(new Transaction[transactions.size()]); 
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[] getBankUsernames() {
		
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			//at the moment this query limits transfers to
			//transfers between two user accounts
			ResultSet resultSet =statement.executeQuery("SELECT USER_ID FROM PEOPLE"); 

			ArrayList<String> users = new ArrayList<String>();
			
			while (resultSet.next()){
				String name = resultSet.getString("USER_ID");
				users.add(name);
			}
			
			return users.toArray(new String[users.size()]);
		} catch (SQLException e){
			e.printStackTrace();
			return new String[0];
		}
	}

	public static String addAccount(String username, String acctType) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO ACCOUNTS (USERID,ACCOUNT_NAME,BALANCE) VALUES ('"+username+"','"+acctType+"', 0)");
			return null;
		} catch (SQLException e){
			return e.toString();
		}
	}

	public static String addUser(String username, String password, String firstname, String lastname) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO PEOPLE (USER_ID,PASSWORD,FIRST_NAME,LAST_NAME,ROLE) VALUES ('"+username+"','"+password+"', '"+firstname+"', '"+lastname+"','user')");
			return null;
		} catch (SQLException e){
			return e.toString();
			
		}
	}
	
	public static String changePassword(String username, String password) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("UPDATE PEOPLE SET PASSWORD = '"+ password +"' WHERE USER_ID = '"+username+"'");
			return null;
		} catch (SQLException e){
			return e.toString();
			
		}
	}
}