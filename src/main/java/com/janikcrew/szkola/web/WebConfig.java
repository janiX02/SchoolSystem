package com.janikcrew.szkola.web;

import com.janikcrew.szkola.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToUczenConverter stringToUczenConverter;
    private final StringToPrzedmiotConverter stringToPrzedmiotConverter;
    private final StringToKlasaConverter stringToKlasaConverter;
    private final StringToNauczycielConverter stringToNauczycielConverter;
    private final StringToOsobaConverter stringToOsobaConverter;
    private final StringToRodzicConverter stringToRodzicConverter;

    @Autowired
    public WebConfig(StringToUczenConverter stringToUczenConverter, StringToPrzedmiotConverter stringToPrzedmiotConverter,
                     StringToKlasaConverter stringToKlasaConverter, StringToNauczycielConverter stringToNauczycielConverter,
                     StringToOsobaConverter stringToOsobaConverter, StringToRodzicConverter stringToRodzicConverter) {
        this.stringToUczenConverter = stringToUczenConverter;
        this.stringToPrzedmiotConverter = stringToPrzedmiotConverter;
        this.stringToKlasaConverter = stringToKlasaConverter;
        this.stringToNauczycielConverter = stringToNauczycielConverter;
        this.stringToOsobaConverter = stringToOsobaConverter;
        this.stringToRodzicConverter = stringToRodzicConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUczenConverter);
        registry.addConverter(stringToPrzedmiotConverter);
        registry.addConverter(stringToKlasaConverter);
        registry.addConverter(stringToNauczycielConverter);
        registry.addConverter(stringToOsobaConverter);
        registry.addConverter(stringToRodzicConverter);
    }
}