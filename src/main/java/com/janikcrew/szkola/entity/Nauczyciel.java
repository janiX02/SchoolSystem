package com.janikcrew.szkola.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity
@Component
@DiscriminatorValue("ROLE_NAUCZYCIEL")
public class Nauczyciel extends Osoba {

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                                    CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="nazwa_klasy_pod_opieka")
    private Klasa klasaPodOpieka;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_nauczyciela")
    private List<Przedmiot> listaPrzedmiotow;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nauczyciela")
    private List<Uwaga> uwagiWystawione;

    @Transient
    private List<Klasa> listaKlas;

    @Transient
    private Klasa wybranaKlasa;

    @Transient
    private Przedmiot wybranyPrzedmiot;

    @Transient
    private boolean czyPosiadaUwagi;

    @Transient
    private boolean czyWybranaKlasa;

    @Transient
    private boolean czyWybranyPrzedmiot;

    @Transient
    private boolean czyNauczyciel;

    @Transient
    private int idWybranegoPrzedmiotu;

    @Transient
    private Uczen wybranyUczen;

    @Transient
    private boolean czyWybranyUczen;

    public Nauczyciel() {

    }
    public Nauczyciel(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia, Klasa klasaPodOpieką, List<Przedmiot> listaPrzedmiotow) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
        this.klasaPodOpieka = klasaPodOpieką;
        this.listaPrzedmiotow = listaPrzedmiotow;
    }

    public Nauczyciel(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia, List<Przedmiot> listaPrzedmiotow) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
        this.listaPrzedmiotow = listaPrzedmiotow;
    }
    public Nauczyciel(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia) {
        super(pesel, imie, nazwisko, email, dataUrodzenia);
    }
    public void addPrzedmiot(Przedmiot przedmiot) {
        listaPrzedmiotow.add(przedmiot);
    }
    public Klasa getKlasaPodOpieka() {
        return klasaPodOpieka;
    }

    public void setKlasaPodOpieka(Klasa klasaPodOpieką) {
        this.klasaPodOpieka = klasaPodOpieką;
    }

    public List<Przedmiot> getListaPrzedmiotow() {
        return listaPrzedmiotow;
    }

    public void setListaPrzedmiotow(List<Przedmiot> listaPrzedmiotow) {
        this.listaPrzedmiotow = listaPrzedmiotow;
    }

    public List<Uwaga> getUwagiWystawione() {
        return uwagiWystawione;
    }

    public void setUwagiWystawione(List<Uwaga> uwagiWystawione) {
        this.uwagiWystawione = uwagiWystawione;
    }

    public List<Klasa> getListaKlas() {
        return listaKlas;
    }

    public boolean isCzyPosiadaUwagi() {
        return czyPosiadaUwagi;
    }

    public void setCzyPosiadaUwagi(boolean czyPosiadaUwagi) {
        this.czyPosiadaUwagi = czyPosiadaUwagi;
    }

    public void setListaKlas(List<Klasa> listaKlas) {
        this.listaKlas = listaKlas;
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

    public Uczen getWybranyUczen() {
        return wybranyUczen;
    }

    public void setWybranyUczen(Uczen wybranyUczen) {
        this.wybranyUczen = wybranyUczen;
    }

    public Przedmiot getWybranyPrzedmiot() {
        return wybranyPrzedmiot;
    }

    public void setWybranyPrzedmiot(Przedmiot wybranyPrzedmiot) {
        this.wybranyPrzedmiot = wybranyPrzedmiot;
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
