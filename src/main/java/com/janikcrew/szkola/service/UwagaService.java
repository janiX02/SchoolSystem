package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.*;

import java.util.List;

public interface UwagaService {
    List<Uwaga> uwagiWystawione(Osoba osoba);
    List<Uwaga> uwagiOtrzymane(Uczen uczen);
    Uwaga findNoteById(int id);
    void update(Uwaga uwaga);
    void utworzUwage(Uwaga uwaga, Nauczyciel wystawiajacy, Uczen otrzymujacy);
    void utworzUwage(Uwaga uwaga);
    void usunUwageById(int id);
}
