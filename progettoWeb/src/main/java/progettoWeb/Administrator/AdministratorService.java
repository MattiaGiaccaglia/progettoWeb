package progettoWeb.Administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

@Service
public class AdministratorService {
    @Autowired
    UserService userService;

    //Modifico ruolo di un utente
    public void modifyUserRole(int id, String ruolo) {
        UserRecord userRecord = userService.getUser(id);
        userRecord.setRuolo(Role.valueOf(ruolo));
        userService.save(userRecord);
    }
}
