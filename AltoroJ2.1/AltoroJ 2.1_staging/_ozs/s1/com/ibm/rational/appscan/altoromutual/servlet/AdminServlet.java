/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.rational.appscan.altoromutual.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.rational.appscan.altoromutual.util.DBUtil;

/**
 * This servlet handles site admin operations
 * @author Alexei
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		
		//add account
		if (request.getRequestURL().toString().endsWith("addAccount")){
			String username = request.getParameter("username");
			String acctType = request.getParameter("accttypes");
			if (username == null || acctType == null || username.trim().length() == 0 || acctType.trim().length() == 0)
				message = "An error has occurred. Please try again later.";
			else {
				String error = DBUtil.addAccount(username, acctType);
				if (error != null)
					message = error;
			}
		}
		
		//add user
		else if (request.getRequestURL().toString().endsWith("addUser")){
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			if (username == null || username.trim().length() == 0
				|| password1 == null || password1.trim().length() == 0
				|| password2 == null || password2.trim().length() == 0)
				message = "An error has occurred. Please try again later.";
			
			if (firstname == null){
				firstname = "";
			}
			
			if (lastname == null){
				lastname = "";
			}
			
			if (message == null && !password1.equals(password2)){
				message = "Entered passwords did not match.";
			}
			
			if (message == null){
				String error = DBUtil.addUser(username, password1, firstname, lastname);
				
				if (error != null)
					message = error;
			}
			
		}
		
		//change password
		else if (request.getRequestURL().toString().endsWith("changePassword")){
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			if (username == null || username.trim().length() == 0
					|| password1 == null || password1.trim().length() == 0
					|| password2 == null || password2.trim().length() == 0)
					message = "An error has occurred. Please try again later.";
			
			if (message == null && !password1.equals(password2)){
				message = "Entered passwords did not match.";
			}
			
			if (message == null) {
				String error = DBUtil.changePassword(username, password1);
				
				if (error != null)
					message = error;
			}
		}
		else {
			message = "An error has occurred. Please try again later.";	
		}
		
		if (message != null)
			message = "Error: " + message;
		else
			message = "Requested operation has completed successfully.";
		
		request.getSession().setAttribute("message", message);
		response.sendRedirect("admin.jsp");
		return ;
	}

}
