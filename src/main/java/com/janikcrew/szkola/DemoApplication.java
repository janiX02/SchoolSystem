package com.janikcrew.szkola;

import com.janikcrew.szkola.dao.BudzetDAO;
import com.janikcrew.szkola.dao.BudzetDAOImpl;
import com.janikcrew.szkola.dao.KlasaDAO;
import com.janikcrew.szkola.dao.OsobaDAO;
import com.janikcrew.szkola.entity.*;
import com.janikcrew.szkola.service.*;
import jakarta.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	//działa
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(BudzetService budzetService, OsobaService osobaService,
											   KlasaService klasaService, PrzedmiotService przedmiotService,
											   WiadomoscService wiadomoscService, UwagaService uwagaService,
											   AdminService adminService, GodzinaLekcyjnaService godzinaLekcyjnaService,
											   MiejsceService miejsceService, DyzurService dyzurService,
											   OcenaService ocenaService, WydarzenieService wydarzenieService) {
		return runner -> {
			//utworzOsobe(osobaService);
			//testTransakcji(budzetService);
			//testUtworzeniaNauczyciela(budzetService, osobaService);
			//testUtworzeniaKlasy(osobaService, klasaService);
			//testUtworzeniaUcznia(osobaService, klasaService);
			//testDodaniaUczniaDoKlasy(klasaService, osobaService);
			//testUtworzeniaPrzedmiotu(przedmiotService, klasaService, osobaService);
			//testUsunieciaPrzedmiotu(przedmiotService);
			//testDzialaniaKlasy(przedmiotService, osobaService, klasaService);
			//testUtworzeniaWiadomosci(wiadomoscService, osobaService);
			//utworzUwage(uwagaService, osobaService);
			//testUtworzeniaKalendarza(adminService);
			//testUsunieciaKalendarza(adminService);
			//testUtworzeniaGodzinyLekcyjnej(godzinaLekcyjnaService, osobaService, przedmiotService, klasaService, miejsceService);
			//testUsunieciaGodzinyLekcyjnej(godzinaLekcyjnaService);
			//testDodaniaZastepstwa(godzinaLekcyjnaService, osobaService);
			//testDodaniaZastepujacejSali(miejsceService, godzinaLekcyjnaService);
			//testDodaniaZdarzenia(godzinaLekcyjnaService);
			//testDodaniaMiejsca(miejsceService);
			//testModyfikacjiNazwySali(miejsceService);
			//testUtworzeniaDyzuru(dyzurService, osobaService, miejsceService);
			//testUsunieciaDyzurow(dyzurService);
			//testDodaniaOceny(osobaService, przedmiotService, ocenaService);
			//testUsunieciaOceny(ocenaService);
			//testZnalezieniaOcenUcznia(ocenaService, osobaService, przedmiotService);
			//(wydarzenieService, osobaService);
			//wyswietlWydarzenie(wydarzenieService);
			//dodajWydarzenie(wydarzenieService, osobaService);
			//znajdzUczniowZKlasy(klasaService);
			//testZnalezieniaUczniow(osobaService, klasaService);
			//testZnalezieniaWszystkichNauczycieli(osobaService);
			//testUsunieciaWiadomosci(wiadomoscService);
			//testZnalezieniaNiezaakceptowanychUzytkownikow(osobaService);



		};
	}

	private void testZnalezieniaNiezaakceptowanychUzytkownikow(OsobaService osobaService) {
		List<Uczen> lista = osobaService.findNotAcceptedStudents();
		System.out.println(lista.get(0));

	}

	private void hashPassword() {
		// Hash a password
		String originalPassword = "mySecretPassword";
		String hashedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());

		System.out.println("Original Password: " + originalPassword);
		System.out.println("Hashed Password: " + hashedPassword);

		// Check a password against a stored hash
		boolean passwordMatch = BCrypt.checkpw(originalPassword, hashedPassword);

		System.out.println("Password Match: " + passwordMatch);
	}
	private void testUsunieciaWiadomosci(WiadomoscService wiadomoscService) {
		//Wiadomosc wiadomosc = wiadomoscService.usunWiadomosc();
	}

	private void testZnalezieniaWszystkichNauczycieli(OsobaService osobaService) {
		List<Nauczyciel> listaNauczycieli = osobaService.findAllTeachers();
		for (Nauczyciel nauczyciel : listaNauczycieli)
			System.out.println(nauczyciel.toString());
	}

	private void testZnalezieniaUczniow(OsobaService osobaService, KlasaService klasaService) {

		List<Uczen> listaUczniow = osobaService.findStudentsByClassName("1a");
		for (Uczen uczen : listaUczniow) {
			System.out.println(uczen.toString());
		}
	}

	private void znajdzUczniowZKlasy(KlasaService klasaService) {
		Klasa klasa = klasaService.findKlasaByName("1a");
		List<Uczen> uczniowie = klasa.getListaUczniow();
		for(Uczen uczen : uczniowie) {
			System.out.println(uczen.getImie());
		}
	}

	private void wyswietlWydarzenie(WydarzenieService wydarzenieService) {
		int eventId = 4;
		Wydarzenie wydarzenie = wydarzenieService.findEventById(eventId);
		System.out.println(wydarzenie.getAutor().getImie());


	}


	private void dodajWydarzenie(WydarzenieService wydarzenieService, OsobaService osobaService) {
		int idAutora = 2;
		Osoba autor = osobaService.findOsobaById(idAutora);
		String tytuł = "„ Opowieść wigilijna …inaczej”";
		String zawartosc = "Okres przedświąteczny to doskonała okazja do wspólnych spotkań i prezentacji artystycznych w klimacie nadchodzących świąt. Koło teatralne działające w SZS-P nr 2 w Chełmku pod kierunkiem Pani  Marioli Krzeczkowskiej przygotowało i przedstawiło spektakl pt. „Opowieść wigilijna inaczej”. Zespół gościł na zaproszenie bibliotekarki, Pani Bogusławy Cygan, z którą zrealizowano to przedsięwzięcie. Główną rolę zagrał uczeń klasy 7a, który wcielił się w rolę Scrooga doskonale prezentując wszystkie cechy skąpego i bezdusznego bohatera tej opowieści. Pozostali aktorzy z klasy 7a i 6 b także świetnie odegrali swoje role i wzbudzili aplauz zgromadzonej publiczności czyli uczniów klas zerowych i ich wychowawców. Scenariusz do tej inscenizacji napisała Pani Mariola Krzeczkowska, przy współpracy uczniów należących do koła. Ta świąteczna inscenizacja i wykorzystana w niej nastrojowa muzyka a także piękna dekoracja biblioteki wprowadziła gości w cudowną atmosferę zbliżających się świąt. " +
				"" +
				"Spektakl został przedstawiony w siedzibie MBP  w Chełmku, która z tej okazji przygotowała słodki pierniczkowy poczęstunek. Widownia była zachwycona grą aktorską uczniów i brała żywy udział w spektaklu. Potem odbyły się warsztaty, na których adepci koła teatralnego wraz z opiekunem uczyli przedszkolaków wykonywania choinek z serwetek jako dekoracji na świąteczny stół. Zwieńczeniem tego spotkania były wspólne zabawy i tańce. Najmłodsze pokolenie oraz starsi uczniowie świetnie razem się bawili i wspólnie śpiewali świąteczne piosenki.\n" +
				"" +
				"Ta współpraca biblioteki szkolnej z MBP w Chełmku trwająca już wiele lat wciąż się rozwija i co bardzo korzystnie wpływa na różnorodność ofert edukacyjnych obu placówek. Uczniowie chętnie biorą udział we wspólnych działaniach. Ten przedświąteczny występ był tego dowodem, a  młodzież z koła teatralnego już przygotowuje się do kolejnego występu międzypokoleniowego, który odbędzie się z okazji Święta Babci i Dziadka.";
		//wydarzenieService.addEvent(tytuł, zawartosc, autor);
		Wydarzenie wydarzenie = wydarzenieService.findEventById(4);
		wydarzenie.setAutor(autor);
		wydarzenieService.updateEvent(wydarzenie);

	}


	private void testZnalezieniaOcenUcznia(OcenaService ocenaService, OsobaService osobaService, PrzedmiotService przedmiotService) {
		Uczen uczen = (Uczen) osobaService.findOsobaById(9);
		Przedmiot przedmiot = przedmiotService.findPrzedmiotById(1);
		//ocenaService.listaOcenUcznia(uczen);
		System.out.println(uczen.getOceny());
	}

	private void testUsunieciaOceny(OcenaService ocenaService) {
		ocenaService.usunOceneById(1);
	}

	private void testDodaniaOceny(OsobaService osobaService, PrzedmiotService przedmiotService, OcenaService ocenaService) {
		int idUcznia = 9;
		int idNauczyciela = 2;
		Uczen uczen = (Uczen) osobaService.findOsobaById(idUcznia);
		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(idNauczyciela);
		Przedmiot przedmiot = przedmiotService.findPrzedmiotById(1);
		ocenaService.dodajOcene(uczen, nauczyciel, przedmiot, 3.75, 2, "Kartkówka funkcja liniowa");
		ocenaService.dodajOcene(uczen, nauczyciel, przedmiot, 4.5, 2, "Odpowiedź ustna 1");
	}

	private void testUsunieciaDyzurow(DyzurService dyzurService) {
		dyzurService.usunWszystkieDyzury();
	}

	private void testUtworzeniaDyzuru(DyzurService dyzurService, OsobaService osobaService, MiejsceService miejsceService) {
		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(7);
		int idMiejsca = 10;
		String nazwaDnia = "poniedziałek";
		String godzRozpoczecia = "08:45:00";
		String dataRozpoczecia = "2023-09-04";
		Miejsce miejsce = miejsceService.findMiejsceById(idMiejsca);
		dyzurService.dodajDyzurDoPlanuDyzurow(nauczyciel, miejsce, nazwaDnia, godzRozpoczecia, dataRozpoczecia);

	}

	private void testUsunieciaKalendarza(AdminService adminService) {
		adminService.wyczyscKalendarz();
	}

	private void testDodaniaZastepujacejSali(MiejsceService miejsceService, GodzinaLekcyjnaService godzinaLekcyjnaService) {
		int idGodzinyLekcyjnej = 15;
		int idSaliZastepujacej = 5;
		godzinaLekcyjnaService.dodajZastepujacaSale(idGodzinyLekcyjnej, idSaliZastepujacej);
	}

	private void testModyfikacjiNazwySali(MiejsceService miejsceService) {
		String staraNazwa = "Sala Gimnastyczna nr 21";
		String nowaNazwa = "Sala gimnastyczna nr 21";
		miejsceService.aktualizujMiejsce(staraNazwa, nowaNazwa);
	}

	private void testDodaniaMiejsca(MiejsceService miejsceService) {
		String miejsce = "Sala nr 1";
 		miejsceService.dodajMiejsce(miejsce);
	}

	private void testUsunieciaGodzinyLekcyjnej(GodzinaLekcyjnaService godzinaLekcyjnaService) {
		int idPrzedmiotu = 3;
		godzinaLekcyjnaService.usunGodzineZPlanuByIdPrzedmiotu(idPrzedmiotu);
	}

	private void testDodaniaZdarzenia(GodzinaLekcyjnaService godzinaLekcyjnaService) {
		String zdarzenie = "Klasowe wyjście do kina";
		godzinaLekcyjnaService.dodajZdarzenieDoGodzinyLekcyjnej(16, zdarzenie);
	}

	private void testDodaniaZastepstwa(GodzinaLekcyjnaService godzinaLekcyjnaService, OsobaService osobaService) {
		godzinaLekcyjnaService.dodajZastepstwoDoGodzinyLekcyjnej(16, 2);
	}

	private void testUtworzeniaGodzinyLekcyjnej(GodzinaLekcyjnaService godzinaLekcyjnaService, OsobaService osobaService, PrzedmiotService przedmiotService, KlasaService klasaService, MiejsceService miejsceService) {
		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(7);
		Przedmiot przedmiot = przedmiotService.findPrzedmiotById(3);
		Klasa klasa = klasaService.findKlasaByName("1a");
		Miejsce miejsce = miejsceService.findMiejsceById(1);
		String dzien = "poniedziałek";
		String godzRozpoczecia = "08:00:00";
		String dataRozpoczecia = "2023-09-04";
		godzinaLekcyjnaService.dodajGodzinaLekcyjnaDoPlanuLekcji(przedmiot, klasa, miejsce, dzien, godzRozpoczecia, dataRozpoczecia);
	}

	private void testUtworzeniaKalendarza(AdminService adminService) {
		adminService.utworzKalendarzNaRokSzkolny();
	}

	private void utworzUwage(UwagaService uwagaService, OsobaService osobaService) {
		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(7);
		Uczen uczen = (Uczen) osobaService.findOsobaById(9);
		Uwaga uwaga = new Uwaga("POZYTYWNA", "Obrona racji narodowych", "Łukasz pobił kolegę Patryka, który na lekcji śpiewał hymn związku radzieckiego, po czym dumnie wyśpiewał hymn Polski, a następnie hymn Niemiec, co zasługuje na szczególną pochwałę! (Pomimo tak zaszczytnego zachowania nie mogę ustawić oceny wzorowej z zachowania, ponieważ wyśpiewał hymn Polski przed hymnem Niemiec)");
		uwagaService.utworzUwage(uwaga, nauczyciel, uczen);
	}


	private void testUtworzeniaWiadomosci(WiadomoscService wiadomoscService, OsobaService osobaService) {
		Admin admin = (Admin) osobaService.findOsobaById(1);
		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(2);
		Wiadomosc wiadomosc = new Wiadomosc("Szanowny Dyrektorze! ", "Dziękuję za przyjęcie do pracy! ");
		wiadomoscService.utworzWiadomosc(wiadomosc, nauczyciel, admin);
	}

	private void testUsunieciaPrzedmiotu(PrzedmiotService przedmiotService) {
		int id = 2;
		przedmiotService.deletePrzedmiotById(id);
	}

	private void testDzialaniaKlasy(PrzedmiotService przedmiotService, OsobaService osobaService, KlasaService klasaService) {
		Klasa klasa = klasaService.findKlasaByName("1a");
		for(Przedmiot przedmiot : klasa.getListaPrzedmiotow()) {
			System.out.println(przedmiot.getNazwa());
		}
	}


	private void testUtworzeniaPrzedmiotu(PrzedmiotService przedmiotService, KlasaService klasaService, OsobaService osobaService) {
		Przedmiot przedmiot = new Przedmiot("język niemiecki");
		String nazwaKlasy = "1b";
		int idNauczyciela = 7;
		Klasa klasa = klasaService.findKlasaByName(nazwaKlasy);
		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(idNauczyciela);

		przedmiotService.dodajPrzedmiot(przedmiot, nauczyciel, klasa);

	}
	private void testDodaniaUczniaDoKlasy(KlasaService klasaService, OsobaService osobaService) {
		int id = 3;
		int id1 = 6;
		String name = "1a";
		Klasa klasa = klasaService.findKlasaByName(name);
		Uczen uczen = (Uczen) osobaService.findOsobaById(id);
		Uczen uczen1 = (Uczen) osobaService.findOsobaById(id1);
		klasaService.dodajUcznia(klasa, uczen, uczen1);


	}
	private void utworzOsobe(OsobaService osobaService) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		/*Uczen uczen = new Uczen("02256745432", "Kacper", "Łukawski", "klukawski45@gmail.com", LocalDate.parse("2002-12-12"));
		Rodzic rodzic = new Rodzic("694785655678", "Maria", "Łukawska", "gorzowianka32@onet.pl", LocalDate.parse("1969-04-24", formatter));
		osobaService.dodajRodzicaUcznia(rodzic, uczen);
		 */
		Nauczyciel nauczyciel = new Nauczyciel("68111094029", "Marek", "Idzik", "marekidzik1@gmail.com", LocalDate.parse("1968-11-10"));
		osobaService.dodajNauczyciela(nauczyciel);

	}

	private void testUtworzeniaUcznia(OsobaService osobaService, KlasaService klasaService) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Uczen uczen = new Uczen("06222787564", "Michał", "Janicki", "michal06@interia.pl", LocalDate.parse("2006-05-27", formatter));
		Rodzic rodzic = new Rodzic("76983746738", "Ewa", "Janicka", "janike@interia.pl", LocalDate.parse("1976-11-23", formatter));
		Klasa klasa = klasaService.findKlasaByName("1b");
		osobaService.dodajRodzicaUcznia(rodzic, uczen);
		klasaService.dodajUcznia(klasa, uczen);

	}

	private void testUtworzeniaKlasy(OsobaService osobaService, KlasaService klasaService) {
		int id = 7;

		Nauczyciel nauczyciel = (Nauczyciel) osobaService.findOsobaById(id);
		Klasa klasa = new Klasa("1b");
		klasaService.dodajKlase(klasa, nauczyciel);
	}

	private void testUtworzeniaNauczyciela(BudzetService budzetService, OsobaService osobaService) {

		int id = 2;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		//osobaService.dodajUzytkownika(nauczyciel);
		osobaService.dodajNauczyciela(new Nauczyciel("57829485748", "Donald", "Tusk", "donald57@gmail.com", LocalDate.parse("1957-06-06", formatter)));
	}

	public void testTransakcji(BudzetService budzetService) throws Exception {
		int id = 1;
		Budzet budzet = budzetService.findBudzetById(id);
		//Transakcja transakcja = new Transakcja("WYDATEK", 20000.0, LocalDate.now(), LocalTime.now(), "Malowanie pomieszczeń");
		//budzetService.dodajTransakcjeDoBudzetu(budzet, transakcja);
		budzetService.znajdzListeTransakcjiBudzetu(budzet);

		for(Transakcja transakcja : budzet.getListaTransakcji())
			System.out.println(transakcja);


	}
}
