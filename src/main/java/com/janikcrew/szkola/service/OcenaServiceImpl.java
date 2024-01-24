package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.KlasaDAO;
import com.janikcrew.szkola.dao.OcenaDAO;
import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcenaServiceImpl implements OcenaService {
    private final OcenaDAO ocenaDAO;
    private final OsobaDAO osobaDAO;
    private final KlasaDAO klasaDAO;

    @Autowired
    public OcenaServiceImpl(OcenaDAO ocenaDAO, OsobaDAO osobaDAO, KlasaDAO klasaDAO) {
        this.ocenaDAO = ocenaDAO;
        this.osobaDAO = osobaDAO;
        this.klasaDAO = klasaDAO;
    }

    @Override
    public void dodajOcene(Uczen uczen, Nauczyciel nauczyciel, Przedmiot przedmiot, double wartosc, int waga, String etykieta) {
        Ocena ocena = new Ocena(wartosc, waga, etykieta);
        ocenaDAO.save(ocena);
        ocena.setUczen(uczen);
        ocena.setKlasa(uczen.getKlasa());
        ocena.setNauczyciel(nauczyciel);
        ocena.setPrzedmiot(przedmiot);
        ocenaDAO.update(ocena);
    }

    @Override
    public void dodajOcene(Ocena ocena) {
        ocenaDAO.save(ocena);
    }

    @Override
    public void aktualizujOcene(int idOceny, int nowaWartosc) {
        Ocena ocena = ocenaDAO.findOcenaById(idOceny);
        ocena.setWartosc(nowaWartosc);
        ocenaDAO.update(ocena);

    }

    @Override
    public void aktualizujOcene(int idOceny, String nowaEtykieta) {
        Ocena ocena = ocenaDAO.findOcenaById(idOceny);
        ocena.setEtykieta(nowaEtykieta);
        ocenaDAO.update(ocena);
    }

    @Override
    public void aktualizujOcene(int idOceny, double nowaWartosc, int nowaWaga, String nowaEtykieta) {
        Ocena ocena = ocenaDAO.findOcenaById(idOceny);
        ocena.setWartosc(nowaWartosc);
        ocena.setEtykieta(nowaEtykieta);
        ocena.setWaga(nowaWaga);
        ocenaDAO.update(ocena);
    }

    @Override
    public void updateGrade(Ocena ocena) {
        ocenaDAO.update(ocena);
    }

    @Override
    public void deleteGradeById(int id) {
        ocenaDAO.deleteOcenaById(id);
    }

    @Override
    public void usunOceneById(int idOceny) {
        ocenaDAO.deleteOcenaById(idOceny);
    }

    @Override
    public Ocena getGradeById(int id) {
        return ocenaDAO.findOcenaById(id);
    }

    @Override
    public List<Ocena> listaOcenUcznia(int idUcznia, int idPrzedmiotu) {
        return ocenaDAO.findOcenyByIdUcznia(idUcznia, idPrzedmiotu);
    }
}
