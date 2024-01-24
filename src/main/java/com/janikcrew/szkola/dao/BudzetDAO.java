package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Budzet;
import com.janikcrew.szkola.entity.Transakcja;

import java.util.List;

public interface BudzetDAO {
    List<Transakcja> findTransactionsByBudzetId(int id);
    Budzet findBudzetById(int id);
    Transakcja findTransakcjaById(int id);
    void save(Budzet budzet);
    void save(Transakcja transakcja);
    void update(Budzet budzet);
    void update(Transakcja transakcja);
    void deleteTransactionById(int id);
    void deleteBudzetById(int id);
    void refresh(Budzet budzet);
}
