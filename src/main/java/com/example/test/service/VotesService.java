package com.example.test.service;

import com.example.test.DAO.VotesDAO;
import com.example.test.model.Quote;
import com.example.test.model.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotesService {
    VotesDAO votesDAO = new VotesDAO();

    public List<Vote> getVotesByQuote(int id) {
        return votesDAO.getVoteByQuote(id);
    }

    public void upVote(int quoteId) {
        votesDAO.saveVote(quoteId, true);
    }

    public void downVote(int quoteId) {
        votesDAO.saveVote(quoteId, false);
    }
}
