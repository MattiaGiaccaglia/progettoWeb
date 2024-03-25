package progettoWeb.Chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import progettoWeb.Assistence.AssistanceRecord;
import progettoWeb.Messages.MessagesRecord;
import progettoWeb.User.UserRecord;

import java.util.List;

@Entity
public class ChatRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private boolean close;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "staff_id")
    private UserRecord staff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "utente_id")
    private UserRecord utente;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    private List<MessagesRecord> messaggi;

    @OneToOne(mappedBy = "chat")
    @JsonIgnore
    private AssistanceRecord assistanceRecord;

    public List<MessagesRecord> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<MessagesRecord> messaggi) {
        this.messaggi = messaggi;
    }

    public UserRecord getStaff() {
        return staff;
    }

    public void setStaff(UserRecord staff) {
        this.staff = staff;
    }

    public UserRecord getUtente() {
        return utente;
    }

    public void setUtente(UserRecord utente) {
        this.utente = utente;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public AssistanceRecord getAssistanceRecord() {
        return assistanceRecord;
    }

    public void setAssistanceRecord(AssistanceRecord assistanceRecord) {
        this.assistanceRecord = assistanceRecord;
    }
}
