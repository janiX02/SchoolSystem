package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity(name="osoba")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rola", discriminatorType = DiscriminatorType.STRING)
public abstract class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_budzetu")
    private Budzet budzet;

    @Column(name="pesel")
    private String pesel;

    @Column(name="imie")
    private String imie;

    @Column(name="nazwisko")
    private String nazwisko;

    @Column(name="haslo")
    private String haslo;

    @Column(name="email")
    private String email;

    @Column(name="active")
    private int active;

    @Column(name="data_uro")
    private LocalDate dataUrodzenia;

    @Column(name="accepted")
    private int accepted;

    @Transient
    private boolean czyPosiadaWiadomosci;

    @Transient
    private boolean czyUzytkownikToNadawca;

    @Transient
    private boolean czyWybranyNauczyciel;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_odbiorcy")
    private List<Wiadomosc> wiadomosciOdebrane;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nadawcy")
    private List<Wiadomosc> wiadomosciWyslane;

    public Osoba() {

    }

    public Osoba(String pesel, String imie, String nazwisko, String email, LocalDate dataUrodzenia) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.dataUrodzenia = dataUrodzenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Budzet getBudzet() {
        return budzet;
    }

    public void setBudzet(Budzet budzet) {
        this.budzet = budzet;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public List<Wiadomosc> getWiadomosciOdebrane() {
        return wiadomosciOdebrane;
    }

    public void setWiadomosciOdebrane(List<Wiadomosc> wiadomosciOdebrane) {
        this.wiadomosciOdebrane = wiadomosciOdebrane;
    }

    public List<Wiadomosc> getWiadomosciWyslane() {
        return wiadomosciWyslane;
    }

    public void setWiadomosciWyslane(List<Wiadomosc> wiadomosciWyslane) {
        this.wiadomosciWyslane = wiadomosciWyslane;
    }

    public boolean isCzyPosiadaWiadomosci() {
        return czyPosiadaWiadomosci;
    }

    public void setCzyPosiadaWiadomosci(boolean czyPosiadaWiadomosci) {
        this.czyPosiadaWiadomosci = czyPosiadaWiadomosci;
    }

    public boolean isCzyUzytkownikToNadawca() {
        return czyUzytkownikToNadawca;
    }

    public void setCzyUzytkownikToNadawca(boolean czyUzytkownikToNadawca) {
        this.czyUzytkownikToNadawca = czyUzytkownikToNadawca;
    }

    public boolean isCzyWybranyNauczyciel() {
        return czyWybranyNauczyciel;
    }

    public void setCzyWybranyNauczyciel(boolean czyWybranyNauczyciel) {
        this.czyWybranyNauczyciel = czyWybranyNauczyciel;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", budzetu=" + budzet +
                ", pesel=" + pesel +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                '}';
    }
}
