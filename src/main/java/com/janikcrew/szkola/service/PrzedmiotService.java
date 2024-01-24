package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Klasa;
import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Przedmiot;

import java.util.List;

public interface PrzedmiotService {
    void dodajPrzedmiot(Przedmiot przedmiot, Nauczyciel prowadzacy, Klasa klasa);
    List<Przedmiot> findSubjectsByClassName(String nazwaKlasy);
    List<Przedmiot> findSubjectsByClassNameAndTeacherId(int idNayczyciela, String nazwaKlasy);
    Przedmiot findPrzedmiotById(int id);
    void deletePrzedmiotById(int id);
}
