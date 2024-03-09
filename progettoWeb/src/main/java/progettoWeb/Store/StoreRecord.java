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

    @OneToOne(targetEntity = UserRecord.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "proprietario")
    private UserRecord proprietario;

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

    public UserRecord getProprietario() {
        return proprietario;
    }

    public void setProprietario(UserRecord proprietario) {
        this.proprietario = proprietario;
    }

    public int getProgramma() {
        return programma;
    }

    public void setProgramma(int programma) {
        this.programma = programma;
    }
}
