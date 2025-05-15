package com.example.demo.services;

import com.example.demo.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameAction {
    private String actionType;
    private Card card;
    private Integer betAmount;
}
