package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Miejsce;

public interface MiejsceService {
    Miejsce findMiejsceById(int id);
    void dodajMiejsce(String nazwa);
    void aktualizujMiejsce(String staraNazwa, String nowaNazwa);
    void usunMiejsceById(int id);
}
