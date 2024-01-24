package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Przedmiot;

import java.util.List;

public interface PrzedmiotDAO {
    List<Przedmiot> findPrzedmiotyByNauczycielId(int id);
    List<Przedmiot> findSubjectsByTeacherIdAndClassName(int idNauczyciela, String nazwaKlasy);
    List<Przedmiot> findSubjectsByClassName(String nazwaKlasy);
    Przedmiot findPrzedmiotById(int id);
    void deletePrzedmiotById(int id);
    void save(Przedmiot przedmiot);
    void update(Przedmiot przedmiot);
}
