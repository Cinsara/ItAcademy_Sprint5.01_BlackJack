package cat.itacademy.s05.t01.repository;

import cat.itacademy.s05.t01.model.game.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GameRepository extends ReactiveCrudRepository<Game, String>, ReactiveMongoRepository<Game, String> {

}
