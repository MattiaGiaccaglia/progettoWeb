package progettoWeb.Store;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private UserService userService;

    //Restituisco tutti gli store
    @RequestMapping("/api/getStores")
    public List<StoreRecord> getAllStore() {
        return storeService.getAllStore();
    }

    //Restituisco uno store specifico
    @GetMapping("/api/store/{id}")
    public ResponseEntity<Object> getStore(@PathVariable("id") String id) {
        Optional<StoreRecord> store = null;
        try {
            store = storeService.getStore(Integer.parseInt(id));
        }catch (Exception e){
            return new ResponseEntity<>("Impossibile restituire store, non esiste", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    //Aggiungo store
    @RequestMapping(value="/api/addStore", method= RequestMethod.POST)
    public ResponseEntity<String> addStore(@RequestBody StoreRecord store) {
        UserRecord venditore = store.getProprietario();
        List<UserRecord> dipendenti = store.getDipendenti();
        //Assegno il ruolo di dipendente a tutti gli utenti aggiunti allo store
        for (UserRecord dipendente: dipendenti) {
            dipendente.setRuolo(Role.dipendente); //TODO controllare che l'utente non sia già venditore in un altro negozio
            userService.modifyUser(dipendente);
        }
        //Aggiungo venditore
        if (venditore.getRuolo().compareTo(Role.venditore) == 0){
            if(storeService.addStore(store))
                return new ResponseEntity<>("Negozio aggiunto correttamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Non è possibile aggiungere il negozio", HttpStatus.BAD_REQUEST);
    }

    //Aggiungo dipendente a uno store
    @RequestMapping(value="/api/addEmployee/{idstore}/{iduser}", method= RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@PathVariable("idstore") String idstore, @PathVariable("iduser") String iduser) {

        try{
            Optional<StoreRecord> store = storeService.getStore(Integer.parseInt(idstore));
            List<UserRecord> dipendenti = store.get().getDipendenti();
            Optional<UserRecord> user = userService.getUser(Integer.parseInt(iduser));

            //Creo il dipendente
            UserRecord dipendente = new UserRecord();
            dipendente.setId(user.get().getId());
            dipendente.setNome(user.get().getNome());
            dipendente.setCognome(user.get().getCognome());
            dipendente.setEmail(user.get().getEmail());
            dipendente.setUsername(user.get().getUsername());
            dipendente.setTelefono(user.get().getTelefono());
            dipendente.setPassword(user.get().getPassword());
            dipendente.setRuolo(Role.dipendente);
            //Aggiungo dipendente
            userService.aggiungiUtente(dipendente);
            dipendenti.add(dipendente);

            //Creo nuovo store
            StoreRecord newstore = new StoreRecord();
            newstore.setId(store.get().getId());
            newstore.setNome(store.get().getNome());
            newstore.setProprietario(store.get().getProprietario());
            newstore.setDipendenti(dipendenti);
            newstore.setProgramma(store.get().getProgramma());

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

        try{
            Optional<StoreRecord> store = storeService.getStore(Integer.parseInt(idstore));
            List<UserRecord> dipendenti = store.get().getDipendenti();

            //Trovo il dipendente da rimuovere e gli assegno il ruolo di utente
            for (UserRecord user: dipendenti) {
                if(user.getId() == Integer.parseInt(iduser)){
                    dipendenti.remove(user);
                    user.setRuolo(Role.utente);
                    userService.aggiungiUtente(user);
                }
            }

            //Creo nuovo store
            StoreRecord newstore = new StoreRecord();
            newstore.setId(store.get().getId());
            newstore.setNome(store.get().getNome());
            newstore.setProprietario(store.get().getProprietario());
            newstore.setDipendenti(dipendenti);
            newstore.setProgramma(store.get().getProgramma());

            //Rimpiazzo lo store vecchio con quello nuovo appena creato
            storeService.modifyStore(newstore);

            return new ResponseEntity<>("Dipendente rimosso correttamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Non è possibile rimuovere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }

}
