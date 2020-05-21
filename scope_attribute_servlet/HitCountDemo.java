import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet("/testhitcount")
public class HitCountDemo extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		ServletContext context = getServletContext();
		Integer count = (Integer)context.getAttribute("hitcount");
		if (count==null) {
			count=1;
		}else {
			count++;
		}
		context.setAttribute("hitcount", count);
		out.println("<h1>The hit count is : "+count+"</h1>");
	}

	


}