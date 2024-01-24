package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.KlasaDAO;
import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.dao.PrzedmiotDAO;
import com.janikcrew.szkola.entity.Klasa;
import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Przedmiot;
import com.janikcrew.szkola.entity.Uczen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KlasaServiceImpl implements KlasaService {

    private final KlasaDAO klasaDAO;
    private final OsobaDAO osobaDAO;
    private final PrzedmiotDAO przedmiotDAO;

    @Autowired
    public KlasaServiceImpl(KlasaDAO klasaDAO, OsobaDAO osobaDAO, PrzedmiotDAO przedmiotDAO) {
        this.klasaDAO = klasaDAO;
        this.osobaDAO = osobaDAO;
        this.przedmiotDAO = przedmiotDAO;
    }
    @Override
    public void dodajKlase(Klasa klasa, Nauczyciel nauczyciel) {
        klasaDAO.save(klasa);
        nauczyciel.setKlasaPodOpieka(klasa);
        klasa.setWychowawca(nauczyciel);
        klasaDAO.update(klasa);
        osobaDAO.update(nauczyciel);
    }

    @Override
    public void dodajUcznia(Klasa klasa, Uczen ... listaUczniow) {
        for (Uczen uczen : listaUczniow) {
            klasa.dodajUcznia(uczen);
            uczen.setKlasa(klasa);
            osobaDAO.update(uczen);
            klasaDAO.update(klasa);
        }

    }

    @Override
    public Klasa findKlasaByName(String name) {
        return klasaDAO.findKlasaByNazwa(name);
    }

    @Override
    public List<Klasa> findClassesByTeacherId(int id) {
        List<Przedmiot> listaPrzedmiotow = przedmiotDAO.findPrzedmiotyByNauczycielId(id);
        List<Klasa> listaKlas = new ArrayList<>();

        outerLoop:
        for (Przedmiot przedmiot : listaPrzedmiotow) {
            Klasa klasa = przedmiot.getKlasa();
            if (!listaKlas.isEmpty()) {
                for (Klasa klasa1 : listaKlas) {
                    if (klasa.equals(klasa1)) {
                        continue outerLoop;
                    }
                }
                listaKlas.add(klasa);
            }
            else {
                listaKlas.add(klasa);
            }
        }
        Collections.sort(listaKlas);
        return listaKlas;
    }

    @Override
    public List<Klasa> findAllClasses() {
        return klasaDAO.findAllClasses();
    }

    @Override
    public void delete(Klasa klasa) {
        Nauczyciel nauczyciel = klasa.getWychowawca();
        nauczyciel.setKlasaPodOpieka(null);
        klasa.setWychowawca(null);
        String nazwa = klasa.getNazwa();
        klasaDAO.update(klasa);
        osobaDAO.update(nauczyciel);
        klasaDAO.deleteKlasaByName(nazwa);
    }
}
