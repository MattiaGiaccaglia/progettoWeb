package progettoWeb.FidelityCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FidelityCardService {

    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    @Autowired
    private UserService userService;

    //Restituisco tutte le fidelity card
    public List<FidelityCardRecord> getAllFidelityCard() {
        List<FidelityCardRecord>fidelityCardsRecords = new ArrayList<>();
        fidelityCardRepository.findAll().forEach(fidelityCardsRecords::add);
        return fidelityCardsRecords;
    }

    //Restituisco fidelity card a partire da un ID
    public Optional<FidelityCardRecord> getFidelityCard(int id) {
        return fidelityCardRepository.findById(id);
    }

    public Optional<List<FidelityCardRecord>> getFidelityCardByUserId(int id) {
        List<FidelityCardRecord> fidelityCardRecords = new ArrayList<>();
        Optional<UserRecord> userRecord = userService.getUser(id);

        //Se utente non esiste, restituisco Optional.empty()
        if(userRecord.isEmpty())
            return Optional.empty();
        for (FidelityCardRecord fc: this.getAllFidelityCard())
            if(fc.getUser().getId() == id)
                fidelityCardRecords.add(fc);
        if(fidelityCardRecords.isEmpty())
            return Optional.empty();
        return Optional.of(fidelityCardRecords);
    }

    //Aggiungo una nuova fidelity card
    public boolean addFidelityCard(FidelityCardRecord fidelityCardsRecord) {
        //Controllo se l'utente ha gia una fidelity card assegnata
        //Se non ha nessuna fidelity card la aggiungo e restituisco true
        //Altrimenti se ha gia una fidelity card assegnata, controllo che il venditore della card non sia lo stesso
        //Se non è lo stesso venditore restituisco true altrimenti false
        List<FidelityCardRecord> f = this.getAllFidelityCard();
        if(f.isEmpty()){
            fidelityCardRepository.save(fidelityCardsRecord);
            return true;
        }
        for (FidelityCardRecord fc: f)
            if(fc.getUser().getId() == fidelityCardsRecord.getUser().getId() && fc.getVendorFidelity().getId() == fidelityCardsRecord.getVendorFidelity().getId())
                return false;
        fidelityCardRepository.save(fidelityCardsRecord);
        return true;
    }

    //Modifico una fidelity card già esistente
    public boolean modifyFidelityCard(FidelityCardRecord fidelityCardsRecords) {
        try {
            if (!fidelityCardRepository.existsById(fidelityCardsRecords.getId()))
                return false;
            fidelityCardRepository.save(fidelityCardsRecords);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
