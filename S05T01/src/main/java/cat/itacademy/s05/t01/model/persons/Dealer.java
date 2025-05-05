package cat.itacademy.s05.t01.model.persons;

import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dealer")
@Table(name = "dealer")
public class Dealer extends Person {
    private boolean connected;

    public Dealer(){
    }

    public Dealer(String id, String name, String email, boolean connected){
        super(id,name,email);
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public String toString(){
        return "\n--- DEALER ---" +
                "\n- ID: " + getId() +
                "\n- Name: " + getName() +
                "\n- Email: " + getEmail() +
                "\n- Active: " + this.connected;
    }
}
