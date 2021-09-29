package ru.lanit;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("Hello " + name);
		response.getWriter().println("</html>");
	}
}