package cat.itacademy.s05.t01.model.persons;

import cat.itacademy.s05.t01.model.cards.Hand;
import org.springframework.data.annotation.Id;

public abstract class Person {
    @Id
    private String id;
    private String name;
    private String email;
    private Hand hand;

    public Person(){
    }

    public Person(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Hand getHand() {
        return hand;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}


