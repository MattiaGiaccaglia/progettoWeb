package progettoWeb.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Assistence.AssistanceRepository;
import progettoWeb.IdChat.IdChatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private IdChatRepository idChatRepository;
    @Autowired
    AssistanceRepository assistanceRepository;

    //Restituisco tutti i messaggi
    public List<MessagesRecord> getAllMessages() {
        List<MessagesRecord> messagesRecords = new ArrayList<>();
        messagesRepository.findAll().forEach(messagesRecords::add);
        return messagesRecords;
    }



}
