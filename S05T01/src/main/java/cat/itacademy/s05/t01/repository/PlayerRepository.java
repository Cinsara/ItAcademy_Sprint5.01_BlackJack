package cat.itacademy.s05.t01.repository;

import cat.itacademy.s05.t01.model.persons.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PlayerRepository extends ReactiveCrudRepository<Player, String>, ReactiveMongoRepository<Player, String> {

}

