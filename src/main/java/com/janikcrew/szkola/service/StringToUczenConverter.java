package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Uczen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUczenConverter implements Converter<String, Uczen> {

    private final OsobaService uczenService;

    @Autowired
    public StringToUczenConverter(OsobaService uczenService) {
        this.uczenService = uczenService;
    }

    @Override
    public Uczen convert(String source) {
        Integer uczenId = Integer.valueOf(source);
        return (Uczen) uczenService.findOsobaById(uczenId);
    }
}