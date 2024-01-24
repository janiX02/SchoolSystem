package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.dao.UwagaDAO;
import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Uczen;
import com.janikcrew.szkola.entity.Uwaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UwagaServiceImpl implements UwagaService {
    private UwagaDAO uwagaDAO;
    private OsobaDAO osobaDAO;

    @Autowired
    public UwagaServiceImpl(OsobaDAO osobaDAO, UwagaDAO uwagaDAO) {
        this.osobaDAO = osobaDAO;
        this.uwagaDAO = uwagaDAO;
    }

    @Override
    public List<Uwaga> uwagiWystawione(Osoba nauczyciel) {
        return uwagaDAO.findUwagiWystawioneByIdNauczyciela(nauczyciel.getId());
    }

    @Override
    public List<Uwaga> uwagiOtrzymane(Uczen uczen) {
        return uwagaDAO.findUwagiWystawioneByIdUcznia(uczen.getId());
    }

    @Override
    public Uwaga findNoteById(int id) {
        return uwagaDAO.findUwagaById(id);
    }

    @Override
    public void update(Uwaga uwaga) {
        uwagaDAO.update(uwaga);
    }

    @Override
    public void utworzUwage(Uwaga uwaga, Nauczyciel wystawiajacy, Uczen otrzymujacy) {
        uwagaDAO.save(uwaga);
        uwaga.setWystawiajacy(wystawiajacy);
        uwaga.setOtrzymujacy(otrzymujacy);
        uwagaDAO.update(uwaga);
    }

    @Override
    public void utworzUwage(Uwaga uwaga) {
        uwagaDAO.save(uwaga);
    }

    @Override
    public void usunUwageById(int id) {
        uwagaDAO.deleteUwagaById(id);
    }
}
