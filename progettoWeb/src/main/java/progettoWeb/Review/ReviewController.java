package progettoWeb.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //Restituisco tutte le Review
    @GetMapping("/getReviews")
    public ResponseEntity<Object> getAllReviews() {
        List<ReviewRecord> reviewRecords = reviewService.getAllReviews();
        if(reviewRecords.isEmpty())
            return new ResponseEntity<>("Nessuna Review presente.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviewRecords, HttpStatus.OK);
    }

    //Aggiungo una Review
    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(@RequestBody ReviewRecord reviewRecord) {
        if(reviewService.addReview(reviewRecord))
            return new ResponseEntity<>("Review aggiunta correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Non è possibile aggiungere la Review, controllare i dati inseriti e riprovare.", HttpStatus.BAD_REQUEST);
    }

    //Restituisco una Review a partire dal suo ID
    @GetMapping("/getReview/{id}")
    public ResponseEntity<ReviewRecord> getReview(@PathVariable("id") int id){
        return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
    }

    //Restituisco tutte le Review fatte da un Utente
    @GetMapping("/getReviewByIdUser/{id}")
    public ResponseEntity<Object> getAllReviewByIDUser(@PathVariable("id") int id) {
        List<ReviewRecord> reviewRecords = reviewService.getAllReviewByIdUser(id);
        if (reviewRecords.isEmpty())
            return new ResponseEntity<>("L'utente " + id + " non ha scritto nessuna recensione.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviewRecords, HttpStatus.OK);
    }

    //Restituisco tutte le Review fatte a un Venditore
    @GetMapping("/getReviewByIDVendor/{id}")
    public ResponseEntity<Object> getAllReviewByIDVendor(@PathVariable("id") int id) {
        List<ReviewRecord> reviewRecords = reviewService.getAllReviewByIdVendor(id);
        if (reviewRecords.isEmpty())
            return new ResponseEntity<>("Non è stata fatta alcuna Review al venditore " + id + ".", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviewRecords, HttpStatus.OK);
    }

    //Elimino una Review
    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") int id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>("Recensione eliminata correttamente.", HttpStatus.OK);
    }

}
