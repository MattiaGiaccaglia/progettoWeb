package progettoWeb.Assistence;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AssistanceRecord> getAllAssistant(){
        return assistanceService.getAllAssistance();
    }

    //Richiedo tutte le assistenze fatte da un determinato Staff
    @GetMapping("/api/getAssistanceByStaff/{id}")
    public List<AssistanceRecord> getAllAssistant(@PathVariable("id") int id){
        return assistanceService.getAllAssistanceByStaff(id);
    }
}
