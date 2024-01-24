package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.MiejsceDAO;
import com.janikcrew.szkola.entity.Miejsce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MiejsceServiceImpl implements MiejsceService {
    private final MiejsceDAO miejsceDAO;

    @Autowired
    public MiejsceServiceImpl(MiejsceDAO miejsceDAO) {
        this.miejsceDAO = miejsceDAO;
    }

    @Override
    public Miejsce findMiejsceById(int id) {
        return miejsceDAO.findMiejsceById(id);
    }

    @Override
    public void dodajMiejsce(String nazwa) {
        miejsceDAO.save(new Miejsce(nazwa));
    }

    @Override
    public void aktualizujMiejsce(String staraNazwa, String nowaNazwa) {

        ArrayList<Miejsce> miejsca = (ArrayList<Miejsce>) miejsceDAO.findMiejsceByNazwa(staraNazwa);

        for (Miejsce miejsce : miejsca) {
            miejsce.setNazwa(nowaNazwa);
            miejsceDAO.update(miejsce);
        }
    }

    @Override
    public void usunMiejsceById(int id) {
        miejsceDAO.deleteMiejsceById(id);
    }
}
