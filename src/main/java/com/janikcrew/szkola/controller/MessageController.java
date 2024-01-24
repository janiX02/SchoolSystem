package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.entity.*;
import com.janikcrew.szkola.service.KlasaService;
import com.janikcrew.szkola.service.OsobaService;
import com.janikcrew.szkola.service.WiadomoscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {
    private final WiadomoscService wiadomoscService;
    private final OsobaService osobaService;
    private final KlasaService klasaService;

    @Autowired
    public MessageController(WiadomoscService wiadomoscService, OsobaService osobaService, KlasaService klasaService) {
        this.wiadomoscService = wiadomoscService;
        this.osobaService = osobaService;
        this.klasaService = klasaService;
    }

    @GetMapping("/showReceivedMessages")
    public String showReceivedMessages(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);

            List<Wiadomosc> wiadomosciOdebrane = wiadomoscService.wiadomosciOdebrane(uzytkownik.getId());

            uzytkownik.setCzyPosiadaWiadomosci(!wiadomosciOdebrane.isEmpty());
            uzytkownik.setCzyUzytkownikToNadawca(false);

            for (Wiadomosc wiadomosc : wiadomosciOdebrane) {
                wiadomosc.setCzyUzytkownikNadawca(false);
            }
            model.addAttribute("user", uzytkownik);
            model.addAttribute("messages", wiadomosciOdebrane);
        }
        return "show_messages";
    }

    @GetMapping("/showSentMessages")
    public String showSentMessages(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);

            List<Wiadomosc> wiadomosciWyslane = wiadomoscService.wiadomosciWyslane(uzytkownik.getId());

            uzytkownik.setCzyPosiadaWiadomosci(!wiadomosciWyslane.isEmpty());
            uzytkownik.setCzyUzytkownikToNadawca(true);

            for (Wiadomosc wiadomosc : wiadomosciWyslane) {
                wiadomosc.setCzyUzytkownikNadawca(false);
            }
            model.addAttribute("user", uzytkownik);
            model.addAttribute("messages", wiadomosciWyslane);
        }

        return "show_messages";
    }

    @GetMapping("/addMessage")
    public String addMessage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            Wiadomosc wiadomosc = new Wiadomosc();


            if (uzytkownik instanceof Uczen || uzytkownik instanceof Rodzic) {
                List<Nauczyciel> listaNauczycieli = osobaService.findAllTeachers();
                wiadomosc.setCzyWybranyTypOdbiorcy(true);
                wiadomosc.setCzyWybranyNauczyciel(false);
                wiadomosc.setTypWiadomosci("nauczyciel");

                model.addAttribute("teachers", listaNauczycieli);
            }
            else {
                wiadomosc.setCzyWybranyTypOdbiorcy(false);
            }
            model.addAttribute("message", wiadomosc);

        }

        return "add_message";
    }
    @PostMapping("/addMessage2")
    public String addMessage2(@ModelAttribute("message")Wiadomosc wiadomosc, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            List<Klasa> listaKlas = klasaService.findAllClasses();

            if (wiadomosc.getTypWiadomosci().equals("nauczyciel")) {
                wiadomosc.setCzyWybranyNauczyciel(true);
                List<Nauczyciel> listaNauczycieli = osobaService.findAllTeachers();

                if (uzytkownik instanceof Nauczyciel) {
                    listaNauczycieli.remove(uzytkownik);
                }
                else if (uzytkownik instanceof Admin) {
                    listaNauczycieli.remove(osobaService.findOsobaById(13));
                }

                model.addAttribute("teachers", listaNauczycieli);
            }
            if (uzytkownik instanceof Nauczyciel) {
                ((Nauczyciel) uzytkownik).setCzyWybranaKlasa(false);
                wiadomosc.setCzyWybranyTypOdbiorcy(true);
            }
            else if (uzytkownik instanceof Admin) {
                ((Admin) uzytkownik).setCzyWybranaKlasa(false);
                wiadomosc.setCzyWybranyTypOdbiorcy(true);

            }
            model.addAttribute("user", uzytkownik);
            model.addAttribute("classes", listaKlas);
            model.addAttribute("message", wiadomosc);

        }

        return "add_message";
    }

    @PostMapping("/addMessage3")
    public String addMessage3(@ModelAttribute("message")Wiadomosc wiadomosc, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            Osoba uzytkownik = osobaService.findOsobaByEmail(email);

            Klasa wybranaKlasa = wiadomosc.getWybranaKlasa();
            List<Klasa> listaKlas = klasaService.findAllClasses();
            List<Uczen> listaUczniow = osobaService.findStudentsByClassName(wybranaKlasa.getNazwa());

            if (wiadomosc.getTypWiadomosci().equals("rodzic")) {
                List<Rodzic> listaRodzicow = new ArrayList<>();
                int idUcznia;
                Uczen tempUczen;
                for (Uczen uczen : listaUczniow) {
                    idUcznia = uczen.getId();
                    tempUczen = (Uczen) osobaService.findOsobaById(idUcznia);
                    Rodzic rodzic = tempUczen.getRodzic();
                    listaRodzicow.add(rodzic);
                }
                model.addAttribute("parents", listaRodzicow);
            }


            listaKlas = GradeController.sortChosenClassList(listaKlas, wybranaKlasa);


            if (uzytkownik instanceof Nauczyciel) {
                ((Nauczyciel) uzytkownik).setCzyWybranaKlasa(true);
                ((Nauczyciel) uzytkownik).setCzyWybranyUczen(false);
                wiadomosc.setCzyWybranaKlasa(true);
                wiadomosc.setCzyWybranyUczen(false);
            }
            else if (uzytkownik instanceof Admin) {
                ((Admin) uzytkownik).setCzyWybranaKlasa(true);
                ((Admin) uzytkownik).setCzyWybranyUczen(false);
                wiadomosc.setCzyWybranaKlasa(true);
                wiadomosc.setCzyWybranyUczen(false);
            }
            wiadomosc.setCzyWybranyTypOdbiorcy(true);

            model.addAttribute("students", listaUczniow);
            model.addAttribute("classes", listaKlas);
            model.addAttribute("user", uzytkownik);
            model.addAttribute("message", wiadomosc);
        }

        return "add_message";
    }
    @PostMapping("/addMessage4")
    public String addMessage4(@ModelAttribute("message")Wiadomosc wiadomosc, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            Osoba uzytkownik = osobaService.findOsobaByEmail(email);

            Klasa wybranaKlasa = wiadomosc.getWybranaKlasa();
            List<Klasa> listaKlas = klasaService.findAllClasses();
            List<Uczen> listaUczniow = osobaService.findStudentsByClassName(wybranaKlasa.getNazwa());


            listaKlas = GradeController.sortChosenClassList(listaKlas, wybranaKlasa);
            wiadomosc.setIdOdbiorcy(wiadomosc.getOdbiorca().getId());


            if (wiadomosc.getTypWiadomosci().equals("rodzic")) {
                List<Rodzic> listaRodzicow = new ArrayList<>();
                wiadomosc.setWybranyRodzic((Rodzic) wiadomosc.getOdbiorca());
                int idUcznia;
                Uczen tempUczen;
                for (Uczen uczen : listaUczniow) {
                    idUcznia = uczen.getId();
                    tempUczen = (Uczen) osobaService.findOsobaById(idUcznia);
                    Rodzic rodzic = tempUczen.getRodzic();
                    listaRodzicow.add(rodzic);
                }
                GradeController.sortChosenParentList(listaRodzicow, wiadomosc.getWybranyRodzic());
                model.addAttribute("parents", listaRodzicow);
            }
            else if (wiadomosc.getTypWiadomosci().equals("uczen")) {
                listaUczniow = GradeController.sortChosenStudentList(listaUczniow, (Uczen) wiadomosc.getOdbiorca());
            }
            else {
                List<Nauczyciel> listaNauczycieli = osobaService.findAllTeachers();
                listaNauczycieli = GradeController.sortChosenTeachersList(listaNauczycieli, (Nauczyciel) wiadomosc.getOdbiorca());
                wiadomosc.setIdOdbiorcy(wiadomosc.getOdbiorca().getId());
                wiadomosc.setCzyWybranyNauczyciel(true);
                wiadomosc.setCzyWybranyTypOdbiorcy(true);

                if (uzytkownik instanceof Nauczyciel) {
                    listaNauczycieli.remove(uzytkownik);
                }
                else if (uzytkownik instanceof Admin) {
                    listaNauczycieli.remove(osobaService.findOsobaById(13));
                }
                model.addAttribute("teachers", listaNauczycieli);
            }
            wiadomosc.setOdbiorca(null);

            if (uzytkownik instanceof Nauczyciel && (wiadomosc.getTypWiadomosci().equals("uczen") || wiadomosc.getTypWiadomosci().equals("rodzic"))) {
                ((Nauczyciel) uzytkownik).setCzyWybranaKlasa(true);
                ((Nauczyciel) uzytkownik).setCzyWybranyUczen(true);
                wiadomosc.setCzyWybranaKlasa(true);
                wiadomosc.setCzyWybranyUczen(true);

            }
            else if (uzytkownik instanceof Admin && (wiadomosc.getTypWiadomosci().equals("uczen") || wiadomosc.getTypWiadomosci().equals("rodzic"))) {
                ((Admin) uzytkownik).setCzyWybranaKlasa(true);
                ((Admin) uzytkownik).setCzyWybranyUczen(true);
                wiadomosc.setCzyWybranaKlasa(true);
                wiadomosc.setCzyWybranyUczen(true);

            }
            wiadomosc.setCzyWybranyTypOdbiorcy(true);
            model.addAttribute("students", listaUczniow);
            model.addAttribute("classes", listaKlas);
            model.addAttribute("user", uzytkownik);
            model.addAttribute("message", wiadomosc);

        }

        return "add_message";
    }

    @PostMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("message")Wiadomosc wiadomosc) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Osoba uzytkownik = osobaService.findOsobaByEmail(email);
        if (wiadomosc.getTytul().equals("") || wiadomosc.getTresc().equals(""))
            return "redirect:/addMessage";
        if (wiadomosc.getIdOdbiorcy() == 13)
            wiadomosc.setIdOdbiorcy(1);
        wiadomoscService.utworzWiadomosc(wiadomosc.getTytul(), wiadomosc.getTresc(), uzytkownik.getId(), wiadomosc.getIdOdbiorcy());

        return "redirect:/showSentMessages";
    }

    @GetMapping("/updateMessage")
    public String updateMessage(@RequestParam("messageId")int id, Model model) {
        Wiadomosc wiadomosc = wiadomoscService.findMessageById(id);
        wiadomosc.setIdNadawcy(wiadomosc.getNadawca().getId());
        wiadomosc.setIdOdbiorcy(wiadomosc.getOdbiorca().getId());
        wiadomosc.setNazwiskoOdbiorcy(wiadomosc.getOdbiorca().getImie() + " " + wiadomosc.getOdbiorca().getNazwisko());

        model.addAttribute("message", wiadomosc);
        return "update_message";
    }

    @PostMapping("/saveUpdatedMessage")
    public String saveUpdatedMessage(@ModelAttribute("message")Wiadomosc wiadomosc) {
        wiadomosc.setOdbiorca(osobaService.findOsobaById(wiadomosc.getIdOdbiorcy()));
        wiadomosc.setNadawca(osobaService.findOsobaById(wiadomosc.getIdNadawcy()));
        wiadomoscService.updateMessage(wiadomosc);
        return "redirect:/showSentMessages";
    }
    @GetMapping("/deleteMessage")
    public String deleteMessage(@RequestParam("messageId")int id) {
        Wiadomosc wiadomosc = wiadomoscService.findMessageById(id);
        wiadomosc.setOdbiorca(null);
        wiadomosc.setNadawca(null);
        wiadomoscService.updateMessage(wiadomosc);
        wiadomoscService.usunWiadomosc(id);
        return "redirect:/showSentMessages";
    }
}
