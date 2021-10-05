package ru.lanit;

import ru.lanit.repository.PostgresRepository;
import ru.lanit.repository.Repository;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPatronymic extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, javax.servlet.ServletException {
		HttpSession session = request.getSession();
		Repository repo = new PostgresRepository();

		String patronymic = request.getParameter("patronymic");
		session.setAttribute("patronymic", patronymic);

		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");

		repo.save(surname, name, patronymic);

		getServletContext().getRequestDispatcher("/exit.jsp").forward(request, response);
		
	}
}

