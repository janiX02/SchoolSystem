package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.*;

import java.util.List;

public interface OsobaDAO {
    void save(Osoba osoba);
    void update(Osoba osoba);
    Osoba findOsobaById(int id);
    Osoba findOsobaByEmail(String email);
    List<Uczen> findStudentsByClassName(String nazwaKlasy);
    List<Nauczyciel> findAllTeachers();
    List<Uczen> findNotAcceptedStudents();
    List<Rodzic> findNotAcceptedParents();
    void deleteOsobaById(int id);

}
