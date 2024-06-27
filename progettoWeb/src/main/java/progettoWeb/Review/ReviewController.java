/*
 * Copyright 2024 Mattia Giaccaglia
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

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
