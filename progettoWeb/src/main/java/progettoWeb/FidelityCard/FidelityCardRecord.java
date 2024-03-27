package progettoWeb.FidelityCard;

import jakarta.persistence.*;
import progettoWeb.Store.StoreRecord;
import progettoWeb.User.UserRecord;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FidelityCard_FidelityPlan", //Nome della tabella di join
            joinColumns = @JoinColumn(name = "fidelityCard_id", referencedColumnName = "id"), //Colonna di join per l'entità corrente
            inverseJoinColumns = @JoinColumn(name = "storeRecord_id", referencedColumnName = "id") //Colonna di join per l'entità associata
    )
    private List<StoreRecord> fidelityPlan;

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

    public List<StoreRecord> getFidelityPlan() {
        return fidelityPlan;
    }

    public void setFidelityPlan(List<StoreRecord> fidelityPlan) {
        this.fidelityPlan = fidelityPlan;
    }
}
