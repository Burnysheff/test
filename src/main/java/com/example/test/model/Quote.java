package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private int id;
    private int authorId;
    private String text;
    private int score;
    private String localDate;
}
