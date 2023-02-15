package com.example.test.DAO;

import com.example.test.model.Quote;
import com.example.test.model.Vote;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VotesDAO {
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

    public List<Vote> getVoteByQuote(int id) {
        List<Vote> votes = new ArrayList<>();
        ResultSet rs;
        try {
            String request = "SELECT * FROM VOTES WHERE QUOTE_ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Vote vote = new Vote();
                vote.setId(rs.getInt(1));
                vote.setUp(rs.getBoolean(2));
                vote.setQuoteId(rs.getInt(4));

                votes.add(vote);
            }

            preparedStatement.close();
            return votes;

        } catch (Exception ignored) {
        }
        return null;
    }

    public void saveVote(int quoteId, boolean up) {
        try {
            String request = "INSERT INTO VOTES(UP, QUOTE_ID) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(request);

            preparedStatement.setBoolean(1, up);
            preparedStatement.setInt(2, quoteId);

            preparedStatement.close();

        } catch (Exception ignored) {
        }
    }
}
