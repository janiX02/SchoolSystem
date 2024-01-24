package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Dzien;

import java.time.LocalDate;
import java.util.List;

public interface KalendarzDAO {
    void save(Dzien dzien);
    List<Dzien> getKalendarzByDzien(String dzien);
    List<Dzien> getKalendarz();
    void deleteDzienByDate(LocalDate date);
}
