package progettoWeb.FidelityCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fidelityCard")
public class FidelityCardController {

    @Autowired
    private FidelityCardService fidelityCardService;

    //Restituisco tutte le fidelity card
    @GetMapping("/getFidelityCards")
    public ResponseEntity<Object> getAllFidelityCard() {
        List<FidelityCardRecord> fidelityCardRecord = fidelityCardService.getAllFidelityCard();
        if(fidelityCardRecord.isEmpty())
            return new ResponseEntity<>("Nessuna Fidelity Card presente.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(fidelityCardRecord, HttpStatus.OK);
    }

    //Restituisco fidelity card a partire da un ID
    @GetMapping("/getFidelityCard/{id}")
    public ResponseEntity<Object> getFidelityCard(@PathVariable("id") int id) {
        FidelityCardRecord fidelityCard = fidelityCardService.getFidelityCard(id);
        return new ResponseEntity<>(fidelityCardService.getFidelityCard(id), HttpStatus.OK);
    }

    //Restituisco fidelity card di un utente
    @GetMapping("/getFidelityCardUser/{id}")
    public ResponseEntity<Object> getFidelityCardByUserId(@PathVariable("id") int id){
        List<FidelityCardRecord> fidelityCard = fidelityCardService.getFidelityCardByUserId(id);
        if (fidelityCard.isEmpty())
            return new ResponseEntity<>("Nessuna Fidelity Card associata a questo utente.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(fidelityCard, HttpStatus.OK);
    }

    //Aggiungo una fidelity card
    @PostMapping("/addFidelityCard")
    public ResponseEntity<String> addFidelityCard(@RequestBody FidelityCardRecord fidelityCard) {
        if (fidelityCardService.addFidelityCard(fidelityCard))
            return new ResponseEntity<>("Fidelity Card aggiunta correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Non è possibile aggiungere la Fidelity Card, controllare i dati inseriti e riprovare.", HttpStatus.BAD_REQUEST);
    }

    //Modifico una fidelity card
    @PutMapping("/modifyFidelityCard")
    public ResponseEntity<String> modifyFidelityCard(@RequestBody FidelityCardRecord fidelityCard) {
        if (fidelityCardService.modifyFidelityCard(fidelityCard))
            return new ResponseEntity<>("Fidelity Card aggiornata correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Non è possibile aggiornare la Fidelity Card, controllare i dati inseriti e riprovare.", HttpStatus.BAD_REQUEST);
    }

    //Elimino fidelity card
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFidelityCard(@PathVariable("id") int id){
        fidelityCardService.deleteFidelityCard(id);
        return new ResponseEntity<>("Fidelity Card eliminata correttamente.", HttpStatus.OK);
    }

    //Aggiungo fidelity plan a una fidelity card
    /*@RequestMapping(value="/api/addFidelityPlan/{idFidelityCard}/{idStore}", method= RequestMethod.POST)
    public ResponseEntity<String> addFidelityPlan(@PathVariable("idFidelityCard") String idFidelityCard, @PathVariable("idStore") String idStore) {
        try{
            Optional<FidelityCardRecord> fidelityCard = fidelityCardService.getFidelityCard(Integer.parseInt(idFidelityCard));
            List<StoreRecord> fidelityPlan = fidelityCard.get().getFidelityPlan();
            Optional<StoreRecord> store = storeService.getStore(Integer.parseInt(idStore));

            //Creo fidelity plan
            StoreRecord newFidelityPlan = new StoreRecord();
            newFidelityPlan.setId(store.get().getId());
            newFidelityPlan.setNome(store.get().getNome());
            newFidelityPlan.setDipendenti(store.get().getDipendenti());
            newFidelityPlan.setProprietario(store.get().getProprietario());
            newFidelityPlan.setProgramma(store.get().getProgramma());
            storeService.modifyStore(newFidelityPlan);
            fidelityPlan.add(newFidelityPlan);

            //Creo fidelity card
            FidelityCardRecord newFidelityCard = new FidelityCardRecord();
            newFidelityCard.setId(fidelityCard.get().getId());
            newFidelityCard.setUser(fidelityCard.get().getUser());
            newFidelityCard.setVendorFidelity(fidelityCard.get().getVendorFidelity());
            newFidelityCard.setFidelityPlan(fidelityPlan);

            //Sostituisco fidelity card esistente con quella modificata
            fidelityCardService.modifyFidelityCard(newFidelityCard);

            return new ResponseEntity<>("Piano fedeltà aggiunto correttamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Non è possibile aggiungere il piano fedeltà", HttpStatus.BAD_REQUEST);
        }
    }*/

}
