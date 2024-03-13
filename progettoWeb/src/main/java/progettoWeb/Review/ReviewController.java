package progettoWeb.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //Restituisco tutte le Review
    @RequestMapping(value = "/api/getReviews")
    public ResponseEntity<Object> getAllReviews() {
        List<ReviewRecord> reviewRecords = reviewService.getAllReviews();
        if(reviewRecords.isEmpty())
            return new ResponseEntity<>("Nessuna Review presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviewRecords, HttpStatus.OK);
    }

    //Aggiungo una Review
    @RequestMapping(value = "/api/addReview", method= RequestMethod.POST)
    public ResponseEntity<String> addReview(@RequestBody ReviewRecord reviewRecord) {
        if(reviewService.addReview(reviewRecord))
            return new ResponseEntity<>("Review aggiunta correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile aggiungere Review", HttpStatus.BAD_REQUEST);
    }

    //Restituisco tutte le Review fatte da un Utente
    @RequestMapping("/api/getReviewByIdUser/{id}")
    public ResponseEntity<Object> getAllReviewByIDUser(@PathVariable("id") int id) {
        List<ReviewRecord> reviewRecords = reviewService.getAllReviewByIdUser(id);
        if(reviewRecords.isEmpty())
            return new ResponseEntity<>("Nessuna Review presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviewRecords, HttpStatus.OK);
    }

    //Restituisco tutte le Review fatte a un Venditore
    @RequestMapping("/api/getReviewByIDVendor/{id}")
    public ResponseEntity<Object> getAllReviewByIDVendor(@PathVariable("id") int id) {
        List<ReviewRecord> reviewRecords = reviewService.getAllReviewByIdVendor(id);
        if(reviewRecords.isEmpty())
            return new ResponseEntity<>("Nessuna Review presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviewRecords, HttpStatus.OK);
    }

    //Elimino una Review
    @DeleteMapping("/api/deleteReview/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") int id){
        if(reviewService.deleteReview(id))
            return new ResponseEntity<>("Recensione eliminata correttamente", HttpStatus.OK);
        return new ResponseEntity<>("Nessuna recensione trovata", HttpStatus.NOT_FOUND);
    }

}
