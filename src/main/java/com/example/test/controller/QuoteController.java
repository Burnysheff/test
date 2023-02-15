package com.example.test.controller;

import com.example.test.dto.QuoteForm;
import com.example.test.model.Quote;
import com.example.test.service.QuoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {
    QuoteService quoteService = new QuoteService();

    @PostMapping("/quoteCreate")
    @ApiOperation(value = "Saves new quote")
    public void saveQuote(@RequestBody QuoteForm quoteForm) {
        quoteService.saveQuote(quoteForm);
    }

    @GetMapping("/quoteGet/{id}")
    @ApiOperation(value = "Gets quote by id in path")
    public Quote getQuote(@PathVariable("id") int id) {
        return quoteService.getQuote(id);
    }

    @PatchMapping("/quoteChange/{id}")
    @ApiOperation(value = "Patches quote by id in path")
    public void changeQuote(@PathVariable("id") int id, @RequestBody QuoteForm quoteForm) {
        quoteService.patchQuote(id, quoteForm);
    }

    @DeleteMapping("/quoteDelete/{id}")
    @ApiOperation(value = "Deletes quote by id in path")
    public void deleteQuote(@PathVariable("id") int id) {
        quoteService.deleteQuote(id);
    }

    @GetMapping("/quoteTop")
    @ApiOperation(value = "Gets top 10 quotes (by votes)")
    public List<Quote> quoteTop() {
        return quoteService.topQuotes();
    }
    @GetMapping("/quoteDown}")
    @ApiOperation(value = "Gets top 10 worst quotes (by votes)")
    public List<Quote> quoteDown() {
        return quoteService.downQuotes();
    }
}
