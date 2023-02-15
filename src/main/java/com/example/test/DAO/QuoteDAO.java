package com.example.test.DAO;

import com.example.test.dto.LoginForm;
import com.example.test.dto.QuoteForm;
import com.example.test.model.Quote;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuoteDAO {
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

    public void saveQuote(QuoteForm quoteForm, LocalDate date) {
        try {
            String request = "INSERT INTO quotes(text, author_id, data, SCORE) VALUES (?, ?, ?, 0);";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, quoteForm.getText());
            preparedStatement.setInt(2, quoteForm.getAuthorId());
            preparedStatement.setString(3, String.valueOf(date));

            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception ignored) {
        }

    }

    public Quote getQuote(int id) {
        ResultSet rs;
        try {
            String request = "SELECT * FROM QUOTES WHERE ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            Quote quote = new Quote();
            quote.setId(rs.getInt(1));
            quote.setText(rs.getString(2));
            quote.setAuthorId(rs.getInt(3));
            quote.setLocalDate(rs.getString(4));

            preparedStatement.close();

            return quote;

        } catch (Exception ignored) {
        }
        return null;
    }

    public void patchQuote(int id, QuoteForm quoteForm, LocalDate date) {
        try {
            String request = "UPDATE QUOTES SET TEXT = ?, AUTHOR_ID = ?, DATA = ?, SCORE = 0 WHERE ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, quoteForm.getText());
            preparedStatement.setInt(2, quoteForm.getAuthorId());
            preparedStatement.setString(3, String.valueOf(date));
            preparedStatement.setInt(4, id);

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception ignored) {
        }
    }

    public void deleteQuote(int id) {
        try {
            String request = "DELETE FROM QUOTES WHERE ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception ignored) {
        }
    }

    public List<Quote> getAll() {
        ResultSet rs;
        List<Quote> quotes = new ArrayList<>();
        try {
            String request = "SELECT * FROM QUOTES;";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Quote quote = new Quote();
                quote.setId(rs.getInt(1));
                quote.setText(rs.getString(2));
                quote.setAuthorId(rs.getInt(3));
                quote.setLocalDate(rs.getString(4));

                quotes.add(quote);
            }

            preparedStatement.close();

            return quotes;

        } catch (Exception ignored) {
        }
        return null;
    }
}
