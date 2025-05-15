package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameRequest {
    @NotBlank
    private String playerName;

    @Min(1)
    @Max(10000)
    private double initialBalance = 100.0;
}
