package com.janikcrew.szkola.service;

import com.janikcrew.szkola.entity.Rodzic;
import com.janikcrew.szkola.entity.Uczen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRodzicConverter implements Converter<String, Rodzic> {

    private final OsobaService rodzicService;

    @Autowired
    public StringToRodzicConverter(OsobaService rodzicService) {
        this.rodzicService = rodzicService;
    }

    @Override
    public Rodzic convert(String source) {
        Integer rodzicId = Integer.valueOf(source);
        return (Rodzic) rodzicService.findOsobaById(rodzicId);
    }
}