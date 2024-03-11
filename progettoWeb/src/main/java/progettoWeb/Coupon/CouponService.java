package progettoWeb.Coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<CouponRecord> getSingleCoupon(int id) {
        return couponRepository.findById(id);
    }

    //Aggiungo Coupon
    public void addCoupon(CouponRecord couponRecords) {
        couponRepository.save(couponRecords);
    }

    //Aggiungo un Coupon a partire da un ID
    public void addCouponFromId(int value, int vendorid, int userid) {
        Optional<UserRecord> Venditore = userService.getUser(vendorid);
        Optional<UserRecord> Utente = userService.getUser(userid);

        //Creo Venditore
        UserRecord vendor = new UserRecord();
        vendor.setId(Venditore.get().getId());
        vendor.setNome(Venditore.get().getNome());
        vendor.setCognome(Venditore.get().getCognome());
        vendor.setUsername(Venditore.get().getUsername());
        vendor.setPassword(Venditore.get().getPassword());
        vendor.setEmail(Venditore.get().getEmail());
        vendor.setTelefono(Venditore.get().getTelefono());
        vendor.setRuolo(Venditore.get().getRuolo());

        //Creo Utente
        UserRecord user = new UserRecord();
        user.setId(Utente.get().getId());
        user.setNome(Utente.get().getNome());
        user.setCognome(Utente.get().getCognome());
        user.setUsername(Utente.get().getUsername());
        user.setPassword(Utente.get().getPassword());
        user.setEmail(Utente.get().getEmail());
        user.setTelefono(Utente.get().getTelefono());
        user.setRuolo(Utente.get().getRuolo());

        //Creo Coupon e aggiungo Venditore e Utente
        CouponRecord couponRecords = new CouponRecord();
        couponRecords.setValore(value);
        couponRecords.setVendorCoupon(vendor);
        couponRecords.setUserCoupon(user);
        couponRepository.save(couponRecords);
    }
}
