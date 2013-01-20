package AltoroJ_00202_002e1.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.ibm.rational.appscan.altoromutual.util.ServletUtil;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n    \r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jspf", out, false);
      out.write("\r\n\r\n<div id=\"wrapper\" style=\"width: 99%;\">\r\n\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/bank/membertoc.jspf", out, false);
      out.write("\r\n\t<td valign=\"top\" colspan=\"3\" class=\"bb\">\r\n\t\t\r\n\t\t\r\n\t\t");

		String[] users = ServletUtil.getBankUsers();
		
		
      out.write("\r\n\t\t<script language=\"javascript\">\r\n\t\t\r\n\t\tfunction confirmpass(myform)\r\n\t\t{\r\n\t\t  if (myform.password1.value.length && (myform.password1.value==myform.password2.value))\r\n\t\t  {\r\n\t\t    return true;\r\n\t\t  }\r\n\t\t  else\r\n\t\t  {\r\n\t\t    myform.password1.value=\"\";\r\n\t\t    myform.password2.value=\"\";\r\n\t\t    myform.password1.focus();\r\n\t\t    alert (\"Passwords do not match\");\r\n\t\t    return false;\r\n\t\t  }\r\n\t\t\r\n\t\t}\r\n\t\t</script>\r\n\t\t\r\n\t\t<!-- Be careful what you change.  All changes are made directly to Altoro.mdb database. -->\r\n\t\t<div class=\"fl\" style=\"width: 99%;\">\r\n\t\t<p><span style=\"color:#FF0066;font-size:12pt;font-weight:bold;\">\r\n\t\t");

		java.lang.String error = (String)request.getSession().getAttribute("message");
		
		if (error != null && error.trim().length() > 0){
			out.print(error);
		}
		
      out.write("\r\n\t\t</span></p>\r\n\t\t\r\n\t\t<h1>Edit User Information</h1>\r\n\t\t\r\n\t\t<table width=\"100%\" border=\"0\">\r\n\t\t<form id=\"addAccount\" name=\"addAccount\" action=\"addAccount\" method=\"post\">\r\n\t\t  <tr>\r\n\t\t    <td colspan=\"4\">\r\n\t\t      <h2>Add an account to an existing user</h2>\r\n\t\t    </td>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <th>\r\n\t\t      Users:\r\n\t\t    </th>\r\n\t\t    <th>\r\n\t\t      Account Types:\r\n\t\t    </th>\r\n\t\t    <th>&nbsp;</th>\r\n\t\t    <th>&nbsp;</th>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <td>\r\n\t\t      \t<select name=\"username\" id=\"username\" size=\"1\">\r\n\t\t\t\t\t");
 for (String user:users){ 
      out.write("\r\n\t\t\t\t\t<option value=\"");
      out.print(user);
      out.write('"');
      out.write('>');
      out.print(user);
      out.write("</option>\r\n\t\t\t\t\t");
} 
      out.write("\r\n\t\t\t\t</select>\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <Select name=\"accttypes\">\r\n\t\t        <option Value=\"Checking\">Checking</option>\r\n\t\t        <option Value=\"Savings\" Selected>Savings</option>\r\n\t\t        <option Value=\"IRA\">IRA</option>\r\n\t\t      </Select></td>\r\n\t\t    <td></td>\r\n\t\t    <td><input type=\"submit\" value=\"Add Account\"></td>\r\n\t\t  </tr>\r\n\t\t  </form>\r\n\t\t  <form id=\"changePass\" name=\"changePass\" action=\"changePassword\" method=\"post\" onsubmit=\"return confirmpass(this);\">\t  \r\n\t\t  <tr>\r\n\t\t    <td colspan=\"4\"><h2><br><br>Change user's password</h2></td>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <th>\r\n\t\t      Users:\r\n\t\t    </th>\r\n\t\t    <th>\r\n\t\t      Password:\r\n\t\t    </th>\r\n\t\t    <th>\r\n\t\t      Confirm:\r\n\t\t    </th>\r\n\t\t    <th>&nbsp;</th>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <td>\r\n\t\t        <select name=\"username\" id=\"username\" size=\"1\">\r\n\t\t\t\t\t");
 for (String user:users){ 
      out.write("\r\n\t\t\t\t\t<option value=\"");
      out.print(user);
      out.write('"');
      out.write('>');
      out.print(user);
      out.write("</option>\r\n\t\t\t\t\t");
} 
      out.write("\r\n\t\t\t\t</select>\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <input type=\"password\" name=\"password1\">\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <input type=\"password\" name=\"password2\">\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <input type=\"submit\" name=\"change\" value=\"Change Password\">\r\n\t\t    </td>\r\n\t\t  </tr>\r\n\t\t  </form>\r\n\t\t  <form method=\"post\" name=\"addUser\" action=\"addUser\" id=\"addUser\" onsubmit=\"return confirmpass(this);\">\t\t  \r\n\t\t  <tr>\r\n\t\t    <td colspan=\"4\"><h2><br><br>Add an new user</h2></td>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <th>\r\n\t\t      First Name:\r\n\t\t      <br>\r\n\t\t      Last Name:\r\n\t\t    </th>\r\n\t\t    <th>\r\n\t\t      Username:\r\n\t\t    </th>\r\n\t\t    <th>\r\n\t\t      Password:\r\n\t\t      <br>\r\n\t\t      Confirm:\r\n\t\t    </th>\r\n\t\t    <th>\r\n\t\t      &nbsp;</th>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <td>\r\n\t\t      <input type=\"text\" name=\"firstname\">\r\n\t\t      <br>\r\n\t\t      <input type=\"text\" name=\"lastname\">\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <input type=\"text\" name=\"username\">\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <input type=\"password\" name=\"password1\">\r\n\t\t      <br>\r\n");
      out.write("\t\t      <input type=\"password\" name=\"password2\">\r\n\t\t    </td>\r\n\t\t    <td>\r\n\t\t      <input type=\"submit\" name=\"add\" value=\"Add User\">\r\n\t\t    </td>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <td colspan=\"4\">It is highly recommended that you leave the username as first\r\n\t\t      initial last name.\r\n\t\t    </td>\r\n\t\t  </tr>\r\n\t\t </form>\r\n\t\t</table>\r\n\t\t</div>\r\n    </td>\r\n</div>\r\n\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jspf", out, false);
      out.write('	');
      out.write('	');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
