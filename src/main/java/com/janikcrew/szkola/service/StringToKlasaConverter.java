package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Klasa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToKlasaConverter implements Converter<String, Klasa> {
    private final KlasaService klasaService;

    @Autowired
    public StringToKlasaConverter(KlasaService klasaService) {
        this.klasaService = klasaService;
    }


    @Override
    public Klasa convert(String source) {
        String name = String.valueOf(source);
        return klasaService.findKlasaByName(name);
    }
}
