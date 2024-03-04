package progettoWeb.Assistence;

import jakarta.persistence.*;
import progettoWeb.IdChat.IdChatRecord;
import progettoWeb.User.UserRecord;

@Entity
public class AssistanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = IdChatRecord.class)
    @JoinColumn(name = "IdChat")
    private IdChatRecord IdChat;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(name = "staff")
    private UserRecord staff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IdChatRecord getIdChat() {
        return IdChat;
    }

    public void setIdChat(IdChatRecord idChat) {
        IdChat = idChat;
    }

    public UserRecord getStaff() {
        return staff;
    }

    public void setStaff(UserRecord staff) {
        this.staff = staff;
    }
}
