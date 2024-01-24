package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.*;

import java.util.List;

public interface OcenaService {
    void dodajOcene(Uczen uczen, Nauczyciel nauczyciel, Przedmiot przedmiot, double wartosc, int waga, String etykieta);
    void dodajOcene(Ocena ocena);
    void aktualizujOcene(int idOceny, int nowaWartosc);
    void aktualizujOcene(int idOceny, String nowaEtykieta);
    void aktualizujOcene(int idOceny, double nowaWartosc, int nowaWaga, String nowaEtykieta);
    void updateGrade(Ocena ocena);
    void deleteGradeById(int id);
    void usunOceneById(int idOceny);
    Ocena getGradeById(int id);
    List<Ocena> listaOcenUcznia(int idUcznia, int idPrzedmiotu);
}
