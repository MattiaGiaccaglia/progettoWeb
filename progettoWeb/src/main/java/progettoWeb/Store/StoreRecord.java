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
