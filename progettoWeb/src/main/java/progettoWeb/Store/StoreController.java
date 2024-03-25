package progettoWeb.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private UserService userService;

    //Restituisco tutti gli store
    @RequestMapping("/api/getStores")
    public ResponseEntity<Object> getAllStores() {
        List<StoreRecord> storeRecords = storeService.getAllStore();
        if (storeRecords.isEmpty())
            return new ResponseEntity<>("Non ci sono store registrati", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(storeRecords, HttpStatus.OK);
    }

    //Restituisco uno store specifico
    @GetMapping("/api/store/{id}")
    public ResponseEntity<Object> getStore(@PathVariable("id") int id) {
        StoreRecord store = storeService.getStore(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    //Aggiungo store
    @RequestMapping(value="/api/addStore", method= RequestMethod.POST)
    public ResponseEntity<String> addStore(@RequestBody StoreRecord store) {
        if (storeService.addStore(store))
            return new ResponseEntity<>("Store aggiunto correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile aggiungere Store, controllare i dati inseriti e riprovare.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Aggiungo dipendente a uno store
    @RequestMapping(value="/api/addEmployee/{idstore}/{iduser}", method= RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@PathVariable("idstore") String idstore, @PathVariable("iduser") String iduser) {
        try{
            StoreRecord store = storeService.getStore(Integer.parseInt(idstore));
            List<UserRecord> dipendenti = store.getDipendenti();
            UserRecord user = userService.getUser(Integer.parseInt(iduser));

            //Creo il dipendente
            UserRecord dipendente = new UserRecord();
            dipendente.setId(user.getId());
            dipendente.setNome(user.getNome());
            dipendente.setCognome(user.getCognome());
            dipendente.setEmail(user.getEmail());
            dipendente.setUsername(user.getUsername1());
            dipendente.setTelefono(user.getTelefono());
            dipendente.setPassword(user.getPassword1());
            dipendente.setRuolo(Role.dipendente);
            //Aggiungo dipendente
            userService.aggiungiUtente(dipendente);
            dipendenti.add(dipendente);

            //Creo nuovo store
            StoreRecord newstore = new StoreRecord();
            newstore.setId(store.getId());
            newstore.setNome(store.getNome());
            newstore.setProprietario(store.getProprietario());
            newstore.setDipendenti(dipendenti);
            newstore.setProgramma(store.getProgramma());

            //Rimpiazzo lo store vecchio con quello nuovo appena creato
            storeService.modifyStore(newstore);

            return new ResponseEntity<>("Dipendente aggiunto correttamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Non è possibile aggiungere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }

    //rimuovo un dipendente da uno store
    @RequestMapping(value="/api/removeEmployee/{idstore}/{iduser}", method= RequestMethod.POST)
    public ResponseEntity<String> removeEmployee(@PathVariable("idstore") String idstore, @PathVariable("iduser") String iduser) {

        try {
            StoreRecord store = storeService.getStore(Integer.parseInt(idstore));
            List<UserRecord> dipendenti = store.getDipendenti();

            //Trovo il dipendente da rimuovere e gli assegno il ruolo di utente
            for (UserRecord user : dipendenti) {
                if (user.getId() == Integer.parseInt(iduser)) {
                    dipendenti.remove(user);
                    user.setRuolo(Role.utente);
                    userService.aggiungiUtente(user);
                }
            }

            //Creo nuovo store
            StoreRecord newstore = new StoreRecord();
            newstore.setId(store.getId());
            newstore.setNome(store.getNome());
            newstore.setProprietario(store.getProprietario());
            newstore.setDipendenti(dipendenti);
            newstore.setProgramma(store.getProgramma());

            //Rimpiazzo lo store vecchio con quello nuovo appena creato
            storeService.modifyStore(newstore);

            return new ResponseEntity<>("Dipendente rimosso correttamente", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Non è possibile rimuovere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }

}
