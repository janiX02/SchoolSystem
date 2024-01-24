package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.dao.WiadomoscDAO;
import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Uczen;
import com.janikcrew.szkola.entity.Wiadomosc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WiadomoscServiceImpl implements WiadomoscService {
    private OsobaDAO osobaDAO;
    private WiadomoscDAO wiadomoscDAO;

    @Autowired
    public WiadomoscServiceImpl(OsobaDAO osobaDAO, WiadomoscDAO wiadomoscDAO) {
        this.osobaDAO = osobaDAO;
        this. wiadomoscDAO = wiadomoscDAO;
    }

    @Override
    public List<Wiadomosc> wiadomosciOdebrane(int idOdbiorcy) {
        return wiadomoscDAO.findWiadomosciOdebraneById(idOdbiorcy);
    }

    @Override
    public List<Wiadomosc> wiadomosciWyslane(int idNadawcy) {
        return wiadomoscDAO.findWiadomosciWyslaneById(idNadawcy);
    }

    @Override
    public Wiadomosc findMessageById(int id) {
        return wiadomoscDAO.findWiadomoscById(id);
    }

    @Override
    public void updateMessage(Wiadomosc wiadomosc) {
        wiadomoscDAO.update(wiadomosc);
    }

    @Override
    public void utworzWiadomosc(Wiadomosc wiadomosc, Osoba nadawca, Osoba odbiorca) {
        wiadomoscDAO.save(wiadomosc);
        wiadomosc.setNadawca(nadawca);
        wiadomosc.setOdbiorca(odbiorca);
        wiadomoscDAO.update(wiadomosc);
    }

    @Override
    public void utworzWiadomosc(String tytul, String tresc, int idNadawcy, int idOdbiorcy) {
        Wiadomosc wiadomosc = new Wiadomosc();
        wiadomosc.setTytul(tytul);
        wiadomosc.setTresc(tresc);
        Osoba nadawca = osobaDAO.findOsobaById(idNadawcy);
        Osoba odbiorca = osobaDAO.findOsobaById(idOdbiorcy);
        wiadomosc.setOdbiorca(odbiorca);
        wiadomosc.setNadawca(nadawca);
        wiadomoscDAO.save(wiadomosc);
    }

    @Override
    public void utworzWiadomosc(Wiadomosc wiadomosc) {
        wiadomoscDAO.save(wiadomosc);
    }

    @Override
    public void usunWiadomosc(int id) {
        wiadomoscDAO.deleteWiadomoscById(id);
    }

    @Override
    public void usunWiadomosc(Wiadomosc wiadomosc) {
        wiadomoscDAO.deleteMessage(wiadomosc);
    }
}
