package AltoroJ_00202_002e1.bank;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.ibm.rational.appscan.altoromutual.model.Account;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "membertoc.jspf", out, false);
      out.write("\r\n\t<td valign=\"top\" colspan=\"3\" class=\"bb\">\r\n\t\t<div class=\"fl\" style=\"width: 99%;\">\r\n\t\t\r\n\t\t");
com.ibm.rational.appscan.altoromutual.model.User user = (com.ibm.rational.appscan.altoromutual.model.User)request.getSession().getAttribute("user");
      out.write("\r\n\t\t\r\n\t\t<h1>Hello ");
      out.print( user.getFirstName() + " " + user.getLastName() );
      out.write("\r\n\t\t  </h1>\r\n\t\t\r\n\t\t<p>\r\n\t\t  Welcome to Altoro Mutual Online.\r\n\t\t</p>\r\n\t\t\r\n\t\t<form name=\"details\" method=\"get\" action=\"showAccount\">\r\n\t\t<table border=\"0\">\r\n\t\t  <TR valign=\"top\">\r\n\t\t    <td>View Account Details:</td>\r\n\t\t    <td align=\"left\">\r\n\t\t\t  <select size=\"1\" name=\"listAccounts\" id=\"listAccounts\">\r\n\t\t\t\t");
 
				for (Account account: user.getAccounts()){
					out.println("<option value=\""+account.getAccountId()+"\" >" + account.getAccountId() + " " + account.getAccountName() + "</option>");
				}
				
      out.write("\r\n\t\t\t  </select>\r\n\t\t      <input type=\"submit\" id=\"btnGetAccount\" value=\"   GO   \">\r\n\t\t    </td>\r\n\t\t  </tr>\r\n\t\t  <tr>\r\n\t\t    <td colspan=\"2\"><span id=\"_ctl0__ctl0_Content_Main_promo\"><table width=590 border=0><tr><td><h2>Congratulations! </h2></td></tr><tr><td>You have been pre-approved for an Altoro Gold Visa with a credit limit of $10000!</td></tr><tr><td>Click <a href='apply.jsp'>Here</a> to apply.</td></tr></table></span></td>\r\n\t\t  </tr>\r\n\t\t</table>\r\n\t\t</form>\r\n\t\t\r\n\t\t</div>\r\n    </td>\r\n</div>\r\n\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jspf", out, false);
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
