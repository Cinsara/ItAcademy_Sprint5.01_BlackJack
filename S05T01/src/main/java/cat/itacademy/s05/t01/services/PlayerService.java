package cat.itacademy.s05.t01.services;

import cat.itacademy.s05.t01.model.persons.Player;
import cat.itacademy.s05.t01.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public Flux<Player> getRanking(){
        return playerRepository.findAll()
                .sort((p1,p2) -> Integer.compare(p2.getScore(),p1.getScore()));
    }

    public Mono<Player> changeName(String id, String newName){
        return playerRepository.findById(id)
                .flatMap(player -> {
                    player.setName(newName);
                    return playerRepository.save(player);
                });
    }
}
