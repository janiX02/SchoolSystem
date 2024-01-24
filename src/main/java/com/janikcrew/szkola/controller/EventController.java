package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Wydarzenie;
import com.janikcrew.szkola.service.OsobaService;
import com.janikcrew.szkola.service.WydarzenieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventController {
    private final WydarzenieService wydarzenieService;

    private final OsobaService osobaService;

    @Autowired
    public EventController(WydarzenieService wydarzenieService, OsobaService osobaService) {
        this.wydarzenieService = wydarzenieService;
        this.osobaService = osobaService;
    }
    @GetMapping("/addEvent")
    public String addEvent(Model model) {
        Wydarzenie wydarzenie = new Wydarzenie();
        wydarzenie.setCzyNoweWydarzenie(true);
        model.addAttribute("event", wydarzenie);
        return "add_event";
    }
    @GetMapping("/updateEvent")
    public String updateEvent(@RequestParam("eventId") int id, Model model) {
        Wydarzenie wydarzenie = wydarzenieService.findEventById(id);
        wydarzenie.setCzyNoweWydarzenie(false);
        wydarzenie.setCzyUzytkownikToAutor(true);
        model.addAttribute("event", wydarzenie);
        return "add_event";
    }
    @GetMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("eventId") int id) {
        wydarzenieService.deleteEventById(id);
        return "redirect:/home";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute("event") Wydarzenie wydarzenie, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (wydarzenie.getTytul().equals("")){
            wydarzenie.setCzyPustyTytul(true);
            if (wydarzenie.getTresc().equals(""))
                wydarzenie.setCzyPustaTresc(true);
            model.addAttribute("event", wydarzenie);

            return "add_event";
        }

        if (wydarzenie.getTresc().equals("")) {
            wydarzenie.setCzyPustaTresc(true);
            if (wydarzenie.getTytul().equals(""))
                wydarzenie.setCzyPustyTytul(true);
            model.addAttribute("event", wydarzenie);

            return "add_event";
        }
        wydarzenie.setCzyPustyTytul(false);
        wydarzenie.setCzyPustaTresc(false);
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba autor = osobaService.findOsobaByEmail(email);
            if (autor != null) {
                if (wydarzenie.isCzyNoweWydarzenie()) {
                    wydarzenieService.addEvent(wydarzenie.getTytul(), wydarzenie.getTresc(), autor);
                }
                else {
                    wydarzenie.setAutor(autor);
                    wydarzenieService.updateEvent(wydarzenie);
                }
            }
        }
        return "redirect:/home";
    }
}


