package progettoWeb.Coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progettoWeb.User.Role;
import progettoWeb.User.UserRepository;


@RestController
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CouponService couponService;

    //Restituisco tutti i Coupon
    @GetMapping(value = "/api/getCoupons")
    public ResponseEntity<Object> getCoupons(){
        if(couponService.getAllCoupon().isEmpty())
            return new ResponseEntity<Object>("Nessun Coupon presente", HttpStatus.OK);
        return new ResponseEntity<Object>(couponService.getAllCoupon(), HttpStatus.OK);
    }

    //Restituisco uno specifico Coupon
    @GetMapping(value = "/api/getSingleCoupons/{id}")
    public ResponseEntity<Object> getSingleCoupon(@PathVariable int id){
        if(!couponService.getSingleCoupon(id).isPresent())
            return new ResponseEntity<Object>("Il coupon non esiste", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(couponService.getSingleCoupon(id), HttpStatus.OK);
    }

    //Aggiungo un Coupon
    @RequestMapping(value = "/api/addCoupon", method = RequestMethod.POST)
    public ResponseEntity<String> addCoupon(@RequestBody CouponRecord couponRecord){

        //Controllo che il ruolo del venditore sia Role.venditore
        if(couponRecord.getVendorCoupon().getRuolo().equals(Role.venditore)) {
            couponService.addCoupon(couponRecord);
            return new ResponseEntity<String>("Coupon aggiunto correttamente", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Impossibile aggiungere Coupon", HttpStatus.BAD_REQUEST);
    }

    //Aggiungo un Coupon a partire da un ID
    @RequestMapping(value = "/api/addCouponId/{value}/{vendorid}/{userid}", method= RequestMethod.POST)
    public ResponseEntity<String> addCouponFromId(@PathVariable("value") int value, @PathVariable ("vendorid") int vendorid, @PathVariable("userid") int userid){
        try {
            //Controllo che il ruolo del venditore sia Role.venditore
            if(userRepository.findById(userid).get().getRuolo().equals(Role.venditore)) {
                couponService.addCouponFromId(value, vendorid, userid);
                return new ResponseEntity<String>("Coupon aggiunto correttamente", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Impossibile aggiungere Coupon", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Impossibile aggiungere Coupon", HttpStatus.BAD_REQUEST);
    }

    //Elimino un Coupon
    @RequestMapping(value="/api/deleteCoupon/{idCoupon}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteCoupon(@PathVariable ("idCoupon") int idCoupon){
        if(!couponService.getSingleCoupon(idCoupon).isPresent())
            return new ResponseEntity<String>("Impossibile eliminare il Coupon, non esiste", HttpStatus.BAD_REQUEST);
        else{
            couponRepository.delete(couponService.getSingleCoupon(idCoupon).get());
            return new ResponseEntity<String>("Coupon eliminato correttamente", HttpStatus.OK);
        }
    }
}
