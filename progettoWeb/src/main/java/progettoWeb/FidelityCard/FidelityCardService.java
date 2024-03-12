package progettoWeb.FidelityCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    //Restituisco fidelity card di un user
    public FidelityCardRecord getFidelityCardByUserId(int id) {
        return fidelityCardRepository.findByUser(userService.getUser(id).get());
    }

    //Aggiungo una nuova fidelity card
    public boolean addFidelityCard(FidelityCardRecord fidelityCardsRecord) {
        if(fidelityCardRepository.existsById(fidelityCardsRecord.getId()))
            return false;
        fidelityCardRepository.save(fidelityCardsRecord);
        return true;
    }

    //Modifico una fidelity card già esistente
    public boolean modifyFidelityCard(FidelityCardRecord fidelityCardsRecords) {
        if(!fidelityCardRepository.existsById(fidelityCardsRecords.getId()))
            return false;
        fidelityCardRepository.save(fidelityCardsRecords);
        return true;
    }
}
