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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Assistence.AssistanceRecord;
import progettoWeb.Assistence.AssistanceService;
import progettoWeb.Chat.ChatRecord;
import progettoWeb.Chat.ChatService;
import progettoWeb.User.Role;
import progettoWeb.User.UserException;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesService {
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private AssistanceService assistanceService;

    //Restituisco tutti i messaggi
    public List<MessagesRecord> getAllMessages() {
        return chatService.getAllChats().stream().flatMap(chatRecord -> chatRecord.getMessaggi().stream()).collect(Collectors.toList());
    }


    //Restituisco tutti i messaggi da una chat
    public List<MessagesRecord> getAllMessagesByIDChat(int idChat) {
        return chatService.getChat(idChat).getMessaggi();
    }


    //Restituisco messaggi a partire da ID
    public MessagesRecord getMessageByID(int id) {
        return messagesRepository.findById(id)
                .orElseThrow(() -> new MessageException.MessageExceptionNotFound("Nessun messaggio presente con il seguente id: " + id));
    }

    //Aggiungo un messaggio a una determinata chat
    public boolean addMessages(MessagesRecord messagesRecord) {
        UserRecord mittente = userService.getUser(messagesRecord.getMittente().getId());
        UserRecord destinatario = userService.getUser(messagesRecord.getDestinatario().getId());
        List<MessagesRecord> messaggi = new ArrayList<>();
        List<ChatRecord> chatRecords = chatService.getAllChats();
        //Controllo che i dati nella requestBody siano corretti
        if (!mittente.equals(messagesRecord.getMittente()) || !destinatario.equals(messagesRecord.getDestinatario()))
            return false;
        // Controlla che il mittente sia diverso dal destinatario
        if (mittente.equals(destinatario) || mittente.getRuolo() == destinatario.getRuolo())
            return false; // Mittente e destinatario non possono essere lo stesso utente

        // Controlla che il mittente sia utente e il destinatario sia staff, o viceversa
        if ((mittente.getRuolo() == Role.utente && destinatario.getRuolo() == Role.staff) ||
                (mittente.getRuolo() == Role.staff && destinatario.getRuolo() == Role.utente)) {
            for (ChatRecord c : chatRecords)
                if ((c.getUtente().equals(mittente) && c.getStaff().equals(destinatario) && !c.isClose()) ||
                        c.getUtente().equals(destinatario) && c.getStaff().equals(mittente) && !c.isClose()) {
                    c.getMessaggi().add(messagesRecord);
                    messagesRecord.setChat(c);
                    messagesRecord.setData(LocalDateTime.now().withNano(0));
                    messagesRepository.save(messagesRecord);
                    return true; // Messaggio aggiunto a una chat esistente
                }
            // Creo una nuova chat e aggiungo il messaggio
            ChatRecord chatRecord = new ChatRecord();
            if(mittente.getRuolo() == Role.utente) {
                chatRecord.setUtente(mittente);
                chatRecord.setStaff(destinatario);
            }else{
                chatRecord.setUtente(destinatario);
                chatRecord.setStaff(mittente);
            }
            chatRecord.setClose(false);
            messaggi.add(messagesRecord);
            chatRecord.setMessaggi(messaggi);
            chatService.addChat(chatRecord);

            //Salvo messaggio
            messagesRecord.setChat(chatRecord);
            messagesRecord.setData(LocalDateTime.now().withNano(0));
            messagesRepository.save(messagesRecord);

            // Creo assistenza dello staff
            AssistanceRecord assistanceRecord = new AssistanceRecord();
            assistanceRecord.setChat(chatRecord);
            assistanceRecord.setStaff(destinatario);
            assistanceService.addAssistance(assistanceRecord);
            return true;
        }
        return false;
    }
}
