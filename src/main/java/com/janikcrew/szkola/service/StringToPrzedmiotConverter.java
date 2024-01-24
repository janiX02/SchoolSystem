package com.janikcrew.szkola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.janikcrew.szkola.entity.Przedmiot;
import org.springframework.stereotype.Component;

@Component
public class StringToPrzedmiotConverter implements Converter<String, Przedmiot> {

    private final PrzedmiotService przedmiotService;

    @Autowired
    public StringToPrzedmiotConverter(PrzedmiotService przedmiotService) {
        this.przedmiotService = przedmiotService;
    }

    @Override
    public Przedmiot convert(String source) {
        Integer przedmiotId = Integer.valueOf(source);
        return przedmiotService.findPrzedmiotById(przedmiotId);
    }
}
