package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {
    @Id
    private Integer id;

    @Column("name")
    private String name;

    @Column("gamesPlayed")
    private int gamesPlayed;

    @Column("gamesWon")
    private int gamesWon;

    @Column("balance")
    private double balance = 100.0;
}
