import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet("/test2")
public class SecondHttpServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<h1>Response from second servlet</h1>");
	}

	


}