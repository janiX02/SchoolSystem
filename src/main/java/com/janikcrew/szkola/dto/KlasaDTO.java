package com.janikcrew.szkola.dto;
import com.janikcrew.szkola.entity.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class KlasaDTO {

    private String nazwa;
    private List<Uczen> listaUczniow;

    private List<Przedmiot> listaPrzedmiotow;

    private Nauczyciel wychowawca;

    public KlasaDTO() {
        listaUczniow = new ArrayList<>();
    }

    public KlasaDTO(String nazwa, List<Uczen> listaUczniow, Nauczyciel wychowawca) {
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
    public KlasaDTO(String nazwa) {
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

}
