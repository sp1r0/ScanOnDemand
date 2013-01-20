package AltoroJ_00202_002e1.bank;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class apply_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n    <td valign=\"top\" colspan=\"3\" class=\"bb\">\r\n\t\t<div class=\"fl\" style=\"width: 99%;\">\r\n\t\t\t<h1>Altoro Mutual Gold Visa Application</h1>\r\n\t\t\t<span><p><b>No application is needed.</b>To approve your new $10000 Altoro Mutual Gold Visa<br />with an 7.9% APR simply enter your password below.</p><form method=\"post\" name=\"Credit\" action=\"ccApply\"><table border=0><tr><td>Password:</td><td><input type=\"password\" name=\"passwd\"></td></tr><tr><td></td><td><input type=\"submit\" name=\"Submit\" value=\"Submit\"></td></tr></table></form></span>\r\n\t\t</div>\r\n    </td>\t\r\n</div>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jspf", out, false);
      out.write("   \r\n");
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
