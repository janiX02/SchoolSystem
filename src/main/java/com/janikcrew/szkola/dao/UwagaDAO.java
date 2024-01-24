package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Uwaga;
import com.janikcrew.szkola.entity.Wiadomosc;

import java.util.List;

public interface UwagaDAO {
    void save(Uwaga uwaga);
    void update(Uwaga uwaga);
    void deleteUwagaById(int id);
    Uwaga findUwagaById(int id);
    List<Uwaga> findUwagiWystawioneByIdNauczyciela(int id);
    List<Uwaga> findUwagiWystawioneByIdUcznia(int id);
}
