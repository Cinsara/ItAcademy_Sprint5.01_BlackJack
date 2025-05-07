package cat.itacademy.s05.t01.services;

import cat.itacademy.s05.t01.model.persons.Dealer;
import org.springframework.stereotype.Service;

@Service
public class DealerService {
    private static final Dealer DEFAULT_DEALER = new Dealer();

    static {
        DEFAULT_DEALER.setId(String.valueOf(1L));
        DEFAULT_DEALER.setName("DealerBot");
        DEFAULT_DEALER.setEmail("dealer@blackjack.com");
    }

    public Dealer getDefaultDealer() {
        return DEFAULT_DEALER;
    }
}
