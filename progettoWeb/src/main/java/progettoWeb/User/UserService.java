package progettoWeb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //restituisco lista completa degli utenti
    public List<UserRecord> getAllUsers(){
        List<UserRecord>UserRecords = new ArrayList<>();
        userRepository.findAll().forEach(UserRecords::add);
        return UserRecords;
    }

    //Aggiungo un utente
    public void aggiungiUtente(UserRecord userRecord){
        userRepository.save(userRecord);
    }

    //Restituisco utente a partire dall'ID
    public Optional<UserRecord> getUser(int id) {
        return userRepository.findById(id);
    }

    public void modifyUser(UserRecord userRecord){
        userRepository.save(userRecord);
    }


    //Restituisco utente a partire dal sup Username
    public Optional<UserRecord> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Elimino utente cercandolo tramite la sua email
    public void eliminaUtente(String email){
        UserRecord user = userRepository.findByEmail(email);
        int id = user.getId();
        userRepository.deleteById(id);
    }

    public void eliminaUtenteByID(int ID){
        userRepository.deleteById(ID);
    }
}
