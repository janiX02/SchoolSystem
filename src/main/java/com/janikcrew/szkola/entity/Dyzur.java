package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "dyżur")
public class Dyzur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "data")
    private Dzien dzien;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel nauczyciel;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela_zastepujacego")
    private Nauczyciel nauczycielZastepujacy;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_miejsca")
    private Miejsce miejsce;

    @Column(name = "godz_rozpoczecia")
    private LocalTime godzinaRozpoczecia;

    @Column(name = "godz_zakonczenia")
    private LocalTime godzinazZakonczenia;

    public Dyzur() {

    }

    public Dyzur(Dzien dzien, Nauczyciel nauczyciel, Nauczyciel nauczycielZastepujacy, Miejsce miejsce, LocalTime godzinaRozpoczecia, LocalTime godzinazZakonczenia) {
        this.dzien = dzien;
        this.nauczyciel = nauczyciel;
        this.nauczycielZastepujacy = nauczycielZastepujacy;
        this.miejsce = miejsce;
        this.godzinaRozpoczecia = godzinaRozpoczecia;
        this.godzinazZakonczenia = godzinazZakonczenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dzien getDzien() {
        return dzien;
    }

    public void setDzien(Dzien dzien) {
        this.dzien = dzien;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Nauczyciel getNauczycielZastepujacy() {
        return nauczycielZastepujacy;
    }

    public void setNauczycielZastepujacy(Nauczyciel nauczycielZastepujacy) {
        this.nauczycielZastepujacy = nauczycielZastepujacy;
    }

    public Miejsce getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(Miejsce miejsce) {
        this.miejsce = miejsce;
    }

    public LocalTime getGodzinaRozpoczecia() {
        return godzinaRozpoczecia;
    }

    public void setGodzinaRozpoczecia(LocalTime godzinaRozpoczecia) {
        this.godzinaRozpoczecia = godzinaRozpoczecia;
    }

    public LocalTime getGodzinazZakonczenia() {
        return godzinazZakonczenia;
    }

    public void setGodzinazZakonczenia(LocalTime godzinazZakonczenia) {
        this.godzinazZakonczenia = godzinazZakonczenia;
    }

    @Override
    public String toString() {
        return "Dyzur{" +
                "id=" + id +
                ", dzien=" + dzien +
                ", nauczyciel=" + nauczyciel +
                ", nauczycielZastepujacy=" + nauczycielZastepujacy +
                ", miejsce='" + miejsce + '\'' +
                ", godzinaRozpoczecia=" + godzinaRozpoczecia +
                ", godzinazZakonczenia=" + godzinazZakonczenia +
                '}';
    }
}
