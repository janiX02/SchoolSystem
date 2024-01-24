package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="transakcja")
public class Transakcja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_budzetu")
    private Budzet budzet;

    @Column(name="kwota")
    private double kwota;

    @Column(name="rodzaj")
    private String rodzaj;

    @Column(name="data")
    private LocalDate data;

    @Column(name="godzina")
    private LocalTime godzina;

    @Column(name="opis")
    private String opis;

    public Transakcja() {

    }

    public Transakcja(String rodzaj, double kwota, LocalDate data, LocalTime godzina, String opis) {

        this.rodzaj = rodzaj;
        this.data = data;
        this.kwota = kwota;
        this.godzina = godzina;
        this.opis = opis;
    }
    public Transakcja(Budzet budzet, String rodzaj, double kwota, LocalDate data, LocalTime godzina, String opis) {
        this.budzet = budzet;
        this.rodzaj = rodzaj;
        this.data = data;
        this.kwota = kwota;
        this.godzina = godzina;
        this.opis = opis;
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

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public void setGodzina(LocalTime godzina) {
        this.godzina = godzina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    @Override
    public String toString() {
        return "Transakcja{" +
                "id=" + id +
                ", rodzaj='" + rodzaj + '\'' +
                ", data=" + data +
                ", godzina=" + godzina +
                ", opis='" + opis + '\'' +
                '}';
    }
}
