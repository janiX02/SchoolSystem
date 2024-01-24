package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Uczen;
import com.janikcrew.szkola.entity.Wiadomosc;

import java.util.List;

public interface WiadomoscService {
    List<Wiadomosc> wiadomosciOdebrane(int idOdbiorcy);
    List<Wiadomosc> wiadomosciWyslane(int idNadawcy);
    Wiadomosc findMessageById(int id);
    void updateMessage(Wiadomosc wiadomosc);
    void utworzWiadomosc(Wiadomosc wiadomosc, Osoba nadawca, Osoba odbiorca);
    void utworzWiadomosc(String tytul, String tresc, int idNadawcy, int idOdbiorcy);
    void utworzWiadomosc(Wiadomosc wiadomosc);
    void usunWiadomosc(int id);
    void usunWiadomosc(Wiadomosc wiadomosc);
}
