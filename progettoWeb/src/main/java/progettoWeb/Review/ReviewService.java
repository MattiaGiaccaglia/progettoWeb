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
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    //Restituisco tutte le Review
    public List<ReviewRecord> getAllReviews() {
        List<ReviewRecord> reviewRecords = new ArrayList<>();
        reviewRepository.findAll().forEach(reviewRecords::add);
        return reviewRecords;
    }

    //Restituisco Review a partire da ID
    public ReviewRecord getReview(int id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewException.ReviewExceptionNotFound("Nessuna Review presente con il seguente id: " + id));
    }

    //Aggiungo una Review
    public boolean addReview(ReviewRecord reviewRecord) {
        UserRecord user = userService.getUser(reviewRecord.getUser().getId());
        UserRecord vendor = userService.getUser(reviewRecord.getVendor().getId());
        //Se non è compresa tra 1 e 5 e che non sia stata fatta da un user a un venditore
        if(reviewRecord.getValutazione() < 1 || reviewRecord.getValutazione() > 5
            || !reviewRecord.getVendor().getRuolo().equals(Role.venditore)
            || !reviewRecord.getUser().getRuolo().equals(Role.utente))
            return false;
        //Controllo che i dati all'interno della RequestBody siano corretti
        if (!user.equals(reviewRecord.getUser()) || !vendor.equals(reviewRecord.getVendor()))
            return false;
        reviewRepository.save(reviewRecord);
        return true;
    }

    //Restituisco tutte le Review che sono state fatte a un Venditore
    public List<ReviewRecord> getAllReviewByIdVendor(int id) {
        UserRecord user = userService.getUser(id);
        // Controllo se l'utente ottenuto è un venditore
        if (!user.getRuolo().equals(Role.venditore))
            throw new IllegalArgumentException("L'utente inserito non è un venditore.");
        return getAllReviews().stream().filter(r -> user.getId() == r.getVendor().getId()).collect(Collectors.toList());
    }

    //Restituisco tutte le Review che sono state fatte da un Utente
    public List<ReviewRecord> getAllReviewByIdUser(int id) {
        UserRecord user = userService.getUser(id);
        if(!user.getRuolo().equals(Role.utente))
            throw new IllegalArgumentException("L'id inserito non appartiene ad un utente, ma a uno/un " + user.getRuolo());
        return getAllReviews().stream().filter(r -> user.getId() == r.getUser().getId()).collect(Collectors.toList());
    }


    //Elimino una Review
    public void deleteReview(int id) {
        reviewRepository.delete(getReview(id));
    }
}
