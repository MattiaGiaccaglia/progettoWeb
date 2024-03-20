package progettoWeb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.Authentication.Auth.AuthenticationRequest;
import progettoWeb.Authentication.Auth.AuthenticationResponse;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Login utenti
    @PostMapping(value = "/api/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    //Registrazione utenti nuovi
    @PostMapping(value = "/registrazione")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserRecord userRecord){
        return new ResponseEntity<>(userService.aggiungiUtente(userRecord), HttpStatus.OK);
    }

    //Restituisco lista completa degli utenti
    @RequestMapping(value = "/getUser")
    public ResponseEntity<Object> getAllUser(){
        List<UserRecord> userRecords = userService.getAllUsers();
        if(userRecords.isEmpty())
            return new ResponseEntity<>("Non ci sono utenti registrati", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userRecords, HttpStatus.OK);
    }

    //Elimino utenti a partire dalla email
    @DeleteMapping(value = "/elimina/{email}")
    public ResponseEntity<String> eliminaUtente(@PathVariable("email") String email) {
        try {
            userService.eliminaUtente(email);
        } catch (Exception e) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Utente eliminato correttamente", HttpStatus.OK);
    }

    /*@DeleteMapping(value = "/delete/{ID}")
    public ResponseEntity<String> eliminaUtenteByID(@PathVariable("ID") Integer ID) {
        userService.eliminaUtenteByID(ID);
        return new ResponseEntity<>("Utente eliminato correttamente", HttpStatus.OK);
    }*/
}
