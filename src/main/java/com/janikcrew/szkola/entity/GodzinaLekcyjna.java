package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "godzina_lekcyjna")
public class GodzinaLekcyjna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel prowadzacy;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela_zastepujacego")
    private Nauczyciel nauczycielZastepujacy;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_miejsca")
    private Miejsce miejsce;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_miejsca_zastepujacego")
    private Miejsce miejsceZastepujace;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "data")
    private Dzien dzien;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "nazwa_klasy")
    private Klasa klasa;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot przedmiot;

    @Column(name = "godzina_rozpoczecia")
    private LocalTime godzRozpoczecia;

    @Column(name = "godzina_zakonczenia")
    private LocalTime godzZakonczenia;

    @Column(name = "zdarzenie")
    private String zdarzenie;

    public GodzinaLekcyjna() {

    }

    public GodzinaLekcyjna(LocalTime godzRozpoczecia, LocalTime godzZakonczenia) {
        this.godzRozpoczecia = godzRozpoczecia;
        this.godzZakonczenia = godzZakonczenia;
    }

    public GodzinaLekcyjna(Nauczyciel prowadzacy, Przedmiot przedmiot, LocalTime godzRozpoczecia, LocalTime godzZakonczenia) {
        this.prowadzacy = prowadzacy;
        this.przedmiot = przedmiot;
        this.godzRozpoczecia = godzRozpoczecia;
        this.godzZakonczenia = godzZakonczenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nauczyciel getProwadzacy() {
        return prowadzacy;
    }

    public void setProwadzacy(Nauczyciel prowadzacy) {
        this.prowadzacy = prowadzacy;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public LocalTime getGodzRozpoczecia() {
        return godzRozpoczecia;
    }

    public void setGodzRozpoczecia(LocalTime godzRozpoczecia) {
        this.godzRozpoczecia = godzRozpoczecia;
    }

    public LocalTime getGodzZakonczenia() {
        return godzZakonczenia;
    }

    public void setGodzZakonczenia(LocalTime godzZakonczenia) {
        this.godzZakonczenia = godzZakonczenia;
    }

    public Nauczyciel getNauczycielZastepujacy() {
        return nauczycielZastepujacy;
    }

    public void setNauczycielZastepujacy(Nauczyciel nauczycielZastepujacy) {
        this.nauczycielZastepujacy = nauczycielZastepujacy;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public Dzien getDzien() {
        return dzien;
    }

    public void setDzien(Dzien dzien) {
        this.dzien = dzien;
    }

    public String getZdarzenie() {
        return zdarzenie;
    }

    public void setZdarzenie(String zdarzenie) {
        this.zdarzenie = zdarzenie;
    }

    public Miejsce getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(Miejsce miejsce) {
        this.miejsce = miejsce;
    }

    public Miejsce getMiejsceZastepujace() {
        return miejsceZastepujace;
    }

    public void setMiejsceZastepujace(Miejsce miejsceZastepujace) {
        this.miejsceZastepujace = miejsceZastepujace;
    }

    @Override
    public String toString() {
        return "GodzinaLekcyjna{" +
                "id=" + id +
                ", prowadzacy=" + prowadzacy +
                ", nauczycielZastepujacy=" + nauczycielZastepujacy +
                ", dzien=" + dzien +
                ", klasa=" + klasa +
                ", przedmiot=" + przedmiot +
                ", godzRozpoczecia=" + godzRozpoczecia +
                ", godzZakonczenia=" + godzZakonczenia +
                '}';
    }
}
