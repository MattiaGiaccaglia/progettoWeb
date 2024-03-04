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

import java.util.Optional;

@RestController
public class AdministratorController {

    @Autowired
    private UserService userService;

    //Metodo utilizzato per modificare il ruolo di un utente da parte dell'amministratore
    @RequestMapping(value = "/api/modifyUserRole/{id}/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> modifyUserRole(@PathVariable("id") String id, @PathVariable("role") String role) {
        Optional<UserRecord> user = userService.getUser(Integer.parseInt(id));
        //TODO controllare che la richiesta sia fatta da un admin
        //Creo utente uguale a quello precedente eccetto per il ruolo
        UserRecord utente = new UserRecord();
        utente.setId(user.get().getId());
        utente.setNome(user.get().getNome());
        utente.setCognome(user.get().getCognome());
        utente.setEmail(user.get().getEmail());
        utente.setUsername(user.get().getUsername());
        utente.setTelefono(user.get().getTelefono());
        utente.setPassword(user.get().getPassword());
        utente.setRuolo(Role.valueOf(role));
        //utente.setVisite(user.get().getVisite());
        //Cambio vecchio utente con utente con ruolo modificato
        userService.modifyUser(utente);
        return new ResponseEntity<>("Utente modificato correttamente", HttpStatus.CREATED);
    }
}
