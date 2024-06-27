/*
 * Copyright 2024 Mattia Giaccaglia
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

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
@CrossOrigin(origins = "http://localhost:4200")
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

    //Restituisco utente dal suo username
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username){
        UserRecord userRecord = userService.getUserByUsername(username);
        return new ResponseEntity<>(userRecord, HttpStatus.OK);
    }

    //Elimino utenti
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("{\"message\": \"Utente eliminato correttamente.\"}");
    }

    //Modifico utente
    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUser(@RequestBody UserRecord userRecord){
        userService.modifyUser(userRecord);
        return ResponseEntity.ok().body("{\"message\": \"Utente modificato correttamente.\"}");
    }
}
