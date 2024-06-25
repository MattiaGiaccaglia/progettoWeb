package progettoWeb.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    UserService userService;

    //Restituisco tutti gli store presenti
    public List<StoreRecord> getAllStore(){
        return new ArrayList<>(storeRepository.findAll());
    }

    //Restituisco uno specifico store
    public StoreRecord getStore(int id) throws StoreException.StoreExceptionNotFound {
        return storeRepository.findById(id)
                .orElseThrow(() -> new StoreException.StoreExceptionNotFound("Nessuno store presente con il seguente id: " + id));
    }

    //Restituisco store di un venditore
    public List<StoreRecord> getStoreByVendor(int id) {
        return getAllStore().stream().filter(s -> s.getProprietario().equals(userService.getUser(id))).collect(Collectors.toList());
    }

    //Aggiungo un nuovo store
    public boolean addStore(StoreRecord storeRecords) {
        UserRecord proprietario = userService.getUser(storeRecords.getProprietario().getId());
        //Controllo se è venditore
        if (!proprietario.equals(storeRecords.getProprietario()) || !proprietario.getRuolo().equals(Role.venditore))
            return false;
        //Controllo se il proprietario ha già un negozio nello stesso luogo
        if (getStoreByVendor(proprietario.getId()).stream().anyMatch(s -> s.getLuogo().equals(storeRecords.getLuogo())))
            return false;
        //Cambio ruolo agli utenti in Role.dipendente, se presenti al momento della creazione
        List<UserRecord> dipendenti = storeRecords.getDipendenti();
        if (dipendenti != null)
            dipendenti.forEach(dipendente -> { dipendente.setRuolo(Role.dipendente); userService.save(dipendente); });
        proprietario.setRuolo(Role.venditore);
        userService.save(proprietario);
        //Salvo store
        storeRepository.save(storeRecords);
        return true;
    }

    //Rimuovo uno store
    public void removeStore(int id) {
        StoreRecord storeRecord = this.getStore(id);
        //Cambio ruolo dei dipendenti in Ruolo.utente
        storeRecord.getDipendenti().forEach(d -> { d.setRuolo(Role.utente); userService.save(d); });
        storeRecord.getProprietario().setRuolo(Role.utente);
        //Rimuovo il negozio
        storeRepository.deleteById(id);
    }

    //Restituisco dipendenti di uno store
    public List<UserRecord> getEmployees(int id){
        StoreRecord storeRecord = this.getStore(id);
        return storeRecord.getDipendenti();
    }

    //Aggiungo un dipendente a uno store
    public boolean addEmployee(UserRecord userRecord, int id){
        UserRecord user = userService.getUser(userRecord.getId());
        StoreRecord store = this.getStore(id);
        //Controllo che nella requestBody vengano inseriti i dati corretti
        if(!user.equals(userRecord))
            return false;
        if(user.equals(store.getProprietario()))
            throw new IllegalArgumentException("L'utente inserito è il proprietario del negozio.");
        //Controllo che l'utente non sia già dipendente di questo store
        if (store.getDipendenti().contains(user))
            throw new IllegalArgumentException("L'utente è già dipendente di questo Store.");
        // Controllo che l'utente non sia già dipendente in un altro store
        List<StoreRecord> allStores = this.getAllStore();
        for (StoreRecord s : allStores)
            if (s.getDipendenti().contains(user))
                throw new IllegalArgumentException("L'utente è già dipendente in un altro Store.");
        store.getDipendenti().add(user);
        user.setRuolo(Role.dipendente);
        userService.save(user);
        storeRepository.save(store);
        return true;
    }

    //Rimuovo dipendente da uno store
    public boolean removeEmployee(UserRecord userRecord, int id){
        UserRecord user = userService.getUser(userRecord.getId());
        StoreRecord store = this.getStore(id);
        //Controllo che nella requestBody vengano inseriti i dati corretti
        if(!user.equals(userRecord))
            return false;
        if(!store.getDipendenti().contains(user))
            throw new IllegalArgumentException("L'utente non è dipendente di questo Store.");
        store.getDipendenti().remove(user);
        user.setRuolo(Role.utente);
        userService.save(user);
        storeRepository.save(store);
        return true;
    }

}
