package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String svnr;
    private String vorname;
    private String nachname;
    private String geschlecht;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate geburtsdatum;

    public int getId() {
        return id;
    }

    public String getSvnr() {
        return svnr;
    }

    public void setSvnr(String svnr) {
        // einfache Prüfung: genau 10 Zahlen
        if (svnr == null || !svnr.matches("\\d{10}")) {
            throw new IllegalArgumentException("Ungültige Sozialversicherungsnummer");
        }
        this.svnr = svnr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        if (geburtsdatum.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Geburtsdatum darf nicht in der Zukunft liegen");
        }
        this.geburtsdatum = geburtsdatum;
    }
}