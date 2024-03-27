package progettoWeb.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.User.UserRecord;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    //Restituisco tutti gli store
    @GetMapping("/getAllStores")
    public ResponseEntity<Object> getAllStores() {
        List<StoreRecord> storeRecords = storeService.getAllStore();
        if (storeRecords.isEmpty())
            return new ResponseEntity<>("Non ci sono store registrati.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(storeRecords, HttpStatus.OK);
    }

    //Restituisco uno store specifico
    @GetMapping("/getStore/{id}")
    public ResponseEntity<Object> getStore(@PathVariable("id") int id) {
        StoreRecord store = storeService.getStore(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    //Aggiungo store
    @PostMapping("/addStore")
    public ResponseEntity<String> addStore(@RequestBody StoreRecord store) {
        if (storeService.addStore(store))
            return new ResponseEntity<>("Store aggiunto correttamente.", HttpStatus.CREATED);
        return new ResponseEntity<>("Impossibile aggiungere Store, controllare i dati inseriti e riprovare.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Aggiungo dipendente a uno store
    @PostMapping("/addEmployee/{id}")
    public ResponseEntity<String> addEmployee(@RequestBody UserRecord userRecord, @PathVariable("id") int id) {
        if(storeService.addEmployee(userRecord, id))
            return new ResponseEntity<>("Dipendente aggiunto correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile aggiungere dipendente, controllare i dati inseriti e riprovare.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Rimuovo dipendente da uno store
    @DeleteMapping("/removeEmployee/{id}")
    public ResponseEntity<String> removeEmployee(@RequestBody UserRecord userRecord, @PathVariable("id") int id) {
        if(storeService.removeEmployee(userRecord, id))
            return new ResponseEntity<>("Dipendente rimosso correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile rimuovere dipendente, controllare i dati inseriti e riprovare.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Restituisco lista dipendenti di uno Store
    @GetMapping("/getEmployees/{id}")
    public ResponseEntity<Object> getEmployees(@PathVariable("id") int id) {
        List<UserRecord> userRecords = storeService.getEmployees(id);
        if(userRecords.isEmpty())
            return new ResponseEntity<>("Lo Store non ha dipendenti.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userRecords, HttpStatus.OK);
    }

    //Rimuovo uno Store
    @DeleteMapping("/removeStore/{id}")
    public ResponseEntity<String> removeStore(@PathVariable("id") int id) {
        storeService.removeStore(id);
        return new ResponseEntity<>("Store rimosso correttamente.", HttpStatus.OK);
    }

}
