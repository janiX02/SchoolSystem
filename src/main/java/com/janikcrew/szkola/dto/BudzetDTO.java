package com.janikcrew.szkola.dto;

import com.janikcrew.szkola.entity.Transakcja;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class BudzetDTO {

    private int id;


    private double budzet;

    private List<Transakcja> listaTransakcji;

    public BudzetDTO() {
        this.budzet = 0.0;
        listaTransakcji = new ArrayList<>();
    }

    public BudzetDTO(double budzet) {
        listaTransakcji = new ArrayList<>();
        this.budzet = budzet;
    }
    public void dodajTransakcje(Transakcja transakcja) throws Exception {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBudzet() {
        return budzet;
    }

    public void setBudzet(double budzet) {
        this.budzet = budzet;
    }

    public List<Transakcja> getListaTransakcji() {
        return listaTransakcji;
    }

    public void setListaTransakcji(List<Transakcja> listaTransakcji) {
        this.listaTransakcji = listaTransakcji;
    }

    @Override
    public String toString() {
        return "Budzet{" +
                "id=" + id +
                ", budzet=" + budzet +
                '}';
    }
}
