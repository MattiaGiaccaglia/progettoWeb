package progettoWeb.Assistence;

import jakarta.persistence.*;
import progettoWeb.Chat.ChatRecord;
import progettoWeb.User.UserRecord;

@Entity
public class AssistanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = ChatRecord.class)
    @JoinColumn(nullable = false ,name = "IdChat")
    private ChatRecord IdChat;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(nullable = false ,name = "staff")
    private UserRecord staff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatRecord getIdChat() {
        return IdChat;
    }

    public void setIdChat(ChatRecord idChat) {
        IdChat = idChat;
    }

    public UserRecord getStaff() {
        return staff;
    }

    public void setStaff(UserRecord staff) {
        this.staff = staff;
    }
}
