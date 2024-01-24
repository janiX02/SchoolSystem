package com.janikcrew.szkola.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Date;

@Entity
@Component
@DiscriminatorValue("ROLE_ADMIN")
public class Admin extends Osoba {

    @Transient
    private boolean czyPosiadaUwagi;

    @Transient
    private boolean czyWybranaKlasa;

    @Transient
    private boolean czyNauczyciel;

    @Transient
    private boolean czyWybranyPrzedmiot;

    @Transient
    private Klasa wybranaKlasa;

    @Transient
    private Przedmiot wybranyPrzedmiot;

    @Transient
    private boolean czyWybranyUczen;

    @Transient
    private Uczen wybranyUczen;

    @Transient
    private int idWybranegoPrzedmiotu;
    public Admin() {
    }

    public Admin(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
    }

    public boolean isCzyPosiadaUwagi() {
        return czyPosiadaUwagi;
    }

    public void setCzyPosiadaUwagi(boolean czyPosiadaUwagi) {
        this.czyPosiadaUwagi = czyPosiadaUwagi;
    }

    public boolean isCzyWybranaKlasa() {
        return czyWybranaKlasa;
    }

    public void setCzyWybranaKlasa(boolean czyWybranaKlasa) {
        this.czyWybranaKlasa = czyWybranaKlasa;
    }

    public Klasa getWybranaKlasa() {
        return wybranaKlasa;
    }

    public void setWybranaKlasa(Klasa wybranaKlasa) {
        this.wybranaKlasa = wybranaKlasa;
    }

    public boolean isCzyNauczyciel() {
        return czyNauczyciel;
    }

    public void setCzyNauczyciel(boolean czyNauczyciel) {
        this.czyNauczyciel = czyNauczyciel;
    }

    public boolean isCzyWybranyPrzedmiot() {
        return czyWybranyPrzedmiot;
    }

    public void setCzyWybranyPrzedmiot(boolean czyWybranyPrzedmiot) {
        this.czyWybranyPrzedmiot = czyWybranyPrzedmiot;
    }

    public Przedmiot getWybranyPrzedmiot() {
        return wybranyPrzedmiot;
    }

    public void setWybranyPrzedmiot(Przedmiot wybranyPrzedmiot) {
        this.wybranyPrzedmiot = wybranyPrzedmiot;
    }

    public Uczen getWybranyUczen() {
        return wybranyUczen;
    }

    public void setWybranyUczen(Uczen wybranyUczen) {
        this.wybranyUczen = wybranyUczen;
    }

    public int getIdWybranegoPrzedmiotu() {
        return idWybranegoPrzedmiotu;
    }

    public void setIdWybranegoPrzedmiotu(int idWybranegoPrzedmiotu) {
        this.idWybranegoPrzedmiotu = idWybranegoPrzedmiotu;
    }

    public boolean isCzyWybranyUczen() {
        return czyWybranyUczen;
    }
    public void setCzyWybranyUczen(boolean czyWybranyUczen) {
        this.czyWybranyUczen = czyWybranyUczen;
    }

    @Override
    public String toString() {
        return this.getImie() + " " + this.getNazwisko();
    }
}
