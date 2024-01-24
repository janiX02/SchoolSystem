package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Budzet;
import com.janikcrew.szkola.entity.Transakcja;

import java.util.List;

public interface BudzetService {

    void dodajBudzet(Budzet budzet);
    void dodajTransakcjeDoBudzetu(Budzet budzet, Transakcja transakcja) throws Exception;
    void znajdzListeTransakcjiBudzetu(Budzet budzet);
    Budzet findBudzetById(int id);
}
