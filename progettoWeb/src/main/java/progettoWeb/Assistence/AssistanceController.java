package progettoWeb.Assistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.User.Role;
import progettoWeb.User.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/assistance")
public class AssistanceController {

    @Autowired
    AssistanceService assistanceService;
    @Autowired
    UserService userService;

    //Restituisco tutte le assistenze fatte
    @RequestMapping("/getAssistance")
    public ResponseEntity<Object> getAllAssistant(){
        List<AssistanceRecord> assistanceRecords = assistanceService.getAllAssistance();
        if(assistanceRecords.isEmpty())
            return new ResponseEntity<>("Nessun assistenza presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(assistanceRecords, HttpStatus.OK);
    }

    //Restituisco assistenza a partire dal suo ID
    @GetMapping("/getAssistanceById/{id}")
    public ResponseEntity<AssistanceRecord> getAssistantById(@PathVariable("id") int id){
        AssistanceRecord assistanceRecord = assistanceService.getAssistanceById(id);
        return new ResponseEntity<>(assistanceRecord, HttpStatus.OK);
    }

    //Richiedo tutte le assistenze fatte da un determinato Staff
    @GetMapping("/getAssistanceByStaff/{id}")
    public ResponseEntity<Object> getAllAssistant(@PathVariable("id") int id){
        if(!userService.getUser(id).getRuolo().equals(Role.staff))
            return new ResponseEntity<>("L'utente selezionato non è uno staff. Si prega di inserire un ID valido.", HttpStatus.BAD_REQUEST);
        List<AssistanceRecord> assistanceRecords = assistanceService.getAllAssistanceByStaff(id);
        if(assistanceRecords.isEmpty())
            return new ResponseEntity<>("Nessun assistenza effettuata da questo staff.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(assistanceRecords, HttpStatus.OK);
    }

    //Elimino Assistenza
    // TODO Valutare inserimento delete per quanto riguarda assistenza
    @DeleteMapping("/deleteAssistance/{id}")
    public ResponseEntity<String> deleteAssistance(@PathVariable("id") int id){
        assistanceService.deleteAssistance(id);
        return new ResponseEntity<>("Assistenza eliminata correttamente", HttpStatus.OK);
    }

}
