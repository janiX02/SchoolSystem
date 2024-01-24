package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.DyzurDAO;
import com.janikcrew.szkola.dao.KalendarzDAO;
import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class DyzurServiceImpl implements DyzurService {

    private final DyzurDAO dyzurDAO;

    private final OsobaDAO osobaDAO;

    private final KalendarzDAO kalendarzDAO;

    @Autowired
    public DyzurServiceImpl(DyzurDAO dyzurDAO, OsobaDAO osobaDAO, KalendarzDAO kalendarzDAO) {
        this.dyzurDAO = dyzurDAO;
        this.osobaDAO = osobaDAO;
        this.kalendarzDAO = kalendarzDAO;
    }
    @Override
    public Dyzur getDyzurById(int id) {
        return dyzurDAO.getDyzurById(id);
    }

    @Override
    public void dodajDyzurDoPlanuDyzurow(Nauczyciel nauczyciel, Miejsce miejsce, String nazwaDnia, String godzinaRozpoczecia, String dateRozpoczecia) {

        DateTimeFormatter formatterGodz = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalTime godzRozpoczecia = LocalTime.parse(godzinaRozpoczecia, formatterGodz);
        LocalTime godzZakonczenia = godzRozpoczecia.plusMinutes(10);
        if (godzRozpoczecia.equals(LocalTime.parse("12:25:00", formatterGodz))) {
            godzZakonczenia = godzRozpoczecia.plusMinutes(20);
        }

        LocalDate dataRozpoczecia = LocalDate.parse(dateRozpoczecia, formatterData);

        ArrayList<Dzien> listaDat = (ArrayList<Dzien>) kalendarzDAO.getKalendarzByDzien(nazwaDnia);


        while (dataRozpoczecia.isAfter(listaDat.get(0).getData())) {
            listaDat.remove(0);
        }

        for (Dzien dzienKalendarzowy : listaDat) {

            System.out.println(dzienKalendarzowy.getData() + ", " + dzienKalendarzowy.getNazwaDnia());
            Dyzur dyzur = new Dyzur();
            dyzurDAO.save(dyzur);
            dyzur.setDzien(dzienKalendarzowy);
            dyzur.setNauczyciel(nauczyciel);
            dyzur.setGodzinaRozpoczecia(godzRozpoczecia);
            dyzur.setGodzinazZakonczenia(godzZakonczenia);
            dyzur.setMiejsce(miejsce);
            dyzurDAO.update(dyzur);

        }
    }

    @Override
    public void dodajZastepstwoDoDyzuru(int idDyzuru, int idNauczycielaZastepujacego) {
        Dyzur dyzur = dyzurDAO.getDyzurById(idDyzuru);
        Nauczyciel nauczycielZastepujacy = (Nauczyciel) osobaDAO.findOsobaById(idNauczycielaZastepujacego);
        dyzur.setNauczycielZastepujacy(nauczycielZastepujacy);
        dyzurDAO.update(dyzur);
    }

    @Override
    public void usunWszystkieDyzury() {
        ArrayList<Dyzur> listaDyzurow = (ArrayList<Dyzur>) dyzurDAO.getAll();

        for (Dyzur dyzur : listaDyzurow) {
            dyzurDAO.deleteDyzurById(dyzur.getId());
        }
    }
}
