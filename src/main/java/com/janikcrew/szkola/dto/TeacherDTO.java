package com.janikcrew.szkola.dto;

import com.janikcrew.szkola.entity.Budzet;
import com.janikcrew.szkola.entity.Nauczyciel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.hibernate.boot.model.relational.NamedAuxiliaryDatabaseObject;

import java.time.LocalDate;

public class TeacherDTO {
    private String pesel;

    private String imie;

    private String nazwisko;

    private String haslo;

    private String email;

    private String dataUrodzenia;

    public TeacherDTO() {

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

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

}
