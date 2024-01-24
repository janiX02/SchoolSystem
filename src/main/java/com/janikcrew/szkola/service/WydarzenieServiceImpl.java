package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.dao.WydarzenieDAO;
import com.janikcrew.szkola.dao.WydarzenieDAOImpl;
import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class WydarzenieServiceImpl implements WydarzenieService {

    private final WydarzenieDAO wydarzenieDAO;
    private final OsobaDAO osobaDAO;

    @Autowired
    public WydarzenieServiceImpl(WydarzenieDAO wydarzenieDAO, OsobaDAO osobaDAO) {
        this.wydarzenieDAO = wydarzenieDAO;
        this.osobaDAO = osobaDAO;
    }

    @Override
    public Wydarzenie findEventById(int id) {
        return wydarzenieDAO.getEventById(id);
    }

    @Override
    public void addEvent(String title, String content, Osoba autor) {
        Wydarzenie wydarzenie = new Wydarzenie(title, content);
        wydarzenieDAO.save(wydarzenie);
        wydarzenie.setAutor(autor);
        wydarzenieDAO.update(wydarzenie);
    }

    @Override
    public void updateEvent(Wydarzenie wydarzenie) {
        wydarzenieDAO.update(wydarzenie);

    }

    @Override
    public void deleteEventById(int id) {
        Wydarzenie wydarzenie = wydarzenieDAO.getEventById(id);
        wydarzenieDAO.delete(wydarzenie);
    }

    @Override
    public List<Wydarzenie> getAllEvents() {
        return wydarzenieDAO.findAllEvents();
    }
}
