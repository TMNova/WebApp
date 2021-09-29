package ru.lanit;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletSurname extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, javax.servlet.ServletException{
		HttpSession session = request.getSession();

		String surname = request.getParameter("surname");

		session.setAttribute("surname", surname);

		getServletContext().getRequestDispatcher("/patronymic.jsp").forward(request, response);
		
	}
}

