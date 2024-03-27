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
