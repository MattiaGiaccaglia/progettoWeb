package progettoWeb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login/{username}/{password}")
    public ResponseEntity<String> addUtente(@PathVariable("username") String username, @PathVariable("password") String password) {
        for (UserRecord u : userService.getAllUsers())
            if (u.getUsername().equals(userService.getUserByUsername(username)))
                return new ResponseEntity<>("Login effettuato correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Username o password errati", HttpStatus.BAD_REQUEST);
    }

    //Restituisco lista completa degli utenti
    @RequestMapping(value = "/getUser")
    public ResponseEntity<Object> getAllUser(){
        List<UserRecord> userRecords = userService.getAllUsers();
        if(userRecords.isEmpty())
            return new ResponseEntity<>("Non ci sono utenti registrati", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userRecords, HttpStatus.OK);
    }

    //Registrazione utenti nuovi
    @RequestMapping(value = "/registrazione", method = RequestMethod.POST)
    public ResponseEntity<String> aggiungiUtente(@RequestBody UserRecord user) {
        if (user.getRuolo() == null)
            user.setRuolo(Role.valueOf("utente"));
        if (user.getNome().isEmpty() || user.getCognome().isEmpty() || user.getUsername().isEmpty()
                || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getTelefono().isEmpty())
            return new ResponseEntity<>("I campi non possono essere vuoti", HttpStatus.BAD_REQUEST);
        for (UserRecord u : userService.getAllUsers())
            if (u.getEmail().equals(user.getEmail()))
                return new ResponseEntity<>("Email già associata ad un utente registrato", HttpStatus.BAD_REQUEST);
        userService.aggiungiUtente(user);
        return new ResponseEntity<>("Utente aggiunto correttamente", HttpStatus.CREATED);
    }

    //Elimino utenti a partire dalla email
    @DeleteMapping(value = "/elimina/{email}")
    public ResponseEntity<String> eliminaUtente(@PathVariable("email") String email) {
        try {
            userService.eliminaUtente(email);
        } catch (Exception e) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Utente eliminato correttamente", HttpStatus.OK);
    }

    /*@DeleteMapping(value = "/delete/{ID}")
    public ResponseEntity<String> eliminaUtenteByID(@PathVariable("ID") Integer ID) {
        userService.eliminaUtenteByID(ID);
        return new ResponseEntity<>("Utente eliminato correttamente", HttpStatus.OK);
    }*/
}
