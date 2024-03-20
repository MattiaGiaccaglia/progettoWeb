package progettoWeb.Administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;


@RestController
public class AdministratorController {

    @Autowired
    private UserService userService;

    //Metodo utilizzato per modificare il ruolo di un utente da parte dell'amministratore
    @RequestMapping(value = "/api/modifyUserRole/{id}/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> modifyUserRole(@PathVariable("id") int id, @PathVariable("role") String role) {
        UserRecord user = userService.getUser(id);
        //TODO controllare che la richiesta sia fatta da un admin
        //Creo utente uguale a quello precedente eccetto per il ruolo
        UserRecord utente = new UserRecord();
        utente.setId(user.getId());
        utente.setNome(user.getNome());
        utente.setCognome(user.getCognome());
        utente.setEmail(user.getEmail());
        utente.setUsername(user.getUsername1());
        utente.setTelefono(user.getTelefono());
        utente.setPassword(user.getPassword1());
        utente.setRuolo(Role.valueOf(role));
        //utente.setVisite(user.get().getVisite());
        //Cambio vecchio utente con utente con ruolo modificato
        userService.modifyUser(utente);
        return new ResponseEntity<>("Utente modificato correttamente", HttpStatus.CREATED);
    }
}
