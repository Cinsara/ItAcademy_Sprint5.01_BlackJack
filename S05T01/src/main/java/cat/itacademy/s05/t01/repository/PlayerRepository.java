package cat.itacademy.s05.t01.repository;

import cat.itacademy.s05.t01.model.persons.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, String> {

}

