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
