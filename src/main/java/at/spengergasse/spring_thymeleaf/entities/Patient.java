package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;
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
    private LocalDate geburtsdatum;

    public int getId() { return id; }
    public String getSvnr() { return svnr; }
    public void setSvnr(String svnr) { this.svnr = svnr; }
    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }
    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }
    public String getGeschlecht() { return geschlecht; }
    public void setGeschlecht(String geschlecht) { this.geschlecht = geschlecht; }
    public LocalDate getGeburtsdatum() { return geburtsdatum; }
    public void setGeburtsdatum(LocalDate geburtsdatum) { this.geburtsdatum = geburtsdatum; }
}