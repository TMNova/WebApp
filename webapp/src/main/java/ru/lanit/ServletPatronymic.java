package ru.lanit;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

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
		String query = "INSERT INTO person (surname, name, patronymic) VALUES ('" + surname + "', '" + name + "', '" + patronymic + "');";

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			PreparedStatement st = connection.prepareStatement(query);
			st.executeUpdate();
			st.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/exit.jsp").forward(request, response);
		
	}
}

