package ru.lanit;

import repository.RepositoryPostgres;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPatronymic extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, javax.servlet.ServletException {
		HttpSession session = request.getSession();

		String patronymic = request.getParameter("patronymic");
		session.setAttribute("patronymic", patronymic);

		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");

		RepositoryPostgres postgres = new RepositoryPostgres();
		postgres.saveToDataBase(surname, name, patronymic);

		getServletContext().getRequestDispatcher("/exit.jsp").forward(request, response);
		
	}
}

