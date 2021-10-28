package ru.lanit;

import ru.lanit.repository.HibernatePostgresRepository;
import ru.lanit.repository.PostgresRepository;
import ru.lanit.repository.Repository;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPatronymic extends HttpServlet {
	private Repository repo = new PostgresRepository();
	private Repository hibernateRepo = new HibernatePostgresRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, javax.servlet.ServletException {
		HttpSession session = request.getSession();

		String patronymic = request.getParameter("patronymic");
		session.setAttribute("patronymic", patronymic);

		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");
		Person person = new Person(surname, name, patronymic);

		hibernateRepo.save(person);
		List<Person> persons = hibernateRepo.getAll();

		session.setAttribute("persons", persons);

		getServletContext().getRequestDispatcher("/exit.jsp").forward(request, response);
		
	}
}

