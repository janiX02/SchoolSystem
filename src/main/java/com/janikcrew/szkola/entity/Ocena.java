package com.janikcrew.szkola.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "ocena")
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ucznia")
    private Uczen uczen;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nauczyciela")
    private Nauczyciel nauczyciel;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot przedmiot;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "nazwa_klasy")
    private Klasa klasa;

    @Column(name = "wartość")
    private double wartosc;

    @Column(name = "waga")
    private int waga;

    @Column(name = "etykieta")
    private String etykieta;

    @Column(name = "data_wpisania")
    private LocalDate dataWpisania;

    @Column(name = "godzina_wpisania")
    private LocalTime godzinaWpisania;

    @Transient
    private double sredniaWazona;

    @Transient
    private String sugerowanaOcena;

    public Ocena() {
        dataWpisania = LocalDate.now();
        godzinaWpisania = LocalTime.now();
    }

    public Ocena(double wartosc, int waga, String etykieta) {
        this.wartosc = wartosc;
        this.waga = waga;
        this.etykieta = etykieta;
        dataWpisania = LocalDate.now();
        godzinaWpisania = LocalTime.now();
    }

    public Ocena(Uczen uczen, Nauczyciel nauczyciel, Przedmiot przedmiot, Klasa klasa, double wartosc, int waga, String etykieta) {
        this.uczen = uczen;
        this.nauczyciel = nauczyciel;
        this.przedmiot = przedmiot;
        this.klasa = klasa;
        this.wartosc = wartosc;
        this.waga = waga;
        this.etykieta = etykieta;
        dataWpisania = LocalDate.now();
        godzinaWpisania = LocalTime.now();
    }
    public String getOcena() {
        if (wartosc == 1)
            return "1";
        else if (wartosc == 1.5)
            return "1+";
        else if (wartosc == 1.75)
            return "2-";
        else if (wartosc == 2.0)
            return "2";
        else if (wartosc == 2.5)
            return "2+";
        else if (wartosc == 2.75)
            return "3-";
        else if (wartosc == 3.0)
            return "3";
        else if (wartosc == 3.5)
            return "3+";
        else if (wartosc == 3.75)
            return "4-";
        else if (wartosc == 4.0)
            return "4";
        else if (wartosc == 4.5)
            return "4+";
        else if (wartosc == 4.75)
            return "5-";
        else if (wartosc == 5.0)
            return "5";
        else if (wartosc == 5.5)
            return "5+";
        else if (wartosc == 5.75)
            return "6-";
        else
            return "6";

    }
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate currentDate = LocalDate.now();
        String formattedTime = godzinaWpisania.format(formatter);
        String month = formattedMonth();
        int day = dataWpisania.getDayOfMonth();
        int year = dataWpisania.getYear();

        if (dataWpisania.getYear() == currentDate.getYear())
            return day + " " + month + " o " +  formattedTime;
        else
            return day + " " + month +" "+ year + " o " +  formattedTime;
    }
    public static double getSredniaWazona(List<Ocena> listaOcen) {
        double srednia = 0;
        int iloscOcen = 0;

        for (Ocena ocena : listaOcen) {
            for (int i = 0; i < ocena.getWaga(); i++) {
                srednia += ocena.getWartosc();
                iloscOcen++;
            }
        }

        srednia /= iloscOcen;
        int currentDecimalPlaces = BigDecimal.valueOf(srednia).scale();

        if (currentDecimalPlaces > 2) {
            srednia = round(srednia, 2);
        }
        return srednia;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static String getProponowanaOcena(double srednia) {
        if (srednia < 2.0)
            return "niedostateczny";
        else if (srednia < 2.75)
            return "dopuszczający";
        else if (srednia < 3.75)
            return "dostateczny";
        else if (srednia < 4.75)
            return "dobry";
        else if (srednia < 5.50)
            return "bardzo dobry";
        else
            return "celujący";
    }
    private String formattedMonth() {
        return switch (dataWpisania.getMonth().getValue()) {
            case 1 -> "styczeń";
            case 2 -> "luty";
            case 3 -> "marzec";
            case 4 -> "kwiecień";
            case 5 -> "maj";
            case 6 -> "czerwiec";
            case 7 -> "lipiec";
            case 8 -> "sierpień";
            case 9 -> "wrzesień";
            case 10 -> "październik";
            case 11 -> "listopad";
            case 12 -> "grudzień";
            default -> null;
        };
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public String getEtykieta() {
        return etykieta;
    }

    public void setEtykieta(String etykieta) {
        this.etykieta = etykieta;
    }

    public LocalDate getDataWpisania() {
        return dataWpisania;
    }

    public void setDataWpisania(LocalDate dataWpisania) {
        this.dataWpisania = dataWpisania;
    }

    public LocalTime getGodzinaWpisania() {
        return godzinaWpisania;
    }

    public void setGodzinaWpisania(LocalTime godzinaWpisania) {
        this.godzinaWpisania = godzinaWpisania;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public double getSredniaWazona() {
        return sredniaWazona;
    }

    public void setSredniaWazona(double sredniaWazona) {
        this.sredniaWazona = sredniaWazona;
    }

    public String getSugerowanaOcena() {
        return sugerowanaOcena;
    }

    public void setSugerowanaOcena(String sugerowanaOcena) {
        this.sugerowanaOcena = sugerowanaOcena;
    }

    @Override
    public String toString() {
        return "Ocena{" +
                ", wartosc=" + wartosc +
                ", waga=" + waga +
                ", etykieta='" + etykieta + '\'' +
                ", dataWpisania=" + dataWpisania +
                ", godzinaWpisania=" + godzinaWpisania +
                '}';
    }
}
