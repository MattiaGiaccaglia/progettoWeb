package progettoWeb.Assistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserRepository;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssistanceService {
    @Autowired
    AssistanceRepository assistanceRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    //Restituisco tutte le Assistenze che sono state fatte
    public List<AssistanceRecord> getAllAssistance(){
        List<AssistanceRecord> assistanceRecords = new ArrayList<>();
        assistanceRepository.findAll().forEach(assistanceRecords::add);
        return assistanceRecords;
    }

    //Restituisco una'assistenze cercandola col suo ID
    public AssistanceRecord getAssistanceById(int id){
        return assistanceRepository.findById(id)
                .orElseThrow(() -> new AssistanceException.AssistanceExceptionNotFound("Nessun assistenza presente con il seguente id: " + id));
    }

    //Restituisco tutte le Assistenze che sono state fatte da un determinato Staff
    public List<AssistanceRecord> getAllAssistanceByStaff(int id){
        UserRecord user = userService.getUser(id);
        List<AssistanceRecord> assistanceRecords = new ArrayList<>();
        //per un determinato Staff, aggiungo tutte le assistenze che sono state fatte da lui in un array e lo restituisco
        for (AssistanceRecord A: getAllAssistance())
            if(A.getStaff().getId() == user.getId())
                assistanceRecords.add(A);
        return assistanceRecords;
    }

    //Elimino una assistenza
    public void deleteAssistance(int id){
        assistanceRepository.delete(getAssistanceById(id));
    }
}
