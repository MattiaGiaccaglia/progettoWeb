package progettoWeb.IdChat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class IdChatRecord {

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
