package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Uczen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOsobaConverter implements Converter<String, Osoba> {

    private final OsobaService uczenService;

    @Autowired
    public StringToOsobaConverter(OsobaService uczenService) {
        this.uczenService = uczenService;
    }

    @Override
    public Osoba convert(String source) {
        Integer id = Integer.valueOf(source);
        return uczenService.findOsobaById(id);
    }
}