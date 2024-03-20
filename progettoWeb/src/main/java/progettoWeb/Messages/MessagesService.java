package progettoWeb.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Assistence.AssistanceRecord;
import progettoWeb.Assistence.AssistanceRepository;
import progettoWeb.Chat.ChatRecord;
import progettoWeb.Chat.ChatRepository;
import progettoWeb.Chat.ChatService;
import progettoWeb.User.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    AssistanceRepository assistanceRepository;

    //Restituisco tutti i messaggi
    public List<MessagesRecord> getAllMessages() {
        List<MessagesRecord> messagesRecords = new ArrayList<>();
        messagesRepository.findAll().forEach(messagesRecords::add);
        return messagesRecords;
    }

    //Restituisco tutti i messaggi da una chat
    public List<MessagesRecord> getAllMessagesByIDChat(int idChat) {
        ChatRecord chatRecord = chatService.getChat(idChat);
        List<MessagesRecord> messagesRecords = new ArrayList<>();
        MessagesRecord messages = new MessagesRecord();
        messages.setIdChat(chatRecord);
        for (MessagesRecord m : getAllMessages())
            if(m.getIdChat().equals(messages.getIdChat()))
                messagesRecords.add(m);
        return messagesRecords;
    }

    //TODO Modificare metodo
    //Aggiungo un messaggio a una determinata chat
    public boolean addMessages(MessagesRecord messagesRecord, int idChat) {
        if (messagesRecord.getGestorePiattaforma().getRuolo().equals(Role.staff) || messagesRecord.getGestorePiattaforma().getRuolo().equals(Role.amministratore)) {
            if(messagesRecord.getUser().getRuolo().equals(Role.utente)){
                //Controllo se la chat non esiste, in tal caso la creo e aggiungo il messaggio
                if (!chatRepository.existsById(idChat)) {
                    ChatRecord chatRecord = new ChatRecord();
                    chatRecord.setId(idChat);
                    chatRepository.save(chatRecord);
                }
                messagesRecord.setIdChat(chatRepository.findById(idChat).get());
                messagesRecord.setData(LocalDateTime.now());
                messagesRepository.save(messagesRecord);

                //Controllo se la chat assegnata a un assistente non esiste, in tal caso la creo e la aggiungo
                if(!assistanceRepository.existsById(chatRepository.findById(idChat).get().getId())) {
                    AssistanceRecord assistanceRecord = new AssistanceRecord();
                    assistanceRecord.setStaff(messagesRecord.getGestorePiattaforma());
                    assistanceRecord.setIdChat(chatRepository.findById(idChat).get());
                    assistanceRepository.save(assistanceRecord);
                }
                return true;
            }
        }
        return false;
    }
}
