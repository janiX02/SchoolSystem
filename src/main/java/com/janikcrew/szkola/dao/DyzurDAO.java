package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Dyzur;

import java.util.List;

public interface DyzurDAO {
    Dyzur getDyzurById(int id);
    List<Dyzur> getListOfDyzurByNauczycielId(int id);
    List<Dyzur> getAll();
    void save(Dyzur dyzur);
    void update(Dyzur dyzur);
    void deleteDyzurById(int id);
}
