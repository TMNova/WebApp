package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepositoryPostgres implements Repository {
    static final private String DB_URL = "jdbc:postgresql://localhost:5432/person_db";
    static final private String DB_USERNAME = "timur";
    static final private String DB_PASSWORD = "";

    @Override
    public void saveToDataBase(String... varargs) {
        int lengthStringArgs = varargs.length;
        String query = "INSERT INTO person (surname, name, patronymic) VALUES (?, ?, ?)";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement st = connection.prepareStatement(query);

            for (int i = 0; i < lengthStringArgs; i++) {
                st.setString(i + 1, varargs[i]);
            }

            st.executeUpdate();
            st.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
