package progettoWeb.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Assistence.AssistanceRecord;
import progettoWeb.Assistence.AssistanceService;
import progettoWeb.Chat.ChatRecord;
import progettoWeb.Chat.ChatRepository;
import progettoWeb.Chat.ChatService;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import javax.print.DocFlavor;
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
        List<ChatRecord> chatRecords = chatService.getAllChats();
        List<MessagesRecord> allMessagesRecords = new ArrayList<>();
        for(ChatRecord c : chatRecords) {
            List<MessagesRecord> messages = c.getMessaggi();
            allMessagesRecords.addAll(messages);
        }
        return allMessagesRecords;
    }

    //Restituisco tutti i messaggi da una chat
    public List<MessagesRecord> getAllMessagesByIDChat(int idChat) {
        ChatRecord chatRecord = chatService.getChat(idChat);
        return chatRecord.getMessaggi();
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
