package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "miejsca")
public class Miejsce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nazwa")
    private String nazwa;

    public Miejsce() {

    }
    public Miejsce(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Miejsce{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
