package com.example.test.DAO;

import com.example.test.dto.LoginForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDAO {

    private static final Connection connection;

    static {
        try {
            String URL = "jdbc:h2:mem:testdb";
            String userName = "sa";
            connection = DriverManager.getConnection(URL, userName, "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(LoginForm loginForm, LocalDate date) {
        try {
            String request = "INSERT INTO users(name, email, password, data) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, loginForm.getName());
            preparedStatement.setString(2, loginForm.getEmail());
            preparedStatement.setString(3, loginForm.getPassword());
            preparedStatement.setString(4, String.valueOf(date));

            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception ignored) {
        }

    }
}
