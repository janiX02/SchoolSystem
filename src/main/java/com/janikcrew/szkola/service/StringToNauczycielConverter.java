package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Uczen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToNauczycielConverter implements Converter<String, Nauczyciel> {

    private final OsobaService uczenService;

    @Autowired
    public StringToNauczycielConverter(OsobaService uczenService) {
        this.uczenService = uczenService;
    }

    @Override
    public Nauczyciel convert(String source) {
        Integer id = Integer.valueOf(source);
        return (Nauczyciel) uczenService.findOsobaById(id);
    }
}