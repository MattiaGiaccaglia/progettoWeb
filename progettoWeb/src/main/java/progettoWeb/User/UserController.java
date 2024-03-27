package progettoWeb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.Authentication.Auth.AuthenticationRequest;
import progettoWeb.Authentication.Auth.AuthenticationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //Login utenti
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    //Registrazione utenti nuovi
    @PostMapping("/registrazione")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserRecord userRecord){
        return new ResponseEntity<>(userService.aggiungiUtente(userRecord), HttpStatus.OK);
    }

    //Restituisco lista completa degli utenti
    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers(){
        List<UserRecord> userRecords = userService.getAllUsers();
        if(userRecords.isEmpty())
            return new ResponseEntity<>("Non ci sono utenti registrati.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userRecords, HttpStatus.OK);
    }

    //Restituisco utente dal suo id
    @GetMapping("/getUser/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id){
        UserRecord userRecord = userService.getUser(id);
        return new ResponseEntity<>(userRecord, HttpStatus.OK);
    }

    //Elimino utenti
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Utente eliminato correttamente.", HttpStatus.OK);
    }

    //Modifico utente
    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUser(@RequestBody UserRecord userRecord){
        userService.modifyUser(userRecord);
        return new ResponseEntity<>("Utente modificato correttamente.", HttpStatus.OK);
    }
}
