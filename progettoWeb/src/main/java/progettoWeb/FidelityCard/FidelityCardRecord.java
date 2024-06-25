package progettoWeb.FidelityCard;

import jakarta.persistence.*;
import progettoWeb.User.UserRecord;


@Entity
public class FidelityCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = UserRecord.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_user")
    private UserRecord user;

    @ManyToOne(targetEntity = UserRecord.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "vendor_id")
    private UserRecord vendorFidelity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRecord getUser() {
        return user;
    }

    public void setUser(UserRecord user) {
        this.user = user;
    }

    public UserRecord getVendorFidelity() {
        return vendorFidelity;
    }
    public void setVendorFidelity(UserRecord vendorFidelity) {
        this.vendorFidelity = vendorFidelity;
    }
}
