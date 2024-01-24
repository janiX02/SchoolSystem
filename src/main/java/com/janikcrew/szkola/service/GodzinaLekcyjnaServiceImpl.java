package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.*;
import com.janikcrew.szkola.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class GodzinaLekcyjnaServiceImpl implements GodzinaLekcyjnaService {

    private final GodzinaLekcyjnaDAO godzinaLekcyjnaDAO;

    private final KalendarzDAO kalendarzDAO;

    private final OsobaDAO osobaDAO;

    private final PrzedmiotDAO przedmiotDAO;

    private final MiejsceDAO miejsceDAO;

    @Autowired
    public GodzinaLekcyjnaServiceImpl(GodzinaLekcyjnaDAO godzinaLekcyjnaDAO, KalendarzDAO kalendarzDAO, OsobaDAO osobaDAO, PrzedmiotDAO przedmiotDAO, MiejsceDAO miejsceDAO) {
        this.godzinaLekcyjnaDAO = godzinaLekcyjnaDAO;
        this.kalendarzDAO = kalendarzDAO;
        this.osobaDAO = osobaDAO;
        this.przedmiotDAO = przedmiotDAO;
        this.miejsceDAO = miejsceDAO;
    }

    @Override
    public GodzinaLekcyjna getGodzinaLekcyjnaById(int id) {
        return godzinaLekcyjnaDAO.getGodzinaLekcyjnaById(id);
    }

    @Override
    public void dodajGodzinaLekcyjnaDoPlanuLekcji(Przedmiot przedmiot, Klasa klasa, Miejsce miejsce, String nazwaDnia, String godzRozpoczecia, String dateRozpoczecia) {

        Nauczyciel nauczyciel = przedmiot.getProwadzacy();
        DateTimeFormatter formatterGodz = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalTime godzinaRozpoczecia = LocalTime.parse(godzRozpoczecia, formatterGodz);
        LocalTime godzinaZakonczenia = godzinaRozpoczecia.plusMinutes(45);
        LocalDate dataRozpoczecia = LocalDate.parse(dateRozpoczecia, formatterData);

        ArrayList<Dzien> listaDat = (ArrayList<Dzien>) kalendarzDAO.getKalendarzByDzien(nazwaDnia);


        while (dataRozpoczecia.isAfter(listaDat.get(0).getData())) {
            listaDat.remove(0);
        }

        for (Dzien dzienKalendarzowy : listaDat) {

            System.out.println(dzienKalendarzowy.getData() + ", " + dzienKalendarzowy.getNazwaDnia());
            GodzinaLekcyjna godzinaLekcyjna = new GodzinaLekcyjna();
            godzinaLekcyjnaDAO.save(godzinaLekcyjna);
            godzinaLekcyjna.setDzien(dzienKalendarzowy);
            godzinaLekcyjna.setGodzRozpoczecia(godzinaRozpoczecia);
            godzinaLekcyjna.setGodzZakonczenia(godzinaZakonczenia);
            godzinaLekcyjna.setKlasa(klasa);
            godzinaLekcyjna.setMiejsce(miejsce);
            godzinaLekcyjna.setProwadzacy(nauczyciel);
            godzinaLekcyjna.setPrzedmiot(przedmiot);
            godzinaLekcyjnaDAO.update(godzinaLekcyjna);

        }

    }

    @Override
    public void dodajZdarzenieDoGodzinyLekcyjnej(int idGodzinyLekcyjnej, String zdarzenie) {

        GodzinaLekcyjna godzinaLekcyjna = godzinaLekcyjnaDAO.getGodzinaLekcyjnaById(idGodzinyLekcyjnej);
        godzinaLekcyjna.setZdarzenie(zdarzenie);
        godzinaLekcyjnaDAO.update(godzinaLekcyjna);
    }

    @Override
    public void usunGodzineZPlanuByIdPrzedmiotu(int idPrzedmiotu) {
        ArrayList<GodzinaLekcyjna> listaGodzinLekcyjnych = (ArrayList<GodzinaLekcyjna>) godzinaLekcyjnaDAO.getListOfGodzinaLekcyjnaByPrzedmiotId(idPrzedmiotu);

        for(GodzinaLekcyjna godzinaLekcyjna : listaGodzinLekcyjnych) {
            int id = godzinaLekcyjna.getId();
            godzinaLekcyjnaDAO.deleteGodzinaLekcyjnaById(id);
        }
    }

    @Override
    public void dodajZastepstwoDoGodzinyLekcyjnej(int idGodzinyLekcyjnej, int idNauczycielaZastepujacego) {
        GodzinaLekcyjna godzinaLekcyjna = godzinaLekcyjnaDAO.getGodzinaLekcyjnaById(idGodzinyLekcyjnej);
        Nauczyciel nauczyciel = (Nauczyciel) osobaDAO.findOsobaById(idNauczycielaZastepujacego);
        godzinaLekcyjna.setNauczycielZastepujacy(nauczyciel);
        godzinaLekcyjnaDAO.update(godzinaLekcyjna);
    }

    @Override
    public void dodajZastepujacaSale(int idGodzinyLekcyjnej, int idSali) {
        GodzinaLekcyjna godzinaLekcyjna = godzinaLekcyjnaDAO.getGodzinaLekcyjnaById(idGodzinyLekcyjnej);
        Miejsce miejsce = miejsceDAO.findMiejsceById(idSali);
        godzinaLekcyjna.setMiejsceZastepujace(miejsce);
        godzinaLekcyjnaDAO.update(godzinaLekcyjna);
    }
}
