package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.BudzetDAO;
import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.dao.OsobaDAOImpl;
import com.janikcrew.szkola.dao.PrzedmiotDAO;
import com.janikcrew.szkola.entity.*;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OsobaServiceImpl implements OsobaService {
    private OsobaDAO osobaDAO;
    private BudzetDAO budzetDAO;
    private PrzedmiotDAO przedmiotDAO;

    @Autowired
    public OsobaServiceImpl(OsobaDAO osobaDAO, BudzetDAO budzetDAO, PrzedmiotDAO przedmiotDAO) {
        this.osobaDAO = osobaDAO;
        this.budzetDAO = budzetDAO;
        this.przedmiotDAO = przedmiotDAO;
    }

    @Override
    public Osoba findOsobaById(int id) {
        return osobaDAO.findOsobaById(id);
    }

    @Override
    public Osoba findOsobaByEmail(String email) {
        return osobaDAO.findOsobaByEmail(email);
    }


    @Override
    public Nauczyciel findNauczycielByIdZPrzedmiotami(int id) {
        Nauczyciel nauczyciel = (Nauczyciel)osobaDAO.findOsobaById(id);
        ArrayList<Przedmiot> listaPrzedmiotow = (ArrayList<Przedmiot>) przedmiotDAO.findPrzedmiotyByNauczycielId(id);
        nauczyciel.setListaPrzedmiotow(listaPrzedmiotow);
        return nauczyciel;
    }

    @Override
    public List<Uczen> findStudentsByClassName(String className) {
        return osobaDAO.findStudentsByClassName(className);
    }

    @Override
    public List<Nauczyciel> findAllTeachers() {
        return osobaDAO.findAllTeachers();
    }

    @Override
    public List<Uczen> findNotAcceptedStudents() {
        return osobaDAO.findNotAcceptedStudents();
    }

    @Override
    public List<Rodzic> findNotAcceptedParents() {
        return osobaDAO.findNotAcceptedParents();
    }


    @Override
    public void update(Osoba osoba) {
        osobaDAO.update(osoba);
    }

    @Override
    public void dodajRodzicaUcznia(Rodzic rodzic, Uczen uczen) {
        osobaDAO.save(rodzic);
        osobaDAO.save(uczen);
        Budzet budzetRodzica = new Budzet();
        budzetDAO.save(budzetRodzica);
        rodzic.setBudzet(budzetRodzica);
        rodzic.setDziecko(uczen);
        uczen.setRodzic(rodzic);
        osobaDAO.update(uczen);
        osobaDAO.update(rodzic);
    }



    @Override
    public void dodajNauczyciela(Nauczyciel nauczyciel) {
        osobaDAO.save(nauczyciel);
        Budzet budzet = new Budzet();
        budzetDAO.save(budzet);
        nauczyciel.setBudzet(budzet);
        osobaDAO.update(nauczyciel);
    }

    @Override
    public void dodajUzytkownika(Osoba osoba, Budzet budzet) {
        osobaDAO.save(osoba);
        budzetDAO.save(budzet);
        osoba.setBudzet(budzet);
        osobaDAO.update(osoba);
    }

    @Override
    public void dodajBudzetDoOsoby(Osoba osoba, Budzet budzet) {
        budzetDAO.save(budzet);
        osoba.setBudzet(budzet);
        osobaDAO.update(osoba);
    }

    @Override
    public void deletePersonById(int id) {
        osobaDAO.deleteOsobaById(id);
    }
}
