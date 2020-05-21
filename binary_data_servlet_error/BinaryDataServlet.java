import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;

@WebServlet("/download")
public class BinaryDataServlet extends HttpServlet{
	private String imageContentType = "image/jpeg";
	private String videoContentType = "video/mp4";

	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		res.setContentType(videoContentType);
		ServletOutputStream os = res.getOutputStream();
		PrintWriter out = res.getWriter();
		String path = getServletContext().getRealPath("clouds.mp4");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[]b = new byte[(int)file.length()];
		fis.read(b);
		os.write(b);
		os.flush();
		os.close();
		
	}

	


}