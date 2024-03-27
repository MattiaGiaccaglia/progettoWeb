package progettoWeb.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    private MessagesService messagesService;

    //Restituisco tutti i messaggi
    @GetMapping("/getMessages")
    public ResponseEntity<Object> getAllMessages(){
        List<MessagesRecord> messagesRecords = messagesService.getAllMessages();
        if(!messagesRecords.isEmpty())
            return new ResponseEntity<>(messagesRecords, HttpStatus.OK);
        return new ResponseEntity<>("Non ci sono messaggi da mostrare", HttpStatus.NOT_FOUND);
    }

    //Restituisco tutti i messaggi di una determinata chat
    @GetMapping("/getMessagesByIDChat/{idChat}")
    public ResponseEntity<Object> getMessagesByIDChat(@PathVariable("idChat") int idChat){
        List<MessagesRecord> messagesRecords = messagesService.getAllMessagesByIDChat(idChat);
        if(!messagesRecords.isEmpty())
            return new ResponseEntity<>(messagesRecords, HttpStatus.OK);
        return new ResponseEntity<>("Non ci sono messaggi da mostrare per questa chat", HttpStatus.NOT_FOUND);
    }

    //Aggiungo un messaggio a una determinata chat
    @PostMapping("/addMessages")
    public ResponseEntity<String> addMessages(@RequestBody MessagesRecord messages){
        if(messagesService.addMessages(messages))
            return new ResponseEntity<>("Messaggio inviato correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile inviare messaggio, controllare i dati inseriti e riprovare.", HttpStatus.BAD_REQUEST);
    }
}
