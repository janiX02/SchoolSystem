package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="budżet")
public class Budzet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="budżet")
    private double budzet;

    @OneToMany(mappedBy = "budzet", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Transakcja> listaTransakcji;

    public Budzet() {
        this.budzet = 0.0;
        listaTransakcji = new ArrayList<>();
    }

    public Budzet(double budzet) {
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
