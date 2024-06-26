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

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import progettoWeb.Authentication.Auth.AuthenticationRequest;
import progettoWeb.Authentication.Auth.AuthenticationResponse;
import progettoWeb.Authentication.Config.JwtService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //restituisco lista completa degli utenti
    public List<UserRecord> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username errato"));
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    //Aggiungo un utente
    public AuthenticationResponse aggiungiUtente(UserRecord userRecord){
        if(userRecord.getNome().isEmpty() || userRecord.getCognome().isEmpty() || userRecord.getUsername1().isEmpty()
                || userRecord.getPassword1().isEmpty() || userRecord.getPassword1().length() < 8 || userRecord.getEmail().isEmpty() ||
                userRecord.getTelefono().length() != 10){
            throw new RuntimeException("Impossibile effettuare registrazione, dati errati");
        }
        userRecord.setRuolo(Role.utente); //Ruolo di default è Role.utente
        userRecord.setPassword(passwordEncoder.encode(userRecord.getPassword1()));
        save(userRecord);
        var jwtToken = jwtService.generateToken(userRecord); //Genero jwt token
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    //Restituisco utente a partire dall'id
    public UserRecord getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException.UserExceptionNotFound("Nessun utente presente con il seguente id: " + id));
    }

    //Restituisco utente a partire dall'username
    public UserRecord getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException.UserExceptionNotFound("Nessun utente presente con il seguente username: " + username));
    }

    //Modifico utente
    public void modifyUser(UserRecord userRecord){
        UserRecord user = this.getUser(userRecord.getId());

        if(userRecord.getNome().isEmpty() || userRecord.getCognome().isEmpty() || userRecord.getUsername1().isEmpty()
                || userRecord.getPassword1().isEmpty() || userRecord.getEmail().isEmpty())
            throw new IllegalArgumentException
                    ("Non è possibile modificare utente. Non lasciare campi vuoti.");
        if(userRecord.getTelefono().length() != 10)
            throw new IllegalArgumentException
                    ("Non è possibile modificare utente. Il numero di telefono è errato.");
        if(userRecord.getPassword1().length() < 8)
            throw new IllegalArgumentException
                    ("Non è possibile modificare utente. La Password è troppo corta. Almeno 8 caratteri.");
        user.setNome(userRecord.getNome());
        user.setCognome(userRecord.getCognome());
        user.setEmail(userRecord.getEmail());
        user.setTelefono(userRecord.getTelefono());
        if(!user.getPassword1().equals(userRecord.getPassword1()))
            user.setPassword(passwordEncoder.encode(userRecord.getPassword1()));
        user.setUsername(userRecord.getUsername());
        user.setRuolo(user.getRuolo());
        save(user);
    }

    //Salvo utente
    public void save(UserRecord userRecord){
        userRepository.save(userRecord);
    }

    //Elimino utente
    public void deleteUser(int id){
        userRepository.deleteById(this.getUser(id).getId());
    }
}
