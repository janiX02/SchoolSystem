package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Transakcja;
import com.janikcrew.szkola.entity.Wydarzenie;

import java.util.List;

public interface WydarzenieDAO {
    List<Wydarzenie> findAllEvents();
    Wydarzenie getEventById(int id);
    void save(Wydarzenie wydarzenie);
    void update(Wydarzenie wydarzenie);
    void delete(Wydarzenie wydarzenie);
}
