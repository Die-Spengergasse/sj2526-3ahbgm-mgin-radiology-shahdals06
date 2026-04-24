package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Reservierung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Geraet geraet;

    @ManyToOne
    private Patient patient;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservierungszeit;

    private String koerperregion;
    private String kommentar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Geraet getGeraet() {
        return geraet;
    }

    public void setGeraet(Geraet geraet) {
        this.geraet = geraet;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getReservierungszeit() {
        return reservierungszeit;
    }

    public void setReservierungszeit(LocalDateTime reservierungszeit) {
        if (reservierungszeit.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservierung darf nicht in der Vergangenheit liegen");
        }
        this.reservierungszeit = reservierungszeit;
    }

    public String getKoerperregion() {
        return koerperregion;
    }

    public void setKoerperregion(String koerperregion) {
        this.koerperregion = koerperregion;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}