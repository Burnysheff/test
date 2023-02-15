package com.example.test.service;

import com.example.test.DAO.QuoteDAO;
import com.example.test.dto.QuoteForm;
import com.example.test.model.Quote;
import com.example.test.model.Vote;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class QuoteService {
    VotesService votesService = new VotesService();
    QuoteDAO quoteDAO = new QuoteDAO();

    public void saveQuote(QuoteForm quoteForm) {
        quoteDAO.saveQuote(quoteForm, LocalDate.now());
    }

    public Quote getQuote(int id) {
        Quote quote = quoteDAO.getQuote(id);

        int score = 0;
        for (Vote vote : votesService.getVotesByQuote(id)) {
            if (vote.isUp()) {
                ++score;
            } else {
                --score;
            }
        }

        quote.setScore(score);
        return quote;
    }

    public void patchQuote(int id, QuoteForm quoteForm) {
        quoteDAO.patchQuote(id, quoteForm, LocalDate.now());
    }

    public void deleteQuote(int id) {
        quoteDAO.deleteQuote(id);
    }

    public List<Quote> topQuotes() {
        List<Quote> quotes = quoteDAO.getAll();

        for (Quote quote : quotes) {
            int score = 0;
            for (Vote vote : votesService.getVotesByQuote(quote.getId())) {
                if (vote.isUp()) {
                    ++score;
                } else {
                    --score;
                }
            }
            quote.setScore(score);
        }

        List<Quote> sortedList = quotes.stream()
                .sorted(Comparator.comparingInt(Quote::getScore)).toList();

        return sortedList.stream().limit(10).toList();
    }
    public List<Quote> downQuotes() {
        List<Quote> quotes = quoteDAO.getAll();

        for (Quote quote : quotes) {
            int score = 0;
            for (Vote vote : votesService.getVotesByQuote(quote.getId())) {
                if (vote.isUp()) {
                    ++score;
                } else {
                    --score;
                }
            }
            quote.setScore(score);
        }

        List<Quote> sortedList = quotes.stream()
                .sorted(Comparator.comparing(Quote::getScore, Comparator.reverseOrder())).toList();

        return sortedList.stream().limit(10).toList();
    }
}
