package progettoWeb.Messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UserRecord mittente;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(nullable = false)
    private UserRecord destinatario;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonIgnore
    private ChatRecord chat;

    public ChatRecord getChat() {
        return chat;
    }

    public void setChat(ChatRecord chat) {
        this.chat = chat;
    }

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

    public UserRecord getMittente() {
        return mittente;
    }

    public void setMittente(UserRecord mittente) {
        this.mittente = mittente;
    }

    public UserRecord getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UserRecord destinatario) {
        this.destinatario = destinatario;
    }
}
