package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.entity.*;
import com.janikcrew.szkola.service.KlasaService;
import com.janikcrew.szkola.service.OcenaService;
import com.janikcrew.szkola.service.OsobaService;
import com.janikcrew.szkola.service.PrzedmiotService;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class GradeController {
    private final OsobaService osobaService;
    private final OcenaService ocenaService;
    private final PrzedmiotService przedmiotService;
    private final KlasaService klasaService;

    @Autowired
    public GradeController(OcenaService ocenaService, OsobaService osobaService, PrzedmiotService przedmiotService, KlasaService klasaService) {
        this.ocenaService = ocenaService;
        this.osobaService = osobaService;
        this.przedmiotService = przedmiotService;
        this.klasaService = klasaService;
    }
    @GetMapping("/showGrades")
    public String showGrades(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            if (uzytkownik != null) {
                List<Przedmiot> listaPrzedmiotow = null;
                Uczen uczen = null;
                if (uzytkownik instanceof Uczen) {
                    listaPrzedmiotow = przedmiotService.findSubjectsByClassName(((Uczen) uzytkownik).getKlasa().getNazwa());
                    uczen = (Uczen) uzytkownik;
                }
                else if (uzytkownik instanceof Rodzic) {
                    listaPrzedmiotow = przedmiotService.findSubjectsByClassName(((Rodzic) uzytkownik).getDziecko().getKlasa().getNazwa());
                    uczen = ((Rodzic) uzytkownik).getDziecko();
                }
                uczen.setCzyWybranyPrzedmiot(false);
                model.addAttribute("subjects", listaPrzedmiotow);
                model.addAttribute("user", uczen);
            }
        }
        return "show_grades";
    }
    @PostMapping("/showGrades2")
    public String showGrades2(@ModelAttribute("user") Uczen uczen, Model model) {

        List<Przedmiot> listaPrzedmiotow = przedmiotService.findSubjectsByClassName(uczen.getKlasa().getNazwa());
        uczen.setCzyWybranyPrzedmiot(true);

        listaPrzedmiotow = sortChosenSubjectList(listaPrzedmiotow, uczen.getWybranyPrzedmiot());

        List<Ocena> listaOcen = ocenaService.listaOcenUcznia(uczen.getId(), uczen.getWybranyPrzedmiot().getId());

        if (!listaOcen.isEmpty()) {
            uczen.setCzyPosiadaOceny(true);
            double sredniaWazona = Ocena.getSredniaWazona(listaOcen);
            String proponowanaOcena = Ocena.getProponowanaOcena(sredniaWazona);
            model.addAttribute("mean", sredniaWazona);
            model.addAttribute("suggestedGrade", proponowanaOcena);
        }
        else {
            uczen.setCzyPosiadaOceny(false);
            model.addAttribute("user", uczen);
            model.addAttribute("subjects", listaPrzedmiotow);
            return "show_grades_empty";
        }

        model.addAttribute("user", uczen);
        model.addAttribute("subjects", listaPrzedmiotow);
        model.addAttribute("grades", listaOcen);

        return "show_grades";
    }
    @GetMapping("/showInsertedGrades")
    public String showInsertedGrades(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Osoba uzytkownik = osobaService.findOsobaByEmail(email);
            List<Klasa> listaKlas = null;
            if (uzytkownik != null) {
                if (uzytkownik instanceof Nauczyciel) {
                    listaKlas = klasaService.findClassesByTeacherId(uzytkownik.getId());
                    ((Nauczyciel) uzytkownik).setCzyWybranaKlasa(false);
                    ((Nauczyciel) uzytkownik).setCzyNauczyciel(true);
                }
                else if (uzytkownik instanceof Admin) {
                    listaKlas = klasaService.findAllClasses();
                    ((Admin) uzytkownik).setCzyWybranaKlasa(false);
                    ((Admin) uzytkownik).setCzyNauczyciel(false);
                }
                model.addAttribute("classes", listaKlas);
                model.addAttribute("user", uzytkownik);
            }
        }
        return "show_inserted_grades";
    }
    @PostMapping("/showInsertedGradesTeacher")
    public String showInsertedGradesTeacher(@ModelAttribute("user")Nauczyciel nauczyciel, Model model) {
        List<Klasa> listaKlas = klasaService.findClassesByTeacherId(nauczyciel.getId());
        listaKlas = sortChosenClassList(listaKlas, nauczyciel.getWybranaKlasa());
        nauczyciel.setCzyWybranaKlasa(true);
        nauczyciel.setCzyNauczyciel(true);
        nauczyciel.setCzyWybranyPrzedmiot(false);

        List<Przedmiot> listaPrzedmiotow = przedmiotService.findSubjectsByClassNameAndTeacherId(nauczyciel.getId(), nauczyciel.getWybranaKlasa().getNazwa());
        model.addAttribute("classes", listaKlas);
        model.addAttribute("user", nauczyciel);
        model.addAttribute("subjects", listaPrzedmiotow);

        return "show_inserted_grades";
    }
    @PostMapping("/showInsertedGradesAdmin")
    public String showInsertedGradesAdmin(@ModelAttribute("user")Admin admin, Model model) {
        List<Klasa> listaKlas = klasaService.findAllClasses();
        listaKlas = sortChosenClassList(listaKlas, admin.getWybranaKlasa());
        admin.setCzyWybranaKlasa(true);
        admin.setCzyNauczyciel(false);
        admin.setCzyWybranyPrzedmiot(false);

        List<Przedmiot> listaPrzedmiotow = przedmiotService.findSubjectsByClassName(admin.getWybranaKlasa().getNazwa());

        model.addAttribute("classes", listaKlas);
        model.addAttribute("user", admin);
        model.addAttribute("subjects", listaPrzedmiotow);

        return "show_inserted_grades";
    }
    @PostMapping("/showInsertedGradesTeacher2")
    public String showInsertedGradesTeacher2(@ModelAttribute("user")Nauczyciel nauczyciel, Model model) {

        List<Klasa> listaKlas = klasaService.findClassesByTeacherId(nauczyciel.getId());
        List<Przedmiot> listaPrzedmiotow = przedmiotService.findSubjectsByClassNameAndTeacherId(nauczyciel.getId(), nauczyciel.getWybranaKlasa().getNazwa());
        List<Uczen> listaUczniow = osobaService.findStudentsByClassName(nauczyciel.getWybranaKlasa().getNazwa());
        initializeStudentsNumbers(listaUczniow);
        nauczyciel.setIdWybranegoPrzedmiotu(nauczyciel.getWybranyPrzedmiot().getId());

        listaKlas = sortChosenClassList(listaKlas, nauczyciel.getWybranaKlasa());
        listaPrzedmiotow = sortChosenSubjectList(listaPrzedmiotow, nauczyciel.getWybranyPrzedmiot());

        nauczyciel.setCzyWybranyPrzedmiot(true);
        nauczyciel.setCzyWybranaKlasa(true);
        nauczyciel.setCzyNauczyciel(true);

        model.addAttribute("classes", listaKlas);
        model.addAttribute("subjects", listaPrzedmiotow);
        model.addAttribute("user", nauczyciel);
        model.addAttribute("students", listaUczniow);

        return "show_inserted_grades";
    }
    @PostMapping("/showInsertedGradesAdmin2")
    public String showInsertedGradesAdmin2(@ModelAttribute("user")Admin admin, Model model) {

        List<Klasa> listaKlas = klasaService.findAllClasses();
        List<Przedmiot> listaPrzedmiotow = przedmiotService.findSubjectsByClassName(admin.getWybranaKlasa().getNazwa());
        List<Uczen> listaUczniow = osobaService.findStudentsByClassName(admin.getWybranaKlasa().getNazwa());
        initializeStudentsNumbers(listaUczniow);
        admin.setIdWybranegoPrzedmiotu(admin.getWybranyPrzedmiot().getId());

        listaKlas = sortChosenClassList(listaKlas, admin.getWybranaKlasa());
        listaPrzedmiotow = sortChosenSubjectList(listaPrzedmiotow, admin.getWybranyPrzedmiot());

        admin.setCzyWybranaKlasa(true);
        admin.setCzyWybranyPrzedmiot(true);
        admin.setCzyNauczyciel(false);

        model.addAttribute("classes", listaKlas);
        model.addAttribute("subjects", listaPrzedmiotow);
        model.addAttribute("user", admin);
        model.addAttribute("students", listaUczniow);

        return "show_inserted_grades";
    }
    @PostMapping("/showStudentGradesTeacher")
    public String showStudentGradesTeacher(@ModelAttribute("user")Nauczyciel nauczyciel, Model model) {

        nauczyciel.setCzyNauczyciel(true);
        nauczyciel.setCzyWybranyPrzedmiot(true);
        nauczyciel.setCzyWybranaKlasa(true);

        Przedmiot wybranyPrzedmiot = przedmiotService.findPrzedmiotById(nauczyciel.getIdWybranegoPrzedmiotu());
        Uczen uczen = nauczyciel.getWybranyUczen();
        List<Ocena> listaOcen = ocenaService.listaOcenUcznia(uczen.getId(), nauczyciel.getIdWybranegoPrzedmiotu());

        Ocena ocena = new Ocena();
        ocena.setUczen(uczen);
        ocena.setNauczyciel(nauczyciel);
        ocena.setPrzedmiot(wybranyPrzedmiot);

        if (!listaOcen.isEmpty()) {
            uczen.setCzyPosiadaOceny(true);
            double sredniaWazona = Ocena.getSredniaWazona(listaOcen);
            String sugerowanaOcena = Ocena.getProponowanaOcena(sredniaWazona);
            model.addAttribute("mean", sredniaWazona);
            model.addAttribute("suggestedGrade", sugerowanaOcena);
        }
        else {
            uczen.setCzyPosiadaOceny(false);
        }
        model.addAttribute("newGrade", ocena);
        model.addAttribute("grades", listaOcen);
        model.addAttribute("student", uczen);
        model.addAttribute("subject", wybranyPrzedmiot);


        return "show_inserted_grades1";
    }
    @PostMapping("/showStudentGradesAdmin")
    public String showStudentGradesAdmin(@ModelAttribute("user")Admin admin, Model model) {

        admin.setCzyNauczyciel(false);
        admin.setCzyWybranaKlasa(true);
        admin.setCzyWybranyPrzedmiot(true);

        Przedmiot wybranyPrzedmiot = przedmiotService.findPrzedmiotById(admin.getIdWybranegoPrzedmiotu());
        Uczen uczen = admin.getWybranyUczen();

        List<Ocena> listaOcen = ocenaService.listaOcenUcznia(uczen.getId(), admin.getIdWybranegoPrzedmiotu());

        Ocena ocena = new Ocena();
        Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(13);
        ocena.setNauczyciel(nauczyciel);
        ocena.setUczen(uczen);
        ocena.setPrzedmiot(wybranyPrzedmiot);

        if (!listaOcen.isEmpty()) {
            uczen.setCzyPosiadaOceny(true);
            double sredniaWazona = Ocena.getSredniaWazona(listaOcen);
            String sugerowanaOcena = Ocena.getProponowanaOcena(sredniaWazona);
            model.addAttribute("mean", sredniaWazona);
            model.addAttribute("suggestedGrade", sugerowanaOcena);
        }
        else {
            uczen.setCzyPosiadaOceny(false);
        }

        model.addAttribute("newGrade", ocena);
        model.addAttribute("grades", listaOcen);
        model.addAttribute("student", uczen);
        model.addAttribute("subject", wybranyPrzedmiot);



        return "show_inserted_grades1";
    }
    @PostMapping("/addGrade")
    public String addGrade(@ModelAttribute("newGrade")Ocena ocena) {
        ocenaService.dodajOcene(ocena.getUczen(), ocena.getNauczyciel(), ocena.getPrzedmiot(), ocena.getWartosc(), ocena.getWaga(), ocena.getEtykieta());

        return "redirect:/showInsertedGrades";
    }
    @GetMapping("/updateGrade")
    public String updateGrade(@RequestParam("gradeId")int idOceny, Model model) {
        Ocena ocena = ocenaService.getGradeById(idOceny);
        model.addAttribute("grade", ocena);
        return "update_grade";
    }

    @PostMapping("/updateGrade1")
    public String updateGrade1(@ModelAttribute("grade")Ocena ocena) {
        Klasa klasa = ocena.getUczen().getKlasa();
        ocena.setDataWpisania(LocalDate.now());
        ocena.setGodzinaWpisania(LocalTime.now());
        ocena.setKlasa(klasa);
        ocenaService.updateGrade(ocena);
        return "redirect:/showInsertedGrades";
    }
    @GetMapping("/deleteGrade")
    public String deleteGrade(@RequestParam("gradeId")int idOceny) {
        Ocena ocena = ocenaService.getGradeById(idOceny);
        ocenaService.deleteGradeById(idOceny);
        return "redirect:/showInsertedGrades";
    }
    public static void initializeStudentsNumbers(List<Uczen> listaUczniow) {

        int nr = 0;

        for (Uczen uczen : listaUczniow) {
            nr++;
            uczen.setNr(nr);
        }
    }
    public static List<Klasa> sortChosenClassList(List<Klasa> listaKlas, Klasa klasaWybrana) {
        Klasa klasa0 = listaKlas.get(0);
        int indexWybranejKLasy = 0;

        for (Klasa klasa : listaKlas) {
            if (klasa.getNazwa().equals(klasaWybrana.getNazwa())) {
                indexWybranejKLasy = listaKlas.indexOf(klasa);
                break;
            }
        }

        listaKlas.set(0, klasaWybrana);
        listaKlas.set(indexWybranejKLasy, klasa0);

        return listaKlas;
    }
    public static List<Przedmiot> sortChosenSubjectList(List<Przedmiot> listaPrzedmiotow, Przedmiot przedmiotWybrany) {
        Przedmiot przedmiot0 = listaPrzedmiotow.get(0);
        int indexWybranegoPrzedmiotu = 0;

        for (Przedmiot przedmiot : listaPrzedmiotow) {
            if (przedmiot.equals(przedmiotWybrany)) {
                indexWybranegoPrzedmiotu = listaPrzedmiotow.indexOf(przedmiot);
                break;
            }
        }

        listaPrzedmiotow.set(0, przedmiotWybrany);
        listaPrzedmiotow.set(indexWybranegoPrzedmiotu, przedmiot0);

        return listaPrzedmiotow;
    }
    public static List<Uczen> sortChosenStudentList(List<Uczen> listaOsob, Uczen osobaWybrana) {
        Uczen osoba0 = listaOsob.get(0);
        int indexWybranejOsoby = 0;

        for (Uczen osoba : listaOsob) {
            if (osoba.equals(osobaWybrana)) {
                indexWybranejOsoby = listaOsob.indexOf(osoba);
                break;
            }
        }

        listaOsob.set(0, osobaWybrana);
        listaOsob.set(indexWybranejOsoby, osoba0);

        return listaOsob;
    }
    public static List<Rodzic> sortChosenParentList(List<Rodzic> listaOsob, Rodzic osobaWybrana) {
        Rodzic osoba0 = listaOsob.get(0);
        int indexWybranejOsoby = 0;

        for (Rodzic osoba : listaOsob) {
            if (osoba.equals(osobaWybrana)) {
                indexWybranejOsoby = listaOsob.indexOf(osoba);
                break;
            }
        }

        listaOsob.set(0, osobaWybrana);
        listaOsob.set(indexWybranejOsoby, osoba0);

        return listaOsob;
    }

    public static List<Nauczyciel> sortChosenTeachersList(List<Nauczyciel> listaOsob, Nauczyciel osobaWybrana) {
        Nauczyciel osoba0 = listaOsob.get(0);
        int indexWybranejOsoby = 0;

        for (Nauczyciel osoba : listaOsob) {
            if (osoba.equals(osobaWybrana)) {
                indexWybranejOsoby = listaOsob.indexOf(osoba);
                break;
            }
        }

        listaOsob.set(0, osobaWybrana);
        listaOsob.set(indexWybranejOsoby, osoba0);

        return listaOsob;
    }

}
