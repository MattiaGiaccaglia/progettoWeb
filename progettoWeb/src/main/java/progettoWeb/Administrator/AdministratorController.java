package progettoWeb.Administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    //Metodo utilizzato per modificare il ruolo di un utente da parte dell'amministratore
    @PutMapping("/modifyUserRole/{id}/{role}")
    @PreAuthorize("hasRole('staff')")
    public ResponseEntity<String> modifyUserRole(@PathVariable("id") int id, @PathVariable("role") String role) {
        //TODO controllare che la richiesta sia fatta da un admin
        administratorService.modifyUserRole(id, role);
        return new ResponseEntity<>("Ruolo utente modificato correttamente.", HttpStatus.CREATED);
    }
}
