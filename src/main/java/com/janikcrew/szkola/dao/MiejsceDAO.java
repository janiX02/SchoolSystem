package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Miejsce;

import java.util.List;

public interface MiejsceDAO {
    Miejsce findMiejsceById(int id);
    List<Miejsce> findAllMiejsca();
    List<Miejsce> findMiejsceByNazwa(String nazwa);
    void save(Miejsce miejsce);
    void update(Miejsce miejsce);
    void deleteMiejsceById(int id);
}
