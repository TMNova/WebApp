package ru.lanit.repository;

import ru.lanit.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresRepository implements Repository {
    static final private String DB_URL = "jdbc:postgresql://localhost:5432/person_db";
    static final private String DB_USERNAME = "timur";
    static final private String DB_PASSWORD = "";

    @Override
    public void save(Person person) {
        String query = "INSERT INTO person (surname, name, patronymic) VALUES (?, ?, ?)";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, person.getSurname());
            st.setString(2, person.getSurname());
            st.setString(3, person.getPatronymic());
            st.executeUpdate();
            st.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
