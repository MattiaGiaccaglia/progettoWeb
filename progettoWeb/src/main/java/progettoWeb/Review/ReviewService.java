package progettoWeb.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserRepository;
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
                .orElseThrow(() -> new ReviewException.ReviewExceptionNotFound("Review non presente con il seguente id: " + id));
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

    //TODO Valutare se mantenere i due metodi distinti

    //Restituisco tutte le Review che sono state fatte da un Utente
    public List<ReviewRecord> getAllReviewByIdUser(int id) {
        UserRecord user = userService.getUser(id);
        return getAllReviews().stream().filter(r -> user.getId() == r.getUser().getId()).collect(Collectors.toList());
    }


    //Elimino una Review
    public boolean deleteReview(int id) {
        reviewRepository.delete(getReview(id));
        return true;
    }
}
