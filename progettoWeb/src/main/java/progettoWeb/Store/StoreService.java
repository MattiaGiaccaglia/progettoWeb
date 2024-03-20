package progettoWeb.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //Rimuovo uno store
    public void removeStore(int id){
        storeRepository.deleteById(id);
    }

    //Metodo per restituire uno specifico store
    public StoreRecord getStore(int id) throws StoreException.StoreExceptionNotFound {
        return storeRepository.findById(id)
                .orElseThrow(() -> new StoreException.StoreExceptionNotFound("Nessuno store presente con il seguente id: " + id));
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
