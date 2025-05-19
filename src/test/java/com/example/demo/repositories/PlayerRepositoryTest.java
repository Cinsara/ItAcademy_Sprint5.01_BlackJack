package com.example.demo.repositories;

import com.example.demo.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlayerRepositoryTest {
    private final PlayerRepository playerRepository;

    @Autowired
    PlayerRepositoryTest(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @BeforeEach
    void setUp(){
        playerRepository.deleteAll().block();

        Player player1 = new Player(null, "Oscar", 12, 5,790);
        Player savedPlayer1 = playerRepository.save(player1).block();

        Player player2 = new Player(null, "Lucas", 3, 2,245);
        Player savedPlayer2 = playerRepository.save(player2).block();

        Player player3 = new Player(null, "Veronica", 6, 3,450);
        Player savedPlayer3 = playerRepository.save(player3).block();
    }

    @Test
    void findAllByOrderByBalanceDesc() {
        playerRepository.findAllByOrderByBalanceDesc()
                .collectList()
                .as(StepVerifier::create)
                .assertNext(players -> {
                    assertThat(players).hasSize(3);
                    assertThat(players.get(0).getName()).isEqualTo("Oscar");
                    assertThat(players.get(1).getName()).isEqualTo("Veronica");
                    assertThat(players.get(2).getName()).isEqualTo("Lucas");
                })
                .verifyComplete();
    }

    @Test
    void findByName() {
        playerRepository.findByName("Oscar")
                .as(StepVerifier::create)
                .assertNext(found -> {
                    assertThat(found.getName()).isEqualTo("Oscar");
                    assertThat(found.getGamesPlayed()).isEqualTo(12);
                    assertThat(found.getGamesWon()).isEqualTo(5);
                    assertThat(found.getBalance()).isEqualTo(790);
                })
                .verifyComplete();
    }

    @Test
    void findOrCreatePlayer() {
        playerRepository.findOrCreatePlayer("Oscar")
                .as(StepVerifier::create)
                .assertNext(player -> {
                    assertThat(player.getName()).isEqualTo("Oscar");
                    assertThat(player.getGamesPlayed()).isEqualTo(12);
                    assertThat(player.getGamesWon()).isEqualTo(5);
                    assertThat(player.getBalance()).isEqualTo(790);
                })
                .verifyComplete();
        playerRepository.findByName("Oscar")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}