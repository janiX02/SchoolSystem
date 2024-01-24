package com.janikcrew.szkola.service;

import com.janikcrew.szkola.dao.KalendarzDAO;
import com.janikcrew.szkola.entity.Dzien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {
    private KalendarzDAO kalendarzDAO;

    @Autowired
    public AdminServiceImpl(KalendarzDAO kalendarzDAO) {
        this.kalendarzDAO = kalendarzDAO;
    }

    @Override
    public void utworzKalendarzNaRokSzkolny() {
        Year year = Year.of(LocalDate.now().getYear());
        Month month = Month.SEPTEMBER;
        int monthValue = month.getValue();
        MonthDay monthDay = MonthDay.of(month.getValue(), 1);
        LocalDate data = year.atMonthDay(monthDay);

        while (monthValue < 7 || monthValue > 8) {
            Dzien dzien = new Dzien(data);
            kalendarzDAO.save(dzien);
            data = data.plusDays(1);
            monthValue = data.getMonthValue();
        }
    }

    @Override
    public void wyczyscKalendarz() {
        ArrayList<Dzien> kalendarz = (ArrayList<Dzien>) kalendarzDAO.getKalendarz();
        for (Dzien dzien : kalendarz) {
            kalendarzDAO.deleteDzienByDate(dzien.getData());
        }
    }

}
