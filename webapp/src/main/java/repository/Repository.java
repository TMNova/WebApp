package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public interface Repository {
    public void saveToDataBase(String query, String... varargs);

}
