import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class FirstHttpServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<h1>Response from doGet method</h1>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<h1>Response from doPost method</h1>");
	}


}