package progettoWeb.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    //Restituisco tutti gli store presenti
    public List<StoreRecord> getAllStore(){
        List<StoreRecord> storeRecords = new ArrayList<>();
        storeRepository.findAll().forEach(storeRecords::add);
        return storeRecords;
    }

    //Metodo per restituire uno specifico store
    public Optional<StoreRecord> getStore(int id) {
        return storeRepository.findById(id);
    }

    //Aggiungo un nuovo store, controllando che prima non esisti già
    public boolean addStore(StoreRecord storeRecords){
        if(!storeRepository.existsById(storeRecords.getId())) {
            storeRepository.save(storeRecords);
            return true;
        }
        return false;
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
