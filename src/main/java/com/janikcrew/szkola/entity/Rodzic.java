package com.janikcrew.szkola.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Component
@DiscriminatorValue("ROLE_RODZIC")
public class Rodzic extends Osoba {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_dziecka")
    private Uczen dziecko;

    public Rodzic() {

    }
    public Rodzic(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
    }
    public Rodzic(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia, Uczen dziecko) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
        this.dziecko = dziecko;
    }

    public Uczen getDziecko() {
        return dziecko;
    }

    public void setDziecko(Uczen dziecko) {
        this.dziecko = dziecko;
    }

    @Override
    public String toString() {
        return this.getImie() + " " + this.getNazwisko();
    }
}
