package com.janikcrew.szkola.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "uwaga")
public class Uwaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela")
    private Osoba wystawiajacy;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ucznia")
    private Uczen otrzymujacy;

    @Transient
    private int idOtrzymujacego;

    @Column(name = "rodzaj")
    @NotNull(message = "Pole nie może pozostać puste!")
    private String rodzaj;

    @Column(name = "tytul")
    @NotNull(message = "Pole nie może pozostać puste!")
    @Size(min = 1, message = "Pole nie może pozostać puste!")
    private String tytul;

    @Column(name = "treść")
    @NotNull(message = "Pole nie może pozostać puste!")
    @Size(min = 1, message = "Pole nie może pozostać puste!")
    private String tresc;

    @Column(name = "data")
    private LocalDate dataWystawienia;

    @Column(name = "czas")
    private LocalTime czasWystawienia;

    @Transient
    private Klasa klasaWybrana;

    @Transient
    private boolean czyWybranaKlasa;

    @Transient
    private boolean czyWybranyUczen;

    @Transient
    private boolean czyNowaUwaga;

    @Transient
    private boolean czyPustyTytul;

    @Transient
    private boolean czyPustaTresc;

    @Transient
    private boolean czyPustyRodzajUwagi;

    @Transient
    private String nazwaKlasy;

    public Uwaga() {
        dataWystawienia = LocalDate.now();
        czasWystawienia = LocalTime.now();
    }

    public Uwaga(String rodzaj, String tytul, String tresc) {
        this.rodzaj = rodzaj;
        this.tytul = tytul;
        this.tresc = tresc;
        dataWystawienia = LocalDate.now();
        czasWystawienia = LocalTime.now();
    }

    public Uwaga(Nauczyciel wystawiajacy, Uczen otrzymujacy, String rodzaj, String tytul, String tresc) {
        this.wystawiajacy = wystawiajacy;
        this.otrzymujacy = otrzymujacy;
        this.rodzaj = rodzaj;
        this.tytul = tytul;
        this.tresc = tresc;
        dataWystawienia = LocalDate.now();
        czasWystawienia = LocalTime.now();
    }
    public String getFormattedDate() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(dataWystawienia)) {
            if (czasWystawienia.getMinute() == currentTime.getMinute()) {
                return "Przed chwilą";
            }
            else if (czasWystawienia.getHour() == currentTime.getHour()) {
                int numberOfMinutes = currentTime.getMinute() - czasWystawienia.getMinute();
                return numberOfMinutes + " min temu";
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String formattedTime = czasWystawienia.format(formatter);
                return "Dzisiaj o " + formattedTime;
            }
        }
        else if (currentDate.minusDays(1).equals(dataWystawienia)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = czasWystawienia.format(formatter);
            return "Wczoraj o " + formattedTime;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = czasWystawienia.format(formatter);
            String month = convertMonth();
            int day = dataWystawienia.getDayOfMonth();
            if (dataWystawienia.getYear() == currentDate.getYear()) {
                return day + " " + month + " o " + formattedTime;
            }
            else {
                int year = dataWystawienia.getYear();
                return day + " " + month +" "+ year + " o " + formattedTime;
            }
        }
    }
    private String convertMonth() {
        return switch (dataWystawienia.getMonth().getValue()) {
            case 1 -> "styczeń";
            case 2 -> "luty";
            case 3 -> "marzec";
            case 4 -> "kwiecień";
            case 5 -> "maj";
            case 6 -> "czerwiec";
            case 7 -> "lipiec";
            case 8 -> "sierpień";
            case 9 -> "wrzesień";
            case 10 -> "październik";
            case 11 -> "listopad";
            case 12 -> "grudzień";
            default -> null;
        };
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Osoba getWystawiajacy() {
        return wystawiajacy;
    }

    public void setWystawiajacy(Osoba wystawiajacy) {
        this.wystawiajacy = wystawiajacy;
    }

    public Uczen getOtrzymujacy() {
        return otrzymujacy;
    }

    public void setOtrzymujacy(Uczen otrzymujacy) {
        this.otrzymujacy = otrzymujacy;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public LocalDate getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(LocalDate dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public LocalTime getCzasWystawienia() {
        return czasWystawienia;
    }

    public void setCzasWystawienia(LocalTime czasWystawienia) {
        this.czasWystawienia = czasWystawienia;
    }

    public boolean isCzyWybranaKlasa() {
        return czyWybranaKlasa;
    }

    public void setCzyWybranaKlasa(boolean czyWybranaKlasa) {
        this.czyWybranaKlasa = czyWybranaKlasa;
    }

    public boolean isCzyWybranyUczen() {
        return czyWybranyUczen;
    }

    public void setCzyWybranyUczen(boolean czyWybranyUczen) {
        this.czyWybranyUczen = czyWybranyUczen;
    }

    public boolean isCzyNowaUwaga() {
        return czyNowaUwaga;
    }

    public void setCzyNowaUwaga(boolean czyNowaUwaga) {
        this.czyNowaUwaga = czyNowaUwaga;
    }

    public String getNazwaKlasy() {
        return nazwaKlasy;
    }

    public void setNazwaKlasy(String nazwaKlasy) {
        this.nazwaKlasy = nazwaKlasy;
    }

    public int getIdOtrzymujacego() {
        return idOtrzymujacego;
    }

    public void setIdOtrzymujacego(int idOtrzymujacego) {
        this.idOtrzymujacego = idOtrzymujacego;
    }

    public boolean isCzyPustyTytul() {
        return czyPustyTytul;
    }

    public void setCzyPustyTytul(boolean czyPustyTytul) {
        this.czyPustyTytul = czyPustyTytul;
    }

    public boolean isCzyPustaTresc() {
        return czyPustaTresc;
    }

    public void setCzyPustaTresc(boolean czyPustaTresc) {
        this.czyPustaTresc = czyPustaTresc;
    }

    public boolean isCzyPustyRodzajUwagi() {
        return czyPustyRodzajUwagi;
    }

    public void setCzyPustyRodzajUwagi(boolean czyPustyRodzajUwagi) {
        this.czyPustyRodzajUwagi = czyPustyRodzajUwagi;
    }

    public Klasa getKlasaWybrana() {
        return klasaWybrana;
    }

    public void setKlasaWybrana(Klasa klasaWybrana) {
        this.klasaWybrana = klasaWybrana;
    }

    @Override
    public String toString() {
        return "Uwaga{" +
                "id=" + id +
                ", wystawiajacy=" + wystawiajacy +
                ", otrzymujacy=" + otrzymujacy +
                ", rodzaj='" + rodzaj + '\'' +
                ", tytul='" + tytul + '\'' +
                ", tresc='" + tresc + '\'' +
                ", dataWystawienia=" + dataWystawienia +
                ", czasWystawienia=" + czasWystawienia +
                '}';
    }
}
