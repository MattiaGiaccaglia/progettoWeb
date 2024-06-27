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
    @GetMapping("/getCoupons")
    public ResponseEntity<Object> getCoupons() {
        List<CouponRecord> couponRecords = couponService.getAllCoupon();
        if (couponRecords.isEmpty())
            return new ResponseEntity<>("Nessun Coupon presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(couponRecords, HttpStatus.OK);
    }

    //Restituisco uno specifico Coupon
    @GetMapping("/getSingleCoupons/{id}")
    public ResponseEntity<Object> getSingleCoupon(@PathVariable int id) {
        CouponRecord couponRecord = couponService.getSingleCoupon(id);
        return new ResponseEntity<>(couponRecord, HttpStatus.OK);
    }

    //Aggiungo un Coupon
    @PostMapping("/addCoupon")
    public ResponseEntity<String> addCoupon(@RequestBody CouponRecord couponRecord){
        if(couponService.addCoupon(couponRecord))
           return new ResponseEntity<>("Coupon aggiunto correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("Impossibile aggiungere Coupon, controllare i dati inseriti e riprovare.",HttpStatus.BAD_REQUEST);
    }


    //Elimino un Coupon
    @PostMapping("/deleteCoupon/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable ("id") int id) {
        couponService.deleteCoupon(id);
        return new ResponseEntity<>("Coupon eliminato correttamente", HttpStatus.OK);
    }
}
