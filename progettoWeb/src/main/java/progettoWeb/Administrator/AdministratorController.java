package progettoWeb.Administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    //Metodo utilizzato per modificare il ruolo di un utente da parte dell'amministratore
    @PutMapping("/modifyUserRole/{id}/{role}")
    public ResponseEntity<String> modifyUserRole(@PathVariable("id") int id, @PathVariable("role") String role) {
        administratorService.modifyUserRole(id, role);
        return ResponseEntity.ok().body("{\"message\": \"Ruolo aggiornato correttamente.\"}");
    }
}
