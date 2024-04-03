package progettoWeb.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    ChatService chatService;

    //Restituisco tutte le chat
    @GetMapping("/getChats")
    public ResponseEntity<Object> getChats() {
        List<ChatRecord> chatRecords = chatService.getAllChats();
        if (chatRecords.isEmpty())
            return new ResponseEntity<>("Nessuna Chat presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chatRecords, HttpStatus.OK);
    }

    //Restituisco una specifica Chat
    @GetMapping("/getChat/{id}")
    public ResponseEntity<Object> getChat(@PathVariable int id) {
        ChatRecord chatRecord = chatService.getChat(id);
        return new ResponseEntity<>(chatRecord, HttpStatus.OK);
    }
}
