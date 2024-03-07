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

    //rimuovo uno store
    @RequestMapping("/api/removeStores/{id}")
    public ResponseEntity<String> removeStore(@PathVariable("id") String id){
        if(!storeService.getStore(Integer.parseInt(id)).isPresent())
            return new ResponseEntity<>("Impossibile eliminare lo store", HttpStatus.BAD_REQUEST);
        try {
            storeService.removeStore(Integer.parseInt(id));
            for (UserRecord u : userService.getAllUsers()){
                if(u.getDipendente() == Integer.parseInt(id)) {
                    UserRecord user = new UserRecord();
                    user.setId(u.getId());
                    user.setNome(u.getNome());
                    user.setCognome(u.getCognome());
                    user.setEmail(u.getEmail());
                    user.setUsername(u.getUsername());
                    user.setTelefono(u.getTelefono());
                    user.setPassword(u.getPassword());
                    user.setRuolo(Role.utente);
                    user.setDipendente(0);
                    userService.aggiungiUtente(user);
                }
            }
            return new ResponseEntity<>("Store eliminato correttamente", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Impossibile eliminare lo store", HttpStatus.BAD_REQUEST);
        }
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
        //Se il ruolo del venditore è Role.venditore, aggiungo lo store, altrimenti no
        if (venditore.getRuolo().compareTo(Role.venditore) == 0){
            if(storeService.addStore(store))
                return new ResponseEntity<>("Negozio aggiunto correttamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Non è possibile aggiungere il negozio", HttpStatus.BAD_REQUEST);
    }

    //Aggiungo un dipendente allo store
    @RequestMapping(value="/api/addEmployee/{idstore}/{iduser}", method= RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@PathVariable("idstore") String idstore, @PathVariable("iduser") String iduser) {
        try {
            Optional<StoreRecord> storeID = storeService.getStore(Integer.parseInt(idstore));
            if (storeService.getStore(Integer.parseInt(idstore)).isPresent()) {
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
                dipendente.setDipendente(Integer.parseInt(idstore));
                userService.aggiungiUtente(dipendente);

                return new ResponseEntity<>("Dipendente aggiunto correttamente", HttpStatus.OK);
            }
            return new ResponseEntity<>("Non è possibile aggiungere il dipendente, lo store non esiste", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>("Non è possibile aggiungere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }

    //Rimuovo un dipendente da un store
    @RequestMapping(value="/api/removeEmployee/{idstore}/{iduser}", method= RequestMethod.POST)
    public ResponseEntity<String> removeEmployee(@PathVariable("idstore") String idstore, @PathVariable("iduser") String iduser){
        try {
            int idStore = Integer.parseInt(idstore);
            int idUser = Integer.parseInt(iduser);
            Optional<UserRecord> user = userService.getUser(Integer.parseInt(iduser));
            if (storeService.getStore(Integer.parseInt(idstore)).isPresent()){
                if(storeService.getStore(idStore).get().getId() == userService.getUser(idUser).get().getDipendente()) {
                    //Creo il dipendente
                    UserRecord dipendente = new UserRecord();
                    dipendente.setId(user.get().getId());
                    dipendente.setNome(user.get().getNome());
                    dipendente.setCognome(user.get().getCognome());
                    dipendente.setEmail(user.get().getEmail());
                    dipendente.setUsername(user.get().getUsername());
                    dipendente.setTelefono(user.get().getTelefono());
                    dipendente.setPassword(user.get().getPassword());
                    dipendente.setRuolo(Role.utente);
                    dipendente.setDipendente(0);
                    userService.aggiungiUtente(dipendente);
                    return new ResponseEntity<>("Dipendente rimosso correttamente", HttpStatus.OK);
                }
                return new ResponseEntity<>("Non si puo rimuovere il dipendente di un altro store", HttpStatus.OK);
            }
            return new ResponseEntity<>("Non è possibile rimuovere il dipendente, lo store non esiste", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Non è possibile rimuovere il dipendente", HttpStatus.BAD_REQUEST);
        }
    }
}
