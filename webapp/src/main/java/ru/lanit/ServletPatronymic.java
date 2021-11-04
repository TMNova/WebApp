package ru.lanit;

import ru.lanit.Abstract.Address;
import ru.lanit.Abstract.Person;
import ru.lanit.entity.AddressEntity;
import ru.lanit.repository.HibernatePostgresRepository;
import ru.lanit.repository.Repository;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPatronymic extends HttpServlet {
	private Repository hibernateRepo = new HibernatePostgresRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, javax.servlet.ServletException {
		HttpSession session = request.getSession();

		String patronymic = request.getParameter("patronymic");
		String city = request.getParameter("city");
		String street = request.getParameter("street");

		session.setAttribute("patronymic", patronymic);

		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");
		Person person = new Person(surname, name, patronymic);
		Address address = new Address(city, street);

		hibernateRepo.save(person, address);
		List<AddressEntity> entAddress = hibernateRepo.getAllAddresses();

		session.setAttribute("addresses", entAddress);

		getServletContext().getRequestDispatcher("/exit.jsp").forward(request, response);
		
	}
}

