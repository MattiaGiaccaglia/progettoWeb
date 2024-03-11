package progettoWeb.Review;

import jakarta.persistence.*;
import progettoWeb.User.UserRecord;

@Entity
public class ReviewRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String testo;

    @Column(nullable = false)
    private double valutazione;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(nullable = false)
    private UserRecord vendor;

    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(nullable = false)
    private UserRecord user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public UserRecord getVendor() {
        return vendor;
    }

    public void setVendor(UserRecord vendor) {
        this.vendor = vendor;
    }

    public UserRecord getUser() {
        return user;
    }

    public void setUser(UserRecord user) {
        this.user = user;
    }
}
