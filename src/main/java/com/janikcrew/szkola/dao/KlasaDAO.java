package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.*;

import java.util.List;

public interface KlasaDAO {
    Klasa findKlasaByNazwa(String nazwa);
    List<Klasa> findAllClasses();
    void save(Klasa klasa);
    void update(Klasa klasa);
    void deleteKlasaByName(String nazwa);
}
