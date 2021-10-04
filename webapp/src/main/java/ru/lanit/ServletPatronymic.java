package ru.lanit;

import repository.RepositoryPostgres;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPatronymic extends HttpServlet {
	static final String DB_URL = "jdbc:postgresql://localhost:5432/person_db";
	static final String DB_USERNAME = "timur";
	static final String DB_PASSWORD = "";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, javax.servlet.ServletException {
		HttpSession session = request.getSession();

		String patronymic = request.getParameter("patronymic");
		session.setAttribute("patronymic", patronymic);

		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");
		String query = "INSERT INTO person (surname, name, patronymic) VALUES (?, ?, ?)";

		RepositoryPostgres postgres = new RepositoryPostgres(DB_URL, DB_USERNAME, DB_PASSWORD);
		postgres.saveToDataBase(query, surname, name, patronymic);

		getServletContext().getRequestDispatcher("/exit.jsp").forward(request, response);
		
	}
}

