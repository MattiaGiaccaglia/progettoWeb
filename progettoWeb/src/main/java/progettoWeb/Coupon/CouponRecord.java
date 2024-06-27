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

import jakarta.persistence.*;
import progettoWeb.User.UserRecord;

@Entity
public class CouponRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer valore;

    @ManyToOne(targetEntity = UserRecord.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "vendor_id")
    private UserRecord vendorCoupon;

    @ManyToOne(targetEntity = UserRecord.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false ,name = "user_id")
    private UserRecord userCoupon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public UserRecord getVendorCoupon() {
        return vendorCoupon;
    }

    public void setVendorCoupon(UserRecord vendorCoupon) {
        this.vendorCoupon = vendorCoupon;
    }

    public UserRecord getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(UserRecord userCoupon) {
        this.userCoupon = userCoupon;
    }
}
