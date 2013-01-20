package AltoroJ_00202_002e1.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n\t<td valign=\"top\" colspan=\"3\" class=\"bb\">\r\n\t\t<h1>Administration Login</h1>\r\n\t\t\r\n\t\t<!-- Password: Altoro1234 -->\r\n\t\t\r\n\t\t<form id=\"Default\" method=\"post\" action=\"doAdminLogin\">\r\n\t\t<!-- <img id=\"captcha\" src=\"getCaptcha.jsp\" /><br /> -->\r\n\t\t  <p>\r\n\t\t<!--  add captcha input here -->\r\n\t\t    <strong>Enter the administrative password:</strong><br />\r\n\t\t    <input id=\"password\" name=\"password\" type=password runat=\"server\" /><br /><br />\r\n\t\t    <input type=\"submit\" name=\"btnSubmit\" value=\"Login\">\r\n\t\t  </p>\r\n\t\t<p><span id=\"_ctl0__ctl0_Content_Main_message\" style=\"color:#FF0066;font-size:12pt;font-weight:bold;\">\r\n\t\t\t");

			java.lang.String error = (String)request.getAttribute("loginError");
			
			if (error != null && error.trim().length() > 0){
				request.getSession().removeAttribute("loginError");
				out.print(error);
			}
			
      out.write("\r\n\t\t</span></p>\r\n\t\t</form>\r\n\t\t\r\n\t\t<script>\r\n\t\twindow.onload = document.forms[1].elements[1].focus();\r\n\t\t</script>\r\n\t</td>\r\n</div>\r\n\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jspf", out, false);
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
