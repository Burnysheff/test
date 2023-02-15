package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    private int id;
    private boolean up;
    private int quoteId;
}
