package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Wydarzenie;

import java.util.List;

public interface WydarzenieService {
    Wydarzenie findEventById(int id);
    void addEvent(String title, String content, Osoba autor);
    void updateEvent(Wydarzenie wydarzenie);
    void deleteEventById(int id);
    List<Wydarzenie> getAllEvents();
}
