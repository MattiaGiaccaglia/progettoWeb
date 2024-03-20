package progettoWeb.Messages;

import jakarta.persistence.*;
import progettoWeb.Chat.ChatRecord;
import progettoWeb.User.UserRecord;
import java.time.LocalDateTime;

@Entity
public class MessagesRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String testoMessaggio;

    private LocalDateTime data;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(nullable = false)
    private UserRecord user;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(nullable = false)
    private UserRecord gestorePiattaforma;

    @ManyToOne(targetEntity = ChatRecord.class)
    private ChatRecord IdChat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestoMessaggio() {
        return testoMessaggio;
    }

    public void setTestoMessaggio(String testoMessaggio) {
        this.testoMessaggio = testoMessaggio;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public UserRecord getUser() {
        return user;
    }

    public void setUser(UserRecord user) {
        this.user = user;
    }

    public UserRecord getGestorePiattaforma() {
        return gestorePiattaforma;
    }

    public void setGestorePiattaforma(UserRecord gestorePiattaforma) {
        this.gestorePiattaforma = gestorePiattaforma;
    }

    public ChatRecord getIdChat() {
        return IdChat;
    }

    public void setIdChat(ChatRecord idChat) {
        IdChat = idChat;
    }
}
