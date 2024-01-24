package com.janikcrew.szkola.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "wiadomość")
public class Wiadomosc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nadawcy")
    private Osoba nadawca;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_odbiorcy")
    private Osoba odbiorca;

    @Column(name = "tytul")
    @NotNull(message = "Pole nie może pozostać puste!")
    @Size(min = 1, message = "Pole nie może pozostać puste!")
    private String tytul;

    @Column(name = "tresc")
    @NotNull(message = "Pole nie może pozostać puste!")
    @Size(min = 1, message = "Pole nie może pozostać puste!")
    private String tresc;

    @Column(name = "data_wyslania")
    private LocalDate dataWyslania;

    @Column(name = "czas_wyslania")
    private LocalTime czasWyslania;

    @Transient
    private boolean czyUzytkownikNadawca;

    @Transient
    private boolean czyWybranaKlasa;

    @Transient
    private boolean czyWybranyUczen;

    @Transient
    private boolean czyWybranyNauczyciel;

    @Transient
    private Klasa wybranaKlasa;

    @Transient
    private boolean czyWybranyTypOdbiorcy;

    @Transient
    private String typWiadomosci;

    @Transient
    private int idNadawcy;

    @Transient
    private int idOdbiorcy;

    @Transient
    private String nazwiskoOdbiorcy;

    @Transient
    private Uczen wybranyUczen;

    @Transient
    private Rodzic wybranyRodzic;

    public Wiadomosc() {
        dataWyslania = LocalDate.now();
        czasWyslania = LocalTime.now();
    }

    public Wiadomosc(Osoba nadawca, Uczen odbiorca, String tytul, String tresc) {
        this.nadawca = nadawca;
        this.odbiorca = odbiorca;
        this.tytul = tytul;
        this.tresc = tresc;
        dataWyslania = LocalDate.now();
        czasWyslania = LocalTime.now();
    }

    public Wiadomosc(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
        dataWyslania = LocalDate.now();
        czasWyslania = LocalTime.now();
    }
    public String getFormattedDate() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(dataWyslania)) {
            if (czasWyslania.getMinute() == currentTime.getMinute()) {
                return "Przed chwilą";
            }
            else if (czasWyslania.getHour() == currentTime.getHour()) {
                int numberOfMinutes = currentTime.getMinute() - czasWyslania.getMinute();
                return numberOfMinutes + " min temu";
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String formattedTime = czasWyslania.format(formatter);
                return "Dzisiaj o " + formattedTime;
            }
        }
        else if (currentDate.minusDays(1).equals(dataWyslania)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = czasWyslania.format(formatter);
            return "Wczoraj o " + formattedTime;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = czasWyslania.format(formatter);
            String month = convertMonth();
            int day = dataWyslania.getDayOfMonth();
            if (dataWyslania.getYear() == currentDate.getYear()) {
                return day + " " + month + " o " + formattedTime;
            }
            else {
                int year = dataWyslania.getYear();
                return day + " " + month +" "+ year + " o " + formattedTime;
            }
        }
    }
    private String convertMonth() {
        return switch (dataWyslania.getMonth().getValue()) {
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

    public Osoba getNadawca() {
        return nadawca;
    }

    public void setNadawca(Osoba nadawca) {
        this.nadawca = nadawca;
    }

    public Osoba getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(Osoba odbiorca) {
        this.odbiorca = odbiorca;
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

    public LocalDate getDataWyslania() {
        return dataWyslania;
    }

    public void setDataWyslania(LocalDate dataWyslania) {
        this.dataWyslania = dataWyslania;
    }

    public LocalTime getCzasWyslania() {
        return czasWyslania;
    }

    public void setCzasWyslania(LocalTime czasWyslania) {
        this.czasWyslania = czasWyslania;
    }

    public boolean isCzyUzytkownikNadawca() {
        return czyUzytkownikNadawca;
    }

    public void setCzyUzytkownikNadawca(boolean czyUzytkownikNadawca) {
        this.czyUzytkownikNadawca = czyUzytkownikNadawca;
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


    public boolean isCzyWybranyTypOdbiorcy() {
        return czyWybranyTypOdbiorcy;
    }

    public void setCzyWybranyTypOdbiorcy(boolean czyWybranyTypOdbiorcy) {
        this.czyWybranyTypOdbiorcy = czyWybranyTypOdbiorcy;
    }

    public String getTypWiadomosci() {
        return typWiadomosci;
    }

    public void setTypWiadomosci(String typWiadomosci) {
        this.typWiadomosci = typWiadomosci;
    }

    public boolean isCzyWybranyUczen() {
        return czyWybranyUczen;
    }

    public void setCzyWybranyUczen(boolean czyWybranyUczen) {
        this.czyWybranyUczen = czyWybranyUczen;
    }

    public int getIdOdbiorcy() {
        return idOdbiorcy;
    }

    public void setIdOdbiorcy(int idOdbiorcy) {
        this.idOdbiorcy = idOdbiorcy;
    }

    public Uczen getWybranyUczen() {
        return wybranyUczen;
    }

    public void setWybranyUczen(Uczen wybranyUczen) {
        this.wybranyUczen = wybranyUczen;
    }

    public Rodzic getWybranyRodzic() {
        return wybranyRodzic;
    }

    public boolean isCzyWybranyNauczyciel() {
        return czyWybranyNauczyciel;
    }

    public void setCzyWybranyNauczyciel(boolean czyWybranyNauczyciel) {
        this.czyWybranyNauczyciel = czyWybranyNauczyciel;
    }

    public void setWybranyRodzic(Rodzic wybranyRodzic) {
        this.wybranyRodzic = wybranyRodzic;
    }

    public int getIdNadawcy() {
        return idNadawcy;
    }

    public void setIdNadawcy(int idNadawcy) {
        this.idNadawcy = idNadawcy;
    }

    public String getNazwiskoOdbiorcy() {
        return nazwiskoOdbiorcy;
    }

    public void setNazwiskoOdbiorcy(String nazwiskoOdbiorcy) {
        this.nazwiskoOdbiorcy = nazwiskoOdbiorcy;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "id=" + id +
                ", nadawca=" + nadawca +
                ", odbiorca=" + odbiorca +
                ", tytul='" + tytul + '\'' +
                ", tresc='" + tresc + '\'' +
                '}';
    }
}
