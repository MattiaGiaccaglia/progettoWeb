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
