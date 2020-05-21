import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;

@WebServlet("/register")
public class FormDemoServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String username = req.getParameter("uname");
		String usercontact = req.getParameter("ucontact");
		out.println("<h3>Username : "+username+"<br>");
		out.println("usercontact : "+usercontact+"<br>");
		String[] s= req.getParameterValues("course");
		out.println("Your selected courses are :"+"<br/>");
		for (String s1: s){
			out.println(s1+"<br>");
		}
		out.println("</h3>");

		
	}

	private String excelType = "application/vnd.ms-excel";
	private String wordType = "application/msword";

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		//out.println("<h1>Request Headers Information</h1>");
		res.setContentType(excelType);
		out.println("<table border=1><tr><th>Header Name</th><th>Header Values</th></tr>");
		Enumeration e = req.getHeaderNames();
		while(e.hasMoreElements()){
			String hn = (String) e.nextElement();
			String hv = req.getHeader(hn);
			out.println("<tr><td>"+hn+"</td><td>"+hv+"</td></tr>");
		}
		out.println("</table>");
		
		out.println("<br></br>");
		out.println("<br></br>");
		out.println("<br></br>");

		// out.println("<h2>Client's IP address:"+req.getRemoteAddr()+"</h2>");
		// out.println("<h2>Client Host: "+req.getRemoteHost()+"</h2>");
		// out.println("<h2>Client Port: "+req.getRemotePort()+"</h2>");
		// out.println("<h2>Server Name: "+req.getServerName()+"</h2>");
		// out.println("<h2>Server Port: "+req.getServerPort()+"</h2>");

		
	}

	


}