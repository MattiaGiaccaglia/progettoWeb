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
