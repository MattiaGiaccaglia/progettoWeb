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
import java.util.Optional;

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
        List<UserRecord>UserRecords = new ArrayList<>();
        userRepository.findAll().forEach(UserRecords::add);
        return UserRecords;
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
            return AuthenticationResponse.builder().token("").build();
        }
        //create the user
        UserRecord user = new UserRecord();
        user.setNome(userRecord.getNome());
        user.setCognome(userRecord.getCognome());
        user.setUsername(userRecord.getUsername1());
        user.setEmail(userRecord.getEmail());
        user.setTelefono(userRecord.getTelefono());
        user.setRuolo(Role.utente); //the default role is user
        user.setPassword(passwordEncoder.encode(userRecord.getPassword1()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user); //generate jwt token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    //Restituisco utente a partire dall'ID
    public Optional<UserRecord> getUser(int id) {
        return userRepository.findById(id);
    }

    public void modifyUser(UserRecord userRecord){
        userRepository.save(userRecord);
    }


    //Restituisco utente a partire dal suo Username
    public Optional<UserRecord> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Elimino utente cercandolo tramite la sua email
    public void eliminaUtente(String email){
        Optional<UserRecord> user = userRepository.findByEmail(email);
        int id = user.get().getId();
        userRepository.deleteById(id);
    }

    public void eliminaUtenteByID(int ID){
        userRepository.deleteById(ID);
    }
}
