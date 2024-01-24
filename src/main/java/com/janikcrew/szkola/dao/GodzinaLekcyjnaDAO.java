package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.GodzinaLekcyjna;

import java.util.List;

public interface GodzinaLekcyjnaDAO {

    GodzinaLekcyjna getGodzinaLekcyjnaById(int id);
    List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByKlasaName(String name);
    List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByNauczycielId(int id);
    List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByPrzedmiotId(int id);
    List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByMiejsceId(int id);
    void save(GodzinaLekcyjna godzinaLekcyjna);
    void update(GodzinaLekcyjna godzinaLekcyjna);
    void deleteGodzinaLekcyjnaById(int id);

}
