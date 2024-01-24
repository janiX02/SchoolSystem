package com.janikcrew.szkola.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Entity
@Component
@DiscriminatorValue("ROLE_UCZEN")
public class Uczen extends Osoba implements Comparable<Uczen> {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="nazwa_klasy")
    private Klasa klasa;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_rodzica")
    private Rodzic rodzic;

    @OneToMany(mappedBy = "otrzymujacy", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Uwaga> uwagiWystawione = new ArrayList<>();

    @OneToMany(mappedBy = "uczen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ocena> oceny = new ArrayList<>();

    @Transient
    private boolean czyPosiadaUwagi;

    @Transient
    private boolean czyPosiadaOceny;

    @Transient
    private boolean czyWybranyPrzedmiot;

    @Transient
    private int nr;

    @Transient
    private Przedmiot wybranyPrzedmiot;
    public Uczen() {

    }
    public Uczen(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
    }
    public Uczen(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia, Klasa klasa) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
        this.klasa = klasa;
    }

    public Uczen(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia, Klasa klasa, Rodzic rodzic) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
        this.klasa = klasa;
        this.rodzic = rodzic;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public Rodzic getRodzic() {
        return rodzic;
    }

    public void setRodzic(Rodzic rodzic) {
        this.rodzic = rodzic;
    }

    public List<Uwaga> getUwagiWystawione() {
        return uwagiWystawione;
    }

    public void setUwagiWystawione(List<Uwaga> uwagiWystawione) {
        this.uwagiWystawione = uwagiWystawione;
    }

    public List<Ocena> getOceny() {
        return oceny;
    }

    public void setOceny(List<Ocena> oceny) {
        this.oceny = oceny;
    }

    public boolean isCzyPosiadaUwagi() {
        return czyPosiadaUwagi;
    }

    public void setCzyPosiadaUwagi(boolean czyPosiadaUwagi) {
        this.czyPosiadaUwagi = czyPosiadaUwagi;
    }

    public boolean isCzyPosiadaOceny() {
        return czyPosiadaOceny;
    }

    public void setCzyPosiadaOceny(boolean czyPosiadaOceny) {
        this.czyPosiadaOceny = czyPosiadaOceny;
    }

    public Przedmiot getWybranyPrzedmiot() {
        return wybranyPrzedmiot;
    }

    public void setWybranyPrzedmiot(Przedmiot wybranyPrzedmiot) {
        this.wybranyPrzedmiot = wybranyPrzedmiot;
    }

    public boolean isCzyWybranyPrzedmiot() {
        return czyWybranyPrzedmiot;
    }

    public void setCzyWybranyPrzedmiot(boolean czyWybranyPrzedmiot) {
        this.czyWybranyPrzedmiot = czyWybranyPrzedmiot;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return this.getImie() + " " + this.getNazwisko();
    }

    @Override
    public int compareTo(Uczen o) {
        return this.getNazwisko().compareToIgnoreCase(o.getNazwisko());
    }
}
