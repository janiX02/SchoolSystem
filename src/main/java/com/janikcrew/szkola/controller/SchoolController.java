package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.dao.KalendarzDAO;
import com.janikcrew.szkola.dto.TeacherDTO;
import com.janikcrew.szkola.entity.*;
import com.janikcrew.szkola.service.KlasaService;
import com.janikcrew.szkola.service.OsobaService;
import com.janikcrew.szkola.service.StringToUczenConverter;
import com.janikcrew.szkola.service.WydarzenieService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SchoolController {

    private final WydarzenieService wydarzenieService;
    private final OsobaService osobaService;

    private final KlasaService klasaService;

    @Autowired
    public SchoolController(WydarzenieService wydarzenieService, OsobaService osobaService, KlasaService klasaService) {
        this.wydarzenieService = wydarzenieService;
        this.osobaService = osobaService;
        this.klasaService = klasaService;
    }
    @GetMapping("/home")
    public String showHome(Model model) {
        List<Wydarzenie> listaWydarzen = wydarzenieService.getAllEvents();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);

            if (uzytkownik.getAccepted() == 0)
                return "not_confirmed";

            for (Wydarzenie wydarzenie : listaWydarzen) {
                if (email.equals(wydarzenie.getAutor().getEmail())) {
                    wydarzenie.setCzyUzytkownikToAutor(true);
                }
            }

        }
        model.addAttribute("events", listaWydarzen);

        return "home_page";
    }
    @GetMapping("/showAcceptanceRequests")
    public String showAcceptanceRequests(Model model) {
        List<Uczen> listaUczniow = osobaService.findNotAcceptedStudents();
        if (listaUczniow.isEmpty())
            return "requests_page_empty";

        model.addAttribute("students", listaUczniow);

        return "requests_page";
    }
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")int id) {
        Uczen uczen = (Uczen) osobaService.findOsobaById(id);
        Rodzic rodzic = uczen.getRodzic();
        uczen.setRodzic(null);
        rodzic.setDziecko(null);
        osobaService.update(uczen);
        osobaService.update(rodzic);
        osobaService.deletePersonById(uczen.getId());
        osobaService.deletePersonById(rodzic.getId());
        return "redirect:/showAcceptanceRequests";
    }
    @GetMapping("confirmStudent")
    public String confirmStudent(@RequestParam("studentId") int id, Model model) {
        List<Klasa> listaKlas = klasaService.findAllClasses();
        Uczen uczen = (Uczen) osobaService.findOsobaById(id);
        Rodzic rodzic = uczen.getRodzic();

        model.addAttribute("student", uczen);
        model.addAttribute("parent", rodzic);
        model.addAttribute("classes", listaKlas);

        return "confirm_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Uczen uczen) {
        Uczen uczenDoDodania = (Uczen) osobaService.findOsobaById(uczen.getId());
        Rodzic rodzic = uczenDoDodania.getRodzic();
        rodzic.setAccepted(1);
        uczenDoDodania.setKlasa(uczen.getKlasa());
        uczenDoDodania.setAccepted(1);
        osobaService.update(uczenDoDodania);
        osobaService.update(rodzic);
        return "redirect:/showAcceptanceRequests";
    }
    @GetMapping("/redirectHome")
    public String redirectHome() {
        return "redirect:/home";
    }

    @GetMapping("/showDetails")
    public String showDetails(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);

            if (uzytkownik instanceof Uczen) {
                model.addAttribute("user", uzytkownik);
            }
            else if (uzytkownik instanceof Rodzic) {
                model.addAttribute("user", uzytkownik);
            }
            else if (uzytkownik instanceof Nauczyciel) {
                model.addAttribute("user", uzytkownik);
            }
            else {
                model.addAttribute("user", uzytkownik);
            }
        }
        return "details";
    }

    @GetMapping("/addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        return "add_teacher";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") TeacherDTO nauczycielDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.setHaslo(BCrypt.hashpw(nauczycielDTO.getHaslo(), BCrypt.gensalt()));
        nauczyciel.setImie(nauczycielDTO.getImie());
        nauczyciel.setNazwisko(nauczycielDTO.getNazwisko());
        nauczyciel.setPesel(nauczycielDTO.getPesel());
        nauczyciel.setEmail(nauczycielDTO.getEmail());
        nauczyciel.setDataUrodzenia(LocalDate.parse(nauczycielDTO.getDataUrodzenia(), formatter));
        nauczyciel.setAccepted(1);
        nauczyciel.setActive(1);

        osobaService.dodajNauczyciela(nauczyciel);
        return "redirect:/home";
    }
}
