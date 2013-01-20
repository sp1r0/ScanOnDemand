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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.rational.appscan.altoromutual.model.User;
import com.ibm.rational.appscan.altoromutual.util.DBUtil;
import com.ibm.rational.appscan.altoromutual.util.ServletUtil;

/**
 * This servlet allows to transfer funds between existing accounts
 * Servlet implementation class TransverServlet
 * 
 * @author Alexei
 */
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int creditActId = 0;
		int debitActId = 0;
		double amount = 0;
		
		try {
			creditActId = Integer.parseInt(request.getParameter("creditAccount"));
			debitActId = Integer.parseInt(request.getParameter("debitAccount"));
			amount = Double.valueOf(request.getParameter("transferAmount"));
		} catch (Exception e){
			//do nothing
		}
		
		
		//we will not send an error immediately, but we need to have an indication when one occurs...

		String message = null;
		if (creditActId < 0){
			message = "Destination account is invalid";
		} else if (debitActId < 0) {
			message = "Originating account is invalid";
		} else if (amount < 0){
			message = "Transfer amount is invalid";
		}
		
		String userName = null;
		try {
			User user = (User)request.getSession().getAttribute(ServletUtil.SESSION_ATTR_USER);
			userName = user.getUsername();
		} catch (Exception e) {
			response.sendRedirect("login.jsp");
			return ;
		}
		
		//if transfer amount is zero then there is nothing to do
		if (message == null && amount > 0){
			//Notice that available balance is not checked
			message = DBUtil.transferFunds(userName, creditActId, debitActId, amount);
		}
		
		if (message != null){
			message = "ERROR: " + message;
		} else {
			message = "$" + amount + " was successfully transferred from Account " + debitActId + " into Account " + creditActId + " at " + new SimpleDateFormat().format(new Date()) + ".";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("transfer.jsp");
		request.setAttribute("message", message);
		dispatcher.forward(request, response);
		
	}

}
