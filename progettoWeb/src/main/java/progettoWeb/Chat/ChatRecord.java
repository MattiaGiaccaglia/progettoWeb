/*
 * Copyright 2024 Mattia Giaccaglia
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package progettoWeb.Chat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import progettoWeb.Assistence.AssistanceRecord;
import progettoWeb.Messages.MessagesRecord;
import progettoWeb.User.UserRecord;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
