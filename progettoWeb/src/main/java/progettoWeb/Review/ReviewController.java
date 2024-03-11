package progettoWeb.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //Restituisco tutte le Review
    @RequestMapping(value = "/api/getReviews")
    public ResponseEntity<Object> getAllReviews() {
        if(reviewService.getAllReviews().isEmpty())
            return new ResponseEntity<Object>("Nessuna Review presente", HttpStatus.OK);
        return new ResponseEntity<Object>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    //Aggiungo una Review
    @RequestMapping(value = "/api/addReview", method= RequestMethod.POST)
    public ResponseEntity<String> addReview(@RequestBody ReviewRecord reviewRecord) {
        if(reviewService.addReview(reviewRecord))
            return new ResponseEntity<String>("Review aggiunta correttamente", HttpStatus.OK);
        return new ResponseEntity<String>("Impossibile aggiungere Review", HttpStatus.BAD_REQUEST);
    }

    //Restituisco tutte le Review fatte da un Utente
    @RequestMapping("/api/getReviewByIdUser/{id}")
    public ResponseEntity<Object> getAllReviewByIDUser(@PathVariable("id") int id) {
        if(reviewService.getAllReviewByIdUser(id).isEmpty())
            return new ResponseEntity<Object>("Nessuna Review presente", HttpStatus.OK);
        return new ResponseEntity<Object>(reviewService.getAllReviewByIdUser(id), HttpStatus.OK);
    }

    //Restituisco tutte le Review fatte a un Venditore
    @RequestMapping("/api/getReviewByIDVendor/{id}")
    public ResponseEntity<Object> getAllReviewByIDVendor(@PathVariable("id") int id) {
        if(reviewService.getAllReviewByIdVendor(id).isEmpty())
            return new ResponseEntity<Object>("Nessuna Review presente", HttpStatus.OK);
        return new ResponseEntity<Object>(reviewService.getAllReviewByIdVendor(id), HttpStatus.OK);
    }

    //Elimino una Review
    @DeleteMapping("/api/deleteReview/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") int id){
        if(reviewService.deleteReview(id))
            return new ResponseEntity<String>("Recensione eliminata correttamente", HttpStatus.OK);
        return new ResponseEntity<String>("Nessuna recensione trovata", HttpStatus.BAD_REQUEST);
    }

}
