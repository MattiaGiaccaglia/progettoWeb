package progettoWeb.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    //Restituisco tutti i messaggi
    @RequestMapping(value = "/api/getMessages")
    public ResponseEntity<Object> getAllMessages(){
        List<MessagesRecord> messagesRecords = messagesService.getAllMessages();
        if(!messagesRecords.isEmpty())
            return new ResponseEntity<>(messagesRecords, HttpStatus.OK);
        return new ResponseEntity<>("Non ci sono messaggi da mostrare", HttpStatus.OK);
    }

    //Restituisco tutti i messaggi di una determinata chat
    @RequestMapping(value = "/api/getMessagesByIDChat/{idChat}")
    public ResponseEntity<Object> getMessagesByIDChat(@PathVariable("idChat") int idChat){
        List<MessagesRecord> messagesRecords = messagesService.getAllMessagesByIDChat(idChat);
        if(!messagesRecords.isEmpty())
            return new ResponseEntity<>(messagesRecords, HttpStatus.OK);
        return new ResponseEntity<>("Non ci sono messaggi da mostrare per questa chat", HttpStatus.OK);
    }

    //Aggiungo un messaggio a una determinata chat
    @RequestMapping(value = "/api/addMessages/{idChat}", method= RequestMethod.POST)
    public ResponseEntity<String> addMessages(@RequestBody MessagesRecord messages, @PathVariable("idChat") int idChat){
        if(messagesService.addMessages(messages, idChat))
            return new ResponseEntity<>("Messaggio inviato correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile inviare messaggio", HttpStatus.BAD_REQUEST);
    }
}
