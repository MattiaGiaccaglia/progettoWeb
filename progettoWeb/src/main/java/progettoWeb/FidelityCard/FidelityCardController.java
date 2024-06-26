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
            return ResponseEntity.ok().body("{\"message\": \"Nessuna Fidelity Card associata all'utente.\"}");
        return new ResponseEntity<>(fidelityCard, HttpStatus.OK);
    }

    //Aggiungo una fidelity card
    @PostMapping("/addFidelityCard")
    public ResponseEntity<String> addFidelityCard(@RequestBody FidelityCardRecord fidelityCard) {
        if (fidelityCardService.addFidelityCard(fidelityCard))
            return ResponseEntity.ok().body("{\"message\": \"Fidelity Card aggiunta correttamente.\"}");
        return ResponseEntity.badRequest().body("{\"message\": \"Non è possibile aggiungere la Fidelity Card, controllare i dati inseriti e riprovare.\"}");
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
}
