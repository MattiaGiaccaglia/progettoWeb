package progettoWeb.Assistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssistanceController {

    @Autowired
    AssistanceService assistanceService;

    //Richiedo tutte le assistenze fatte
    @RequestMapping("/api/getAssistance")
    public ResponseEntity<Object> getAllAssistant(){
        List<AssistanceRecord> assistanceRecords = assistanceService.getAllAssistance();
        if(assistanceRecords.isEmpty())
            return new ResponseEntity<>("Nessun assistenza presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(assistanceRecords, HttpStatus.OK);
    }

    //Richiedo tutte le assistenze fatte da un determinato Staff
    @GetMapping("/api/getAssistanceByStaff/{id}")
    public ResponseEntity<Object> getAllAssistant(@PathVariable("id") int id){
        List<AssistanceRecord> assistanceRecords = assistanceService.getAllAssistanceByStaff(id);
        if(assistanceRecords.isEmpty())
            return new ResponseEntity<>("Nessun assistenza effettuata da questo staff", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(assistanceRecords, HttpStatus.OK);
    }
}
