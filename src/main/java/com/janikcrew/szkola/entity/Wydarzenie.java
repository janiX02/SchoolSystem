package com.janikcrew.szkola.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="wydarzenie")
public class Wydarzenie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autora")
    private Osoba autor;

    @Column(name="data_dodania")
    private LocalDate dataDodania;

    @Column(name="czas_dodania")
    private LocalTime czasDodania;

    @Column(name="tytuł")
    private String tytul;

    @Column(name="treść")
    private String tresc;

    @Column(name="link")
    private String link;

    @Transient
    private boolean czyUzytkownikToAutor;

    @Transient
    private boolean czyNoweWydarzenie;

    @Transient
    private boolean czyPustyTytul;

    @Transient
    private boolean czyPustaTresc;

    public Wydarzenie() {
        dataDodania = LocalDate.now();
        czasDodania = LocalTime.now();
    }

    public Wydarzenie(String tytul, String tresc, Osoba autor) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.autor = autor;
        dataDodania = LocalDate.now();
        czasDodania = LocalTime.now();
        czyUzytkownikToAutor = false;
    }

    public Wydarzenie(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
        dataDodania = LocalDate.now();
        czasDodania = LocalTime.now();
        czyUzytkownikToAutor = false;
    }
    private String convertMonth() {
        return switch (dataDodania.getMonth().getValue()) {
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
    public String getFormattedDate() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(dataDodania)) {
            if (czasDodania.getMinute() == currentTime.getMinute()) {
                return "Przed chwilą";
            }
            else if (czasDodania.getHour() == currentTime.getHour()) {
                int numberOfMinutes = currentTime.getMinute() - czasDodania.getMinute();
                return numberOfMinutes + " min temu";
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String formattedTime = czasDodania.format(formatter);
                return "Dzisiaj o " + formattedTime;
            }
        }
        else if (currentDate.minusDays(1).equals(dataDodania)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = czasDodania.format(formatter);
            return "Wczoraj o " + formattedTime;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = czasDodania.format(formatter);
            String month = convertMonth();
            int day = dataDodania.getDayOfMonth();
            if (dataDodania.getYear() == currentDate.getYear()) {
                return day + " " + month + " o " + formattedTime;
            }
            else {
                int year = dataDodania.getYear();
                return day + " " + month +" "+ year + " o " + formattedTime;
            }
        }
    }

    public boolean isCzyPustaTresc() {
        return czyPustaTresc;
    }

    public void setCzyPustaTresc(boolean czyPustaTresc) {
        this.czyPustaTresc = czyPustaTresc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Osoba getAutor() {
        return autor;
    }

    public void setAutor(Osoba autor) {
        this.autor = autor;
    }

    public LocalDate getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(LocalDate dataDodania) {
        this.dataDodania = dataDodania;
    }

    public LocalTime getCzasDodania() {
        return czasDodania;
    }

    public void setCzasDodania(LocalTime czasDodania) {
        this.czasDodania = czasDodania;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isCzyUzytkownikToAutor() {
        return czyUzytkownikToAutor;
    }

    public void setCzyUzytkownikToAutor(boolean czyUzytkownikToAutor) {
        this.czyUzytkownikToAutor = czyUzytkownikToAutor;
    }

    public boolean isCzyNoweWydarzenie() {
        return czyNoweWydarzenie;
    }

    public void setCzyNoweWydarzenie(boolean czyNoweWydarzenie) {
        this.czyNoweWydarzenie = czyNoweWydarzenie;
    }

    public boolean isCzyPustyTytul() {
        return czyPustyTytul;
    }

    public void setCzyPustyTytul(boolean czyPustyTytul) {
        this.czyPustyTytul = czyPustyTytul;
    }
}
