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
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This servlet allows the users to view account and transaction information.
 * Servlet implementation class AccountServlet
 * @author Alexei
 *
 */
public class AccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//show account balance for a particular account
		if (request.getRequestURL().toString().endsWith("showAccount")){
			String accountName = request.getParameter("listAccounts");
			if (accountName == null){
				response.sendRedirect(request.getContextPath()+"/bank/main.jsp");
				return;
			}
//			response.sendRedirect("/bank/balance.jsp&acctId=" + accountName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bank/balance.jsp?acctId=" + accountName);
			dispatcher.forward(request, response);
			return;
		}
		//this shouldn't happen
		else if (request.getRequestURL().toString().endsWith("showTransactions"))
			doPost(request,response);
		else
			super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//show transactions within the specified date range (if any)
		if (request.getRequestURL().toString().endsWith("showTransactions")){
			long startTime = 0;
			long endTime = 0;
			
			startTime = parseDateString(request.getParameter("startDate"), true);
			endTime = parseDateString(request.getParameter("endDate"), false);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bank/transaction.jsp?" + ((startTime!=0)?"&startTime="+startTime:"") + ((endTime!=0)?"&endTime="+endTime:""));
			dispatcher.forward(request, response);
		}
	}

	/*
	 * Parse date string (MMDDYYYY) and return corresponding time since epoch in milliseconds
	 */
	private long parseDateString(String dateString, boolean isBefore){
		try {
			StringTokenizer tokenizer = new StringTokenizer(dateString.trim(), "/");
			
			int year = 0;
			int month = 0;
			int day = 0;
			if (tokenizer.hasMoreTokens())
				month = Integer.parseInt(tokenizer.nextToken());
			if (tokenizer.hasMoreTokens())
				day = Integer.parseInt(tokenizer.nextToken());
			if (tokenizer.hasMoreTokens())
				year = Integer.parseInt(tokenizer.nextToken());
			
			if (month == 0 || day == 0 || year == 0)
				throw new Exception();
			
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			
			if (isBefore)			
				calendar.set(year, month-1, day+1, 0, 0, 0);
			else
				calendar.set(year, month-1, day-1, 23, 59, 59);
			return calendar.getTimeInMillis();
		} catch (Exception e){
			return 0;
		}
	}
}
