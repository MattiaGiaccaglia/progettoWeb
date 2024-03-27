package progettoWeb.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Messages.MessagesRecord;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepository ChatRepository;

    //Restituisco tutte le chat
    public List<ChatRecord> getAllChats(){
        List<ChatRecord> chatRecords = new ArrayList<>();
        ChatRepository.findAll().forEach(chatRecords::add);
        return chatRecords;
    }

    //Restituisco una chat a partire da un ID
    public ChatRecord getChat(int id) {
        return ChatRepository.findById(id)
                .orElseThrow(() -> new ChatException.ChatExceptionNotFound
                        ("Nessuna Chat associata a questo id: " + id));
    }

    //Aggiungo chat
    public void addChat(ChatRecord chatRecord){
        ChatRepository.save(chatRecord);
    }
}
