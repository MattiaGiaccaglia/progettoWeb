package progettoWeb.Assistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssistanceService {
    @Autowired
    AssistanceRepository assistanceRepository;
    @Autowired
    UserRepository userRepository;

    //Restituisco tutte le Assistenze che sono state fatte
    public List<AssistanceRecord> getAllAssistance(){
        List<AssistanceRecord> assistanceRecords = new ArrayList<>();
        assistanceRepository.findAll().forEach(assistanceRecords::add);
        return assistanceRecords;
    }

    //Restituisco tutte le Assistenze che sono state fatte da un determinato Staff
    public List<AssistanceRecord> getAllAssistanceByStaff(int id){
        UserRecord Staff = new UserRecord();
        Staff.setId(id);
        List<AssistanceRecord> assistanceRecords = new ArrayList<>();
        //Se lo staff non esiste, restituisco array vuoto
        if(!userRepository.existsById(id))
            return assistanceRecords;
        //per un determinato Staff, aggiungo tutte le assistenze che sono state fatte da lui in un array e lo restituisco
        for (AssistanceRecord A: getAllAssistance()) {
            if(A.getStaff().getId() == Staff.getId())
                assistanceRecords.add(A);
        }
        return assistanceRecords;
    }
}
