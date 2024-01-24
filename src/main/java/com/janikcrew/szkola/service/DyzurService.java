package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.*;

public interface DyzurService {
    Dyzur getDyzurById(int id);
    void dodajDyzurDoPlanuDyzurow(Nauczyciel nauczyciel, Miejsce miejsce, String nazwaDnia, String godzinaRozpoczecia, String dataRozpoczecia);
    //void usunGodzineZPlanuByIdPrzedmiotu(int idPrzedmiotu);
    void dodajZastepstwoDoDyzuru(int idDyzuru, int idNauczycielaZastepujacego);
    void usunWszystkieDyzury();
}
