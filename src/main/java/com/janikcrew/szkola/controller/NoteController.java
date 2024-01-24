package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.entity.*;
import com.janikcrew.szkola.service.KlasaService;
import com.janikcrew.szkola.service.OsobaService;
import com.janikcrew.szkola.service.UwagaService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class NoteController {

    private final UwagaService uwagaService;
    private final OsobaService osobaService;
    private final KlasaService klasaService;

    @Autowired
    public NoteController(UwagaService uwagaService, OsobaService osobaService, KlasaService klasaService) {
        this.uwagaService = uwagaService;
        this.osobaService = osobaService;
        this.klasaService = klasaService;
    }

    @GetMapping("/addNote")
    public String addNote(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            List<Klasa> listaKlas = klasaService.findAllClasses();
            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            Uwaga uwaga = new Uwaga();
            if (uzytkownik != null) {
                uwaga.setCzyWybranaKlasa(false);
                uwaga.setCzyWybranyUczen(false);

                model.addAttribute("note", uwaga);
                model.addAttribute("classes", listaKlas);
            }
        }
        return "add_note";
    }
    @PostMapping("/addNote2")
    public String addNote2(@ModelAttribute("note")Uwaga uwaga, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Osoba uzytkownik = osobaService.findOsobaByEmail(email);

        List<Klasa> listaKlas = klasaService.findAllClasses();
        uwaga.setCzyWybranaKlasa(true);
        uwaga.setCzyNowaUwaga(true);
        uwaga.setWystawiajacy(uzytkownik);
        Klasa klasa0 = listaKlas.get(0);
        Klasa klasaWybrana = null;
        int indexWybranejKlasy = 0;

        for(Klasa klasa : listaKlas) {
            if (klasa.getNazwa().equals(uwaga.getNazwaKlasy())) {
                klasaWybrana = klasa;
                indexWybranejKlasy = listaKlas.indexOf(klasa);
                break;
            }
        }

        listaKlas.set(0, klasaWybrana);
        listaKlas.set(indexWybranejKlasy, klasa0);
        List<Uczen> listaUczniow = klasaWybrana.getListaUczniow();
        Collections.sort(listaUczniow);
        uwaga.setKlasaWybrana(klasaWybrana);
        model.addAttribute("classes", listaKlas);
        model.addAttribute("chosenClass", klasaWybrana);
        model.addAttribute("students", listaUczniow);
        model.addAttribute("note", uwaga);

        return "add_note";
    }
    @PostMapping("/saveNote")
    public String saveNote(@ModelAttribute("note") Uwaga uwaga, Model model) {

        if (uwaga.getRodzaj().equals("null") || uwaga.getTytul().equals("null") || uwaga.getTresc().equals("")) {
            uwaga.setCzyPustaTresc(true);
            return "redirect:/addNote";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Osoba uzytkownik = osobaService.findOsobaByEmail(email);
        uwaga.setWystawiajacy(uzytkownik);
        uwagaService.utworzUwage(uwaga);

        return "redirect:/showInsertedNotes";
    }
    @PostMapping("/saveUpdatedNote")
    public String saveUpdatedNote(@ModelAttribute("note") Uwaga uwaga) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Osoba uzytkownik = osobaService.findOsobaByEmail(email);

        uwaga.setWystawiajacy(uzytkownik);
        uwagaService.update(uwaga);
        return "redirect:/showInsertedNotes";
    }

    @GetMapping("/showInsertedNotes")
    public String showInsertedNotes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            if (uzytkownik != null) {
                List<Uwaga> listaWystawionychUwag = null;
                if (uzytkownik instanceof Nauczyciel) {
                    listaWystawionychUwag = uwagaService.uwagiWystawione(uzytkownik);
                    if (!listaWystawionychUwag.isEmpty()) {
                        ((Nauczyciel) uzytkownik).setCzyPosiadaUwagi(true);
                    }
                    else {
                        ((Nauczyciel) uzytkownik).setCzyPosiadaUwagi(false);
                    }
                }
                else if (uzytkownik instanceof Admin) {
                    listaWystawionychUwag = uwagaService.uwagiWystawione(uzytkownik);
                    if (!listaWystawionychUwag.isEmpty()) {
                        ((Admin) uzytkownik).setCzyPosiadaUwagi(true);
                    }
                    else {
                        ((Admin) uzytkownik).setCzyPosiadaUwagi(false);
                    }
                }
                model.addAttribute("notes", listaWystawionychUwag);
                model.addAttribute("user", uzytkownik);
            }
        }
        return "show_inserted_notes";
    }

    @GetMapping("/updateNote")
    public String updateNote(@RequestParam("noteId")int id, Model model) {
        Uwaga uwaga = uwagaService.findNoteById(id);
        String otrzymujacyImie = uwaga.getOtrzymujacy().toString();
        model.addAttribute("note", uwaga);
        model.addAttribute("studentName", otrzymujacyImie);
        return "update_note";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam("noteId")int id) {
        uwagaService.usunUwageById(id);
        return "redirect:/showInsertedNotes";
    }

    @GetMapping("/showNotes")
    public String showNotes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            if (uzytkownik != null) {
                if (uzytkownik instanceof Uczen) {
                    List<Uwaga> listaUwag = uwagaService.uwagiOtrzymane((Uczen) uzytkownik);
                    if (!listaUwag.isEmpty()) {
                        ((Uczen) uzytkownik).setCzyPosiadaUwagi(true);
                    }
                    else {
                        ((Uczen) uzytkownik).setCzyPosiadaUwagi(false);
                    }
                    ((Uczen) uzytkownik).setUwagiWystawione(listaUwag);
                    model.addAttribute("notes", listaUwag);
                    model.addAttribute("student", uzytkownik);
                }
                else if (uzytkownik instanceof Rodzic) {
                     Uczen dziecko = ((Rodzic) uzytkownik).getDziecko();
                     List<Uwaga> listaUwag = uwagaService.uwagiOtrzymane(dziecko);
                     if (!listaUwag.isEmpty()) {
                         dziecko.setCzyPosiadaUwagi(true);
                     }
                     else {
                         dziecko.setCzyPosiadaUwagi(false);
                     }
                     dziecko.setUwagiWystawione(listaUwag);
                     model.addAttribute("notes", listaUwag);
                     model.addAttribute("student", dziecko);
                }
            }
        }
        return "show_notes";
    }
}
