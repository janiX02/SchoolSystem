package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.*;

import java.util.List;

public interface OsobaService {
    Osoba findOsobaById(int id);
    Osoba findOsobaByEmail(String email);
    Nauczyciel findNauczycielByIdZPrzedmiotami(int id);
    List<Uczen> findStudentsByClassName(String className);
    List<Nauczyciel> findAllTeachers();
    List<Uczen> findNotAcceptedStudents();
    List<Rodzic> findNotAcceptedParents();
    void update(Osoba osoba);
    void dodajRodzicaUcznia(Rodzic rodzic, Uczen uczen);
    void dodajNauczyciela(Nauczyciel nauczyciel);
    void dodajUzytkownika(Osoba osoba, Budzet budzet);
    void dodajBudzetDoOsoby(Osoba osoba, Budzet budzet);
    void deletePersonById(int id);
}
