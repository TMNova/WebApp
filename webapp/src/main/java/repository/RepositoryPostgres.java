package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepositoryPostgres implements Repository {
    final private String DB_URL;
    final private String DB_USERNAME;
    final private String DB_PASSWORD;

    public RepositoryPostgres(String DB_URL, String DB_USERNAME, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    @Override
    public void saveToDataBase(String query, String... varargs) {
        int lengthStringArgs = varargs.length;
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
