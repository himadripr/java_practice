import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet("/testsessionhitcount")
public class SessionHitCountDemo extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		ServletContext context = getServletContext();
		HttpSession session = req.getSession();
		Integer count = (Integer)session.getAttribute("hitcount");
		if (count==null) {
			count=1;
		}else {
			count++;
		}
		session.setAttribute("hitcount", count);
		out.println("<h1>The hit count is : "+count+"</h1>");
	}

	


}