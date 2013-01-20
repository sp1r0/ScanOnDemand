package AltoroJ_00202_002e1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.ibm.rational.appscan.altoromutual.util.ServletUtil;
import org.apache.commons.lang.StringEscapeUtils;

public final class feedbacksuccess_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      			"notfound.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n\r\n    \r\n    \r\n\r\n\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jspf", out, false);
      out.write("\r\n\r\n<div id=\"wrapper\" style=\"width: 99%;\">\r\n\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "toc.jspf", out, false);
      out.write("\r\n    <td valign=\"top\" colspan=\"3\" class=\"bb\">\r\n    \r\n    \r\n\t\t\r\n\t\t<div class=\"fl\" style=\"width: 99%;\">\r\n\t\t\r\n\t\t <h1>Thank You</h1>\r\n\t\t \r\n\t\t <p>Thank you for your comments");
      out.print( (request.getAttribute("message_feedback")!=null)?", "+request.getAttribute("message_feedback"):"" );
      out.write(".  They will be reviewed by our Customer Service staff and given the full attention that they deserve. \r\n\t\t ");
 String email = (String) request.getParameter("email_addr"); 
		 	boolean regExMatch = email!=null && email.matches(ServletUtil.EMAIL_REGEXP);
		 	if (email != null && email.trim().length() != 0 && regExMatch) {
      out.write(" \r\n\t\t\t Our reply will be sent to your email: ");
      out.print( ServletUtil.sanitizeBasic(email.toLowerCase()) /*email.toLowerCase()*/ );
      out.write("\r\n\t\t");
 } else {
      out.write("\r\n\t\t\tHowever, the email you gave is incorrect (");
      out.print(email.toLowerCase() /*ServletUtil.sanitizeWeb(email.toLowerCase())*/);
      out.write(") and you will not receive a response.\r\n\t\t");
 }
      out.write("\r\n\t\t\r\n\t\t</p>\r\n\t\t\r\n\t\t</div>\r\n    </td>\t\r\n</div>\r\n\r\n");
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
