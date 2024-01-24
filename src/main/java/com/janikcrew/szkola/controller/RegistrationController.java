package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.dto.StudentParentDTO;
import com.janikcrew.szkola.entity.Rodzic;
import com.janikcrew.szkola.entity.Uczen;
import com.janikcrew.szkola.service.OsobaService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RegistrationController {
    private final OsobaService osobaService;

    @Autowired
    public RegistrationController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }

    @GetMapping("/showRegistrationPage")
    public String showRegistrationPage(Model model) {
        StudentParentDTO studentParentDTO = new StudentParentDTO();

        studentParentDTO.setSamePesel(false);
        studentParentDTO.setCorrectDate(false);
        studentParentDTO.setCorrectPesel(false);
        studentParentDTO.setSameEmails(false);
        studentParentDTO.setHasInvalidEmail(false);
        studentParentDTO.setSamePesel(false);
        studentParentDTO.setGoodPassword(true);
        studentParentDTO.setCorrectName(true);

        model.addAttribute("studentParent", studentParentDTO);
        return "registration_page";
    }
    @PostMapping("/registerTheUser")
    public String registerTheUser(@ModelAttribute("studentParent")StudentParentDTO studentParentDTO, Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final String PESEL_REGEX = "^[0-9]{11}$";
        final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d\\d$";
        final String PASSWORD_REGEX = "^.{6,}$";
        final String NAME_REGEX = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-zA-Ząćęłńóśźż]{1,}$";

        if (studentParentDTO.getImieUcznia().equals("") || studentParentDTO.getImieRodzica().equals("")
        || studentParentDTO.getNazwiskoUcznia().equals("") || studentParentDTO.getNazwiskoRodzica().equals("")
        || studentParentDTO.getPeselUcznia().equals("") || studentParentDTO.getPeselRodzica().equals("")
        || studentParentDTO.getEmailUcznia().equals("") || studentParentDTO.getEmailRodzica().equals("")
        || studentParentDTO.getDataUrodzeniaUcznia().equals("") || studentParentDTO.getDataUrodzeniaRodzica().equals("")
        || studentParentDTO.getHasloUcznia().equals("") || studentParentDTO.getHasloRodzica().equals("")) {

            studentParentDTO.setHasEmptyFields(true);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";
        }

        if (!studentParentDTO.getEmailRodzica().matches(EMAIL_REGEX) || !studentParentDTO.getEmailUcznia().matches(EMAIL_REGEX)) {
            studentParentDTO.setHasInvalidEmail(true);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";
        }

        if (studentParentDTO.getEmailUcznia().equals(studentParentDTO.getEmailRodzica())) {
            studentParentDTO.setSameEmails(true);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";
        }

        if (!studentParentDTO.getDataUrodzeniaUcznia().matches(DATE_REGEX) || !studentParentDTO.getDataUrodzeniaRodzica().matches(DATE_REGEX)) {
            studentParentDTO.setCorrectDate(true);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";
        }

        if (!studentParentDTO.getPeselUcznia().matches(PESEL_REGEX) || !studentParentDTO.getPeselRodzica().matches(PESEL_REGEX)) {
            studentParentDTO.setCorrectPesel(true);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";
        }

        if (studentParentDTO.getPeselUcznia().equals(studentParentDTO.getPeselRodzica())) {
            studentParentDTO.setSamePesel(true);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";
        }

        if (!studentParentDTO.getHasloUcznia().matches(PASSWORD_REGEX) || !studentParentDTO.getHasloRodzica().matches(PASSWORD_REGEX)) {
            studentParentDTO.setGoodPassword(false);
            model.addAttribute("studentParent", studentParentDTO);
            return "registration_page";

        }


        Uczen uczen = new Uczen();
        Rodzic rodzic = new Rodzic();
        uczen.setAccepted(0);
        rodzic.setAccepted(0);
        uczen.setActive(1);
        rodzic.setActive(1);
        uczen.setImie(studentParentDTO.getImieUcznia());
        rodzic.setImie(studentParentDTO.getImieRodzica());
        uczen.setNazwisko(studentParentDTO.getNazwiskoUcznia());
        rodzic.setNazwisko(studentParentDTO.getNazwiskoRodzica());
        uczen.setPesel(studentParentDTO.getPeselUcznia());
        rodzic.setPesel(studentParentDTO.getPeselRodzica());
        uczen.setDataUrodzenia(LocalDate.parse(studentParentDTO.getDataUrodzeniaUcznia(), formatter));
        rodzic.setDataUrodzenia(LocalDate.parse(studentParentDTO.getDataUrodzeniaRodzica(), formatter));
        uczen.setEmail(studentParentDTO.getEmailUcznia());
        rodzic.setEmail(studentParentDTO.getEmailRodzica());
        uczen.setHaslo(BCrypt.hashpw(studentParentDTO.getHasloUcznia(), BCrypt.gensalt()));
        rodzic.setHaslo(BCrypt.hashpw(studentParentDTO.getHasloRodzica(), BCrypt.gensalt()));

        osobaService.dodajRodzicaUcznia(rodzic, uczen);

        return "redirect:/loginPage";

    }
}
