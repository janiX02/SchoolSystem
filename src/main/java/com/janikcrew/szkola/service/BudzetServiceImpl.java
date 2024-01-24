package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.BudzetDAO;
import com.janikcrew.szkola.dao.BudzetDAOImpl;
import com.janikcrew.szkola.entity.Budzet;
import com.janikcrew.szkola.entity.Transakcja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudzetServiceImpl implements BudzetService {
    private final BudzetDAO budzetDAO;

    @Autowired
    public BudzetServiceImpl(BudzetDAO budzetDAO) {
        this.budzetDAO = budzetDAO;
    }

    @Override
    public void dodajBudzet(Budzet budzet) {
        budzetDAO.save(budzet);
    }

    @Override
    public void dodajTransakcjeDoBudzetu(Budzet budzet, Transakcja transakcja) throws Exception {

        budzetDAO.save(transakcja);
        transakcja.setBudzet(budzet);

        double newBudzet;

        if (transakcja.getRodzaj().equals("WYDATEK")) {
            if (transakcja.getKwota() <= budzet.getBudzet()) {
                newBudzet = budzet.getBudzet() - transakcja.getKwota();
                System.out.println("Transakcja typu WYDATEK w wysokości: " + transakcja.getKwota());
            }
            else {
                throw new Exception("Brak odpowiednich środków na koncie! ");
            }
        }
        else {
            newBudzet = budzet.getBudzet() + transakcja.getKwota();
            System.out.println("Transakcja typu PRZYCHÓD w wysokości: " + transakcja.getKwota());
        }
        budzet.setBudzet(newBudzet);
        budzetDAO.update(transakcja);
        budzetDAO.update(budzet);
    }

    @Override
    public void znajdzListeTransakcjiBudzetu(Budzet budzet) {
        int id = budzet.getId();
        List<Transakcja> listaTransakcji = budzetDAO.findTransactionsByBudzetId(id);
        budzet.setListaTransakcji(listaTransakcji);
    }

    @Override
    public Budzet findBudzetById(int id) {
        return budzetDAO.findBudzetById(id);
    }
}
