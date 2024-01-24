package com.janikcrew.szkola.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
@Table(name = "kalendarz")
public class Dzien {
    @Id
    @Column(name = "data")
    private LocalDate data;

    @Column(name = "nazwa_dnia")
    private String nazwaDnia;

    public Dzien() {

    }

    public Dzien(LocalDate data) {
        this.data = data;
        String temp = data.getDayOfWeek().toString();
        nazwaDnia = getDayInPolish(temp);
    }
    private String getDayInPolish(String name) {
        return switch (name) {
            case "MONDAY" -> "poniedziałek";
            case "TUESDAY" -> "wtorek";
            case "WEDNESDAY" -> "środa";
            case "THURSDAY" -> "czwartek";
            case "FRIDAY" -> "piątek";
            case "SATURDAY" -> "sobota";
            case "SUNDAY" -> "niedziela";
            default -> null;
        };
    }
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNazwaDnia() {
        return nazwaDnia;
    }

    public void setNazwaDnia(String nazwaDnia) {
        this.nazwaDnia = nazwaDnia;
    }


    @Override
    public String toString() {
        return "Dzien{" +
                "data=" + data +
                ", nazwaDnia='" + nazwaDnia + '\'' +
                '}';
    }
}
