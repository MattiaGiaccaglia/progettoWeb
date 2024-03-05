package progettoWeb.Store;

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
    public List<StoreRecord> getAllStore(){
        return storeService.getAllStore();
    }

    //Restituisco uno specifico store
    @GetMapping("/api/store/{id}")
    public ResponseEntity<Optional<StoreRecord>> getStore(@PathVariable("id") String id){
        Optional<StoreRecord> store = null;
        try {
            store = storeService.getStore(Integer.parseInt(id));
        }catch (Exception e){
            //In caso non dovesse essere presente, non restituisco nulla
            return new ResponseEntity<>(store, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    //Aggiungo un nuovo store
    @RequestMapping(value="/api/addStore", method= RequestMethod.POST)
    public ResponseEntity<String> addStore(@RequestBody StoreRecord store) {
        UserRecord venditore = store.getProprietario();
        List<UserRecord> dipendenti = store.getDipendenti();
        //Assegno a tutti i dipendenti dello store il ruolo di dipendenti
        for (UserRecord dipendente: dipendenti) {
            dipendente.setRuolo(Role.dipendente); //TODO controllare che l'utente non sia già venditore in un altro negozio
            userService.modifyUser(dipendente);
        }
        //Se il ruolo del venditore è Role.venditore, aggiungo lo store, altrimenti no
        if (venditore.getRuolo().compareTo(Role.venditore) == 0 && storeService.addStore(store))
                return new ResponseEntity<>("Negozio aggiunto correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Non è possibile aggiungere il negozio", HttpStatus.BAD_REQUEST);
    }

    //Aggiungo un dipendente allo store
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
            dipendenti.add(dipendente);

            //Creo store da modificare
            StoreRecord newStore = new StoreRecord();
            newStore.setId(store.get().getId());
            newStore.setNome(store.get().getNome());
            newStore.setProprietario(store.get().getProprietario());
            newStore.setDipendenti(dipendenti);
            newStore.setProgramma(store.get().getProgramma());

            //Modifico il vecchio store con il nuovo appena creato
            storeService.modifyStore(newStore);
            return new ResponseEntity<>("Dipendente aggiunto correttamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Non è possibile aggiungere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }

    //Rimuovo un dipendente da un store
    @RequestMapping(value="/api/removeEmployee/{idstore}/{iduser}", method= RequestMethod.POST)
    public ResponseEntity<String> removeEmployee(@PathVariable("idstore") String idstore, @PathVariable("iduser") String iduser){
        try {
            Optional<StoreRecord> store = storeService.getStore(Integer.parseInt(idstore));
            List<UserRecord> dipendenti = store.get().getDipendenti();

            //Cerco il dipendente, lo rimuovo dalla lista dei dipendenti e gli assegno il ruolo di utente
            for (UserRecord user : dipendenti) {
                if (user.getId() == Integer.parseInt(iduser)) {
                    dipendenti.remove(user);
                    user.setRuolo(Role.utente);
                }
            }

            //Creo store da modificare
            StoreRecord newStore = new StoreRecord();
            newStore.setId(store.get().getId());
            newStore.setNome(store.get().getNome());
            newStore.setProprietario(store.get().getProprietario());
            newStore.setDipendenti(dipendenti);
            newStore.setProgramma(store.get().getProgramma());

            //Modifico il vecchio store con il nuovo appena creato
            storeService.modifyStore(newStore);

            return new ResponseEntity<>("Dipendente rimosso correttamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Non è possibile rimuovere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }
}
