<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/header.jspf"/>

<div id="wrapper" style="width: 99%;">
	<jsp:include page="membertoc.jspf"/>
    <td valign="top" colspan="3" class="bb">
    	<%@page import="com.ibm.rational.appscan.altoromutual.model.Account"%>
		
		<%com.ibm.rational.appscan.altoromutual.model.User user = (com.ibm.rational.appscan.altoromutual.model.User)request.getSession().getAttribute("user");%>
		
		<script type="text/javascript" src="mozxpath.js"></script>
		<script type="text/javascript">
			function setfocus() {
		      	document.login.transferAmount.focus();
			}
			
			function confirminput(myform) {
				var dbt=document.getElementById("debitAccount").value;
				var cdt=document.getElementById("creditAccount").value;
				var amt=document.getElementById("transferAmount").value;
				
				if (dbt == cdt) {
					alert("From Account and To Account fields cannot be the same."+dbt+" "+cdt);
					return false;
				}
				else if (!(amt > 0)){
					alert("Transfer Amount must be a number greater than 0.");
					return false;
				}
		
				return true;
			}
			window.onload = setfocus;
		</script>
		
		<div class="fl" style="width: 99%;">
		
		<form id="tForm" name="tForm" method="post" action="doTransfer" onsubmit="return (confirminput(tForm));">
		
		<h1>Transfer Funds</h1>
		
		<table cellSpacing="0" cellPadding="1" width="100%" border="0">
		  <tr>
		    <td><strong>From Account:</strong>
		    </td>
		    <td>
			  <select size="1" id="debitAccount" name="debitAccount">
				<% 
				for (Account account: user.getAccounts()){
					out.println("<option value=\""+account.getAccountId()+"\" >" + account.getAccountId() + " " + account.getAccountName() + "</option>");
				}
				%>
			  </select>
		    </td>
		  </tr>
		  <tr>
		    <td><strong>To Account:</strong></td>
		    <td>
			  <select size="1" id="creditAccount" name="creditAccount">
				<% 
				for (Account account: user.getAccounts()){
					out.println("<option value=\""+account.getAccountId()+"\">" + account.getAccountId() + " " + account.getAccountName() + "</option>");
				}
				%>
			  </select>
		    </td>
		  </tr>
		  <tr>
		    <td><strong> Amount to Transfer:</strong>
		    </td>
		    <td><input type="text" id="transferAmount" name="transferAmount"></td>
		  </tr>
		  <tr>
		    <td colspan="2" align="center"><input type="submit" name="transfer" value="Transfer Money" ID="transfer"></td>
		  </tr>
		  <tr>
		    <td colspan="2">&nbsp;</td>
		  </tr>
		  <tr>
		    <td colspan="2" align="center">
		    <span id="_ctl0__ctl0_Content_Main_postResp" align="center"><span style='color: Red'><%=(request.getAttribute("message")==null)?"":request.getAttribute("message") %></span></span>
		    <span id="soapResp" name="soapResp" align="center" />
		    </td>
		  </tr>
		</table>
		</form>
		
		</div>       
    </td>	
</div>

<jsp:include page="/footer.jspf"/>  