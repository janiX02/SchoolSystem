package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="klasa")
public class Klasa implements Comparable<Klasa> {

    @Id
    @Column
    private String nazwa;

    @OneToMany(mappedBy = "klasa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Uczen> listaUczniow;

    @OneToMany(mappedBy = "klasa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Przedmiot> listaPrzedmiotow;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_wychowawcy")
    private Nauczyciel wychowawca;

    public Klasa() {
        listaUczniow = new ArrayList<>();
    }

    public Klasa(String nazwa, List<Uczen> listaUczniow, Nauczyciel wychowawca) {
        this.nazwa = nazwa;
        this.listaUczniow = listaUczniow;
        this.wychowawca = wychowawca;
    }
    public void dodajUcznia(Uczen uczen) {
        if (uczen != null) {
            listaUczniow.add(uczen);
        } else
            throw new NullPointerException("Obiekt typu Uczen to null!");
    }
    public void addPrzedmiot(Przedmiot przedmiot) {
        if (przedmiot != null) {
            listaPrzedmiotow.add(przedmiot);
        } else
            throw new NullPointerException("Obiekt typu Przedmiot to null!");
    }
    public Klasa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Uczen> getListaUczniow() {
        return listaUczniow;
    }

    public void setListaUczniow(List<Uczen> listaUczniow) {
        this.listaUczniow = listaUczniow;
    }

    public Nauczyciel getWychowawca() {
        return wychowawca;
    }

    public void setWychowawca(Nauczyciel wychowawca) {
        this.wychowawca = wychowawca;
    }

    public List<Przedmiot> getListaPrzedmiotow() {
        return listaPrzedmiotow;
    }

    public void setListaPrzedmiotow(List<Przedmiot> listaPrzedmiotow) {
        this.listaPrzedmiotow = listaPrzedmiotow;
    }

    @Override
    public String toString() {
        return "Klasa{" +
                "nazwa='" + nazwa + '\'' +
                ", listaUczniow=" + listaUczniow +
                ", wychowawca=" + wychowawca +
                '}';
    }

    @Override
    public int compareTo(Klasa other) {
        return this.getNazwa().compareToIgnoreCase(other.getNazwa());
    }
}
