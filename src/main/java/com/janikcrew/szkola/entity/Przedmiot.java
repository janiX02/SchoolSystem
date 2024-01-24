package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

@Entity
@Table(name="przedmiot")
public class Przedmiot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel prowadzacy;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="nazwa_klasy")
    private Klasa klasa;

    @Column(name="nazwa")
    private String nazwa;

    public Przedmiot() {

    }
    public Przedmiot(String nazwa) {
        this.nazwa = nazwa;
    }
    public Przedmiot(String nazwa, Nauczyciel prowadzacy) {
        this.prowadzacy = prowadzacy;
        this.nazwa = nazwa;
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

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    /*@Override
    public String toString() {
        return "Przedmiot{" +
                "id=" + id +
                ", prowadzacy=" + prowadzacy +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

     */
}
