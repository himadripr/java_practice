import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;

@WebServlet("/testannotation")
public class FirstServlet implements Servlet{
	static {
		System.out.println("servlet class loading...");
	}

	public FirstServlet(){
		System.out.println("Servlet instantiation...");
	}

	public void init(ServletConfig servletConfig) throws ServletException{
		System.out.println("init method execution...");
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		System.out.println("service method execution...");
		PrintWriter writer = res.getWriter();
		writer.println("<h1>Welcome advanced java students</h1>");
		writer.println("<h1>the server time is"+new Date()+"</h1>");

	}

	public void destroy(){
		System.out.println("destroy method execution...");
	}

	public ServletConfig getServletConfig(){
		return null;
	}

	public String getServletInfo(){
		return "Developed by Himadri";
	}
}