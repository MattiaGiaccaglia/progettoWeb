package progettoWeb.Coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private UserService userService;

    //Restituisco tutti i Coupon
    public List<CouponRecord> getAllCoupon() {
        List<CouponRecord>couponRecords = new ArrayList<>();
        couponRepository.findAll().forEach(couponRecords::add);
        return couponRecords;
    }

    //Restituisco un singolo Coupon a partire da ID
    public CouponRecord getSingleCoupon(int id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new CouponException.CouponExceptionNotFound("Nessun coupon con l'id: " + id));
    }

    //Aggiungo un Coupon
    public boolean addCoupon(CouponRecord couponRecord) {
        //Controllo che i dati nella RequestBody siano corretti
        UserRecord user = userService.getUser(couponRecord.getUserCoupon().getId());
        UserRecord vendor = userService.getUser(couponRecord.getVendorCoupon().getId());
        if (!user.equals(couponRecord.getUserCoupon()) || !vendor.equals(couponRecord.getVendorCoupon()))
            return false;
        //Controllo che il ruolo del venditore sia Role.venditore che l'user inserito abbia il ruolo di utente
        if(couponRecord.getVendorCoupon().getRuolo().equals(Role.venditore) && couponRecord.getUserCoupon().getRuolo().equals(Role.utente)) {
            couponRepository.save(couponRecord);
            return true;
        }
        return false;
    }

    //Elimino un Coupon
    public void deleteCoupon(int id){
        couponRepository.delete(getSingleCoupon(id));
    }
}
