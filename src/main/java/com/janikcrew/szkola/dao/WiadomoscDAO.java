package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Przedmiot;
import com.janikcrew.szkola.entity.Wiadomosc;

import java.util.List;

public interface WiadomoscDAO {
    void save(Wiadomosc wiadomosc);
    void update(Wiadomosc wiadomosc);
    void deleteWiadomoscById(int id);
    void deleteMessage(Wiadomosc wiadomosc);
    Wiadomosc findWiadomoscById(int id);
    List<Wiadomosc> findWiadomosciOdebraneById(int id);
    List<Wiadomosc> findWiadomosciWyslaneById(int id);
}
