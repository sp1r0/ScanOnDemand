package AltoroJ_00202_002e1;

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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jspf", out, false);
      out.write("\r\n\r\n<div id=\"wrapper\" style=\"width: 99%;\">\r\n\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/toc.jspf", out, false);
      out.write("\r\n   <td valign=\"top\" colspan=\"3\" class=\"bb\">\r\n\t\t<div class=\"fl\" style=\"width: 99%;\">\r\n\t\t\r\n\t\t<h1>Online Banking Login</h1>\r\n\t\t\r\n\t\t<!-- To get the latest admin login, please contact SiteOps at 415-555-6159 -->\r\n\t\t<p><span id=\"_ctl0__ctl0_Content_Main_message\" style=\"color:#FF0066;font-size:12pt;font-weight:bold;\">\r\n\t\t");

		java.lang.String error = (String)request.getSession(true).getAttribute("loginError");
		
		if (error != null && error.trim().length() > 0){
			request.getSession().removeAttribute("loginError");
			out.print(error);
		}
		
      out.write("\r\n\t\t</span></p>\r\n\t\t\r\n\t\t<form action=\"doLogin\" method=\"post\" name=\"login\" id=\"login\" onsubmit=\"return (confirminput(login));\">\r\n\t\t  <table>\r\n\t\t    <tr>\r\n\t\t      <td>\r\n\t\t        Username:\r\n\t\t      </td>\r\n\t\t      <td>\r\n\t\t        <input type=\"text\" id=\"uid\" name=\"uid\" value=\"\" style=\"width: 150px;\">\r\n\t\t      </td>\r\n\t\t      <td>\r\n\t\t      </td>\r\n\t\t    </tr>\r\n\t\t    <tr>\r\n\t\t      <td>\r\n\t\t        Password:\r\n\t\t      </td>\r\n\t\t      <td>\r\n\t\t        <input type=\"password\" id=\"passw\" name=\"passw\" style=\"width: 150px;\">\r\n\t\t        </td>\r\n\t\t    </tr>\r\n\t\t    <tr>\r\n\t\t        <td></td>\r\n\t\t        <td>\r\n\t\t          <input type=\"submit\" name=\"btnSubmit\" value=\"Login\">\r\n\t\t        </td>\r\n\t\t      </tr>\r\n\t\t  </table>\r\n\t\t</form>\r\n\t\t\r\n\t\t</div>\r\n\t\t\r\n\t\t<script type=\"text/javascript\">\r\n\t\t\tfunction setfocus() {\r\n\t\t\t    if (document.login.uid.value==\"\") {\r\n\t\t\t      \tdocument.login.uid.focus();\r\n\t\t\t    } else {\r\n\t\t\t      \tdocument.login.passw.focus();\r\n\t\t\t    }\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tfunction confirminput(myform) {\r\n\t\t\t    if (myform.uid.value.length && myform.passw.value.length) {\r\n");
      out.write("\t\t\t      return (true);\r\n\t\t\t    } else if (!(myform.uid.value.length)) {\r\n\t\t\t      myform.reset();\r\n\t\t\t      myform.uid.focus();\r\n\t\t\t      alert (\"You must enter a valid username\");\r\n\t\t\t      return (false);\r\n\t\t\t    } else {\r\n\t\t\t      myform.passw.focus();\r\n\t\t\t      alert (\"You must enter a valid password\");\r\n\t\t\t      return (false);\r\n\t\t\t    }\r\n\t\t\t}\r\n\t\t\twindow.onload = setfocus;\r\n\t\t</script>\r\n    </td>\r\n</div>\r\n\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jspf", out, false);
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
