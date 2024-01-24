package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Ocena;

import java.util.List;

public interface OcenaDAO {
    void save(Ocena ocena);
    void update(Ocena ocena);
    Ocena findOcenaById(int id);
    List<Ocena> findOcenyByIdUcznia(int idUcznia, int idPrzedmiotu);
    List<Ocena> findOcenyByNazwaKlasy(String nazwaKlasy);
    List<Ocena> findOcenyByNazwaKlasyAndIdNauczyciela(String nazwaKlasy, int idNauczyciela);
    void deleteOcenaById(int id);
}
