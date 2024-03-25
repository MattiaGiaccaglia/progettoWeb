package progettoWeb.Store;

import jakarta.persistence.*;
import progettoWeb.User.UserRecord;

import java.util.List;

@Entity
public class StoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String luogo;

    @ManyToOne(targetEntity = UserRecord.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "proprietario_id", nullable = false)
    private UserRecord proprietario;

    @OneToMany(targetEntity = UserRecord.class,  fetch = FetchType.EAGER)
    @JoinColumn(name = "dipendenti")
    private List<UserRecord> dipendenti;

    @Column(nullable = false)
    private int programma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public UserRecord getProprietario() {
        return proprietario;
    }

    public void setProprietario(UserRecord proprietario) {
        this.proprietario = proprietario;
    }

    public List<UserRecord> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<UserRecord> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public int getProgramma() {
        return programma;
    }

    public void setProgramma(int programma) {
        this.programma = programma;
    }
}
