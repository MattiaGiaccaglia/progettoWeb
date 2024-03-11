package progettoWeb.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //Restituisco tutte le Review
    public List<ReviewRecord> getAllReviews() {
        List<ReviewRecord>reviewRecords = new ArrayList<>();
        reviewRepository.findAll().forEach(reviewRecords::add);
        return reviewRecords;
    }

    //Aggiungo una Review
    public boolean addReview(ReviewRecord reviewRecord){
        //Se non è compresa tra 1 e 5 e che non sia stata fatta da un user a un venditore
        if(reviewRecord.getValutazione() < 1 || reviewRecord.getValutazione() > 5
            || !reviewRecord.getVendor().getRuolo().equals(Role.venditore)
            || !reviewRecord.getUser().getRuolo().equals(Role.utente))
            return false;
        try {
        reviewRepository.save(reviewRecord);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //Restituisco tutte le Review che sono state fatte a un Venditore
    public List<ReviewRecord> getAllReviewByIdVendor(int id) {
        UserRecord user = new UserRecord();
        user.setId(id);
        List<ReviewRecord>reviewRecords = new ArrayList<>();
        //Per ogni Review, se il Venditore ha lo stesso id della Review, la aggiungo alla lista
        for (ReviewRecord r: getAllReviews()) {
            if(user.getId() == r.getVendor().getId())
                reviewRecords.add(r);
        }
        return reviewRecords;
    }

    //Restituisco tutte le Review che sono state fatte da un Utente
    public List<ReviewRecord> getAllReviewByIdUser(int id){
        UserRecord user = new UserRecord();
        user.setId(id);
        List<ReviewRecord>reviewRecords = new ArrayList<>();
        //Per ogni Review, se l'utente ha lo stesso id della Review, la aggiungo alla lista
        for (ReviewRecord r: getAllReviews()) {
            if(user.getId() == r.getUser().getId())
                reviewRecords.add(r);
        }
        return reviewRecords;
    }

    //Elimino una Review
    public boolean deleteReview(int id) {
        if(reviewRepository.findById(id).isPresent()) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
