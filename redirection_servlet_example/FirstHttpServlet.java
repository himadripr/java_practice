import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet("/test1")
public class FirstHttpServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		res.setStatus(302);
		res.setHeader("Location", "/redirection_servlet_example/test2");
	}

	


}