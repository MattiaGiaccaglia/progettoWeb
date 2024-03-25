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

    //Rimuovo uno store
    public void removeStore(int id){
        storeRepository.deleteById(this.getStore(id).getId());
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
        if (!proprietario.equals(storeRecords.getProprietario()) || proprietario.getRuolo() != Role.venditore)
            return false;
        //Se proprietario ha già negozio in un luogo, non aggiungo
        List<StoreRecord> storeRecords1 = this.getStoreByVendor(proprietario.getId());
        for(StoreRecord s : storeRecords1)
            if(s.getLuogo().equals(storeRecords.getLuogo()))
                return false;
        //Cambio ruolo agli utenti in Role.dipendente, se presenti al momento della creazione
        List<UserRecord> dipendenti = storeRecords.getDipendenti();
        if (dipendenti != null)
            for (UserRecord dipendente : dipendenti) {
                dipendente.setRuolo(Role.dipendente);
                userService.modifyUser(dipendente);
            }
        //Salvo store
        storeRepository.save(storeRecords);
        return true;
    }

    //Modifico uno store già esistente
    public boolean modifyStore(StoreRecord storeRecords){
        if(storeRepository.existsById(storeRecords.getId())) {
            storeRepository.save(storeRecords);
            return true;
        }
        return false;
    }

}
