package progettoWeb.FidelityCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public FidelityCardRecord getFidelityCard(int id) {
        return fidelityCardRepository.findById(id)
                .orElseThrow(() -> new FidelityCardException.FidelityCardExceptionNotFound
                        ("Nessuna fidelity card associata a questo id: " + id));
    }

    public List<FidelityCardRecord> getFidelityCardByUserId(int id) {
        UserRecord user = userService.getUser(id);
        return getAllFidelityCard().stream().filter(fc -> fc.getUser().getId() == user.getId()).collect(Collectors.toList());
    }


    //Aggiungo una nuova fidelity card
    public boolean addFidelityCard(FidelityCardRecord fidelityCardsRecord) {
        List<FidelityCardRecord> f = this.getAllFidelityCard();
        //Se non c'è nessuna fidelity card la aggiungo
        if(f.isEmpty()){
            fidelityCardRepository.save(fidelityCardsRecord);
            return true;
        }
        UserRecord user = userService.getUser(fidelityCardsRecord.getUser().getId());
        UserRecord vendor = userService.getUser(fidelityCardsRecord.getVendorFidelity().getId());
        //Controllo che nella RequestBody vengano inseriti i dati corretti
        if (!user.equals(fidelityCardsRecord.getUser()) || !vendor.equals(fidelityCardsRecord.getVendorFidelity()) ||
                !fidelityCardsRecord.getVendorFidelity().getRuolo().equals(Role.venditore) ||
                !fidelityCardsRecord.getUser().getRuolo().equals(Role.utente))
            return false;
        //Se utente ha già una fidelity card associata a quel venditore restituisco false
        if (f.stream().anyMatch(fc -> fc.getUser().getId() == user.getId() && fc.getVendorFidelity().getId() == vendor.getId()))
            return false;
        fidelityCardRepository.save(fidelityCardsRecord);
        return true;
    }

    //Modifico una fidelity card già esistente
    public boolean modifyFidelityCard(FidelityCardRecord fidelityCardsRecords) {
        //Controllo che la FidelityCard esista già
        getFidelityCard(fidelityCardsRecords.getId());
        UserRecord user = userService.getUser(fidelityCardsRecords.getUser().getId());
        UserRecord vendor = userService.getUser(fidelityCardsRecords.getVendorFidelity().getId());
        //Controllo che nella RequestBody vengano inseriti i dati corretti
        if (!user.equals(fidelityCardsRecords.getUser()) || !vendor.equals(fidelityCardsRecords.getVendorFidelity()))
            return false;
        fidelityCardRepository.save(fidelityCardsRecords);
        return true;
    }

    //Elimino Fidelity Card
    public void deleteFidelityCard(int id){
        fidelityCardRepository.delete(getFidelityCard(id));
    }
}