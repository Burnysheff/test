package com.example.test.controller;

import com.example.test.service.VotesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotesController {
    VotesService votesService = new VotesService();

    @PostMapping("/{id}/upVote")
    @ApiOperation(value = "Saves upvote on quote in path")
    public void upper(@PathVariable("id") int id) {
        votesService.upVote(id);
    }

    @PostMapping("/{id}/downVote")
    @ApiOperation(value = "Saves downvote on quote in path")
    public void downer(@PathVariable("id") int id) {
        votesService.downVote(id);
    }
}
