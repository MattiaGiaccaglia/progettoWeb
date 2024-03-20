package progettoWeb.Coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    //Restituisco tutti i Coupon
    @GetMapping(value = "/getCoupons")
    public ResponseEntity<Object> getCoupons() {
        List<CouponRecord> couponRecords = couponService.getAllCoupon();
        if (couponRecords.isEmpty())
            return new ResponseEntity<>("Nessun Coupon presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(couponRecords, HttpStatus.OK);
    }

    //Restituisco uno specifico Coupon
    @GetMapping(value = "/getSingleCoupons/{id}")
    public ResponseEntity<Object> getSingleCoupon(@PathVariable int id) {
        CouponRecord couponRecord = couponService.getSingleCoupon(id);
        return new ResponseEntity<>(couponRecord, HttpStatus.OK);
    }

    //Aggiungo un Coupon
    @RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
    public ResponseEntity<String> addCoupon(@RequestBody CouponRecord couponRecord){
        if(couponService.addCoupon(couponRecord))
           return new ResponseEntity<>("Coupon aggiunto correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile aggiungere Coupon, controllare i dati inseriti e riprovare.",HttpStatus.BAD_REQUEST);
    }


    //Elimino un Coupon
    @RequestMapping(value = "/deleteCoupon/{idCoupon}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteCoupon(@PathVariable ("idCoupon") int idCoupon) {
        couponService.deleteCoupon(idCoupon);
        return new ResponseEntity<>("Coupon eliminato correttamente", HttpStatus.OK);
    }
}
