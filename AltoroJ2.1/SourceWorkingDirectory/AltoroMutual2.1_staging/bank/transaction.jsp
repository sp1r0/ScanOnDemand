<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/header.jspf"/>

<div id="wrapper" style="width: 99%;">
	<jsp:include page="membertoc.jspf"/>
    <td valign="top" colspan="3" class="bb">
    
		<%@page import="java.util.Date"%>
		<%@page import="com.ibm.rational.appscan.altoromutual.model.Transaction"%>
		
		<div class="fl" style="width: 99%;">
		
		<%
		com.ibm.rational.appscan.altoromutual.model.User user = (com.ibm.rational.appscan.altoromutual.model.User)request.getSession().getAttribute("user");
		String startString = request.getParameter("startTime");
		String endString = request.getParameter("endTime");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new Date(Long.valueOf(startString));
		} catch (Exception e){
			//do nothing
		}
		
		try {
			endDate = new Date(Long.valueOf(endString));
		} catch (Exception e){
			//do nothing
		}
		
		Transaction[] transactions = user.getUserTransactions(startDate, endDate, user.getAccounts());
		%>
		
		<h1>Recent Transactions</h1>
		
		<script type="text/javascript">
			function confirminput(myform) {
			
				if (myform.startDate.value != ""){
					var splitStrings = myform.startDate.value.split("/");
					var month = parseInt((splitStrings[0].charAt(0)==0 && splitStrings[0].length == 2)?splitStrings[0].charAt(1):splitStrings[0]);
					var day = parseInt((splitStrings[1].charAt(0)==0 && splitStrings[1].length == 2)?splitStrings[1].charAt(1):splitStrings[1]);
					var year = parseInt(splitStrings[2]);
					
					var valid = validateDate(month, day, year);
					if (!valid){
						alert ("'After' date of " + myform.startDate.value + " is not valid.");
						return false;
					}
				}
			
				if (myform.endDate.value != ""){
					var splitStrings2 = myform.endDate.value.split("/");
					var month2 = parseInt((splitStrings2[0].charAt(0)==0 && splitStrings2[0].length == 2)?splitStrings2[0].charAt(1):splitStrings2[0]);
					var day2 = parseInt((splitStrings2[1].charAt(0)==0 && splitStrings2[1].length == 2)?splitStrings2[1].charAt(1):splitStrings2[1]);
					var year2 = parseInt(splitStrings2[2]);
			
					var valid2 = validateDate(month2, day2, year2);
					if (!valid2){
						alert ("'Before' date of " + myform.endDate.value + " is not valid.");
					}
				}
				
			}
			
			function validateDate(month, day, year){
				try {
					var thisDate = new Date();
					var wrongMonth = month<1 || month>12;
					var wrongDay = (day<1) || (day>31) || (day>30 && ((month==4)||(month==6)||(month==9)||(month==11))) || (day>29 && month==2 && (year%4==0) && (year%100!=0 || year%400==0)) || (day>28 && month==2 && ((year%4!=0) || (year%100==0 && year%400!=0))); 
					var wrongYear = year < 1990 || year > parseInt(thisDate.getFullYear());
			
					var thisYear = parseInt(thisDate.getFullYear());
					var thisMonth = parseInt(thisDate.getMonth())+1;
					var thisDay = parseInt(thisDate.getDate());
					var wrongDate = year==thisYear && ((thisMonth<month) || (thisMonth==month && thisDay<(day-1)));
			
					if (wrongMonth ||wrongDay || wrongYear || wrongDate)
						return false;
					
					} catch (error){
						return false;
					}
					
					return true;
			}
		</script>
		
		
		<form id="Form1" name="Form1" method="post" action="showTransactions" onsubmit="return (confirminput(Form1));">
		<table border="0" style="padding-bottom:10px;">
		    <tr>
		        <td valign=top>After</td>
		        <td><input id="startDate" name="startDate" type="text" value="<%=(request.getParameter("startDate")==null)?"":request.getParameter("startDate")%>"/><br /><span class="credit">mm/dd/yyyy</span></td>
		        <td valign=top>Before</td>
		        <td><input name="endDate" id="endDate" type="text" value="<%=(request.getParameter("endDate")==null)?"":request.getParameter("endDate") %>"/><br /><span class="credit">mm/dd/yyyy</span></td>
		        <td valign=top><input type=submit value=Submit /></td>
		    </tr>
		</table>
		
		<table cellspacing="0" cellpadding="3" rules="all" border="1" id="_ctl0__ctl0_Content_Main_MyTransactions" style="width:100%;border-collapse:collapse;">
			<tr style="color:White;background-color:#BFD7DA;font-weight:bold;">
				<td>TransactionID</td><td>AccountId</td><td>Description</td><td>Amount</td>
			</tr>
			<% for (int i=0; i<transactions.length; i++){
				//limit to 100 entries
				if (i==100)
					break;
			%>
		
				<tr><td><%=transactions[i].getTransactionId()%></td><td><%=transactions[i].getAccountId()%></td><td><%=transactions[i].getTransactionType()%></td><td align="right"><%=transactions[i].getAmount()%></td></tr>
			<% } %>
		<tr>
		<!-- TODO PAGES: <td colspan="4"><span>1</span>&nbsp;<a href="javascript:__doPostBack('_ctl0$_ctl0$Content$Main$MyTransactions$_ctl54$_ctl1','')">2</a></td> -->
			</tr>
		</table>
		
		</form>
		
		</div>    
    
    </td>	
</div>

<jsp:include page="/footer.jspf"/>  