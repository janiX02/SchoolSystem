package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.GodzinaLekcyjna;
import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Przedmiot;
import com.janikcrew.szkola.entity.Klasa;
import com.janikcrew.szkola.entity.Miejsce;

import java.time.LocalDate;
import java.time.LocalTime;

public interface GodzinaLekcyjnaService {
    GodzinaLekcyjna getGodzinaLekcyjnaById(int id);
    void dodajGodzinaLekcyjnaDoPlanuLekcji(Przedmiot przedmiot, Klasa klasa, Miejsce miejsce, String dzien, String godzinaRozpoczecia, String dataRozpoczecia);
    void dodajZdarzenieDoGodzinyLekcyjnej(int idGodzinyLekcyjnej, String zdarzenie);
    void usunGodzineZPlanuByIdPrzedmiotu(int idPrzedmiotu);
    void dodajZastepstwoDoGodzinyLekcyjnej(int idGodzinyLekcyjnej, int idNauczycielaZastepujacego);
    void dodajZastepujacaSale(int idGodzinyLekcyjnej, int idSali);
}
