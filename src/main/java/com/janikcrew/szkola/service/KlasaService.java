package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Klasa;
import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Uczen;

import java.util.List;

public interface KlasaService {
    void dodajKlase(Klasa klasa, Nauczyciel nauczyciel);
    void dodajUcznia(Klasa klasa, Uczen ... listaUczniow);
    Klasa findKlasaByName(String name);
    List<Klasa> findClassesByTeacherId(int id);
    List<Klasa> findAllClasses();
    void delete(Klasa klasa);
}
